import org.amshove.kluent.shouldEqual
import org.junit.Test


class ConditionValidationTests {

    class ConditionedValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size }.greaterThan(4).whenTrue { it.name == "FirstName" }
            validate { it.floatSize }.greaterThan(4).whenTrue { it.name == "FirstName" }.whenTrue { it.nullableSize != null }
        }
    }

    class InverseConditionedValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size }.greaterThan(4).unless { it.name == "FirstName" }
        }
    }

    private val conditionedValidator = ConditionedValidator()
    private val inverseValidator = InverseConditionedValidator()

    @Test
    fun `should validate positive when inverse condition is true`() {
        inverseValidator.validate(Tree(size = 3, name = "FirstName")).isValid.shouldEqual(true)
    }

    @Test
    fun `should validate negative when inverse condition is false`() {
        inverseValidator.validate(Tree(size = 3, name = "FalseCondition")).isValid.shouldEqual(false)
    }

    @Test
    fun `should validate negative when condition is true`() {
        conditionedValidator.validate(Tree(size = 3, name = "FirstName")).isValid.shouldEqual(false)
    }

    @Test
    fun `should not validate positive when condition is false`() {
        conditionedValidator.validate(Tree(size = 3, name = "FalseCondition")).isValid.shouldEqual(true)
    }

    @Test
    fun `should validate negative when all conditions are true`() {
        conditionedValidator.validate(Tree(floatSize = 3.0f, name = "FirstName", nullableSize = 3)).isValid.shouldEqual(false)
    }

    @Test
    fun `should not validate positive when one of many conditions is false`() {
        conditionedValidator.validate(Tree(floatSize = 3.0f, name = "FirstName", nullableSize = null)).isValid.shouldEqual(true)
    }
}



