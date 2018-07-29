import org.amshove.kluent.shouldBe
import org.junit.Test


class ConditionValidationTests {

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.greaterThan(4).whenTrue { it.name == "FirstName" }
            ruleFor { it.floatSize }.greaterThan(4).whenTrue { it.name == "FirstName" }.whenTrue { it.nullableSize != null }
        }
    }

    val validator = TreeValidator()

    @Test
    fun `should validate when condition is true`() {
        validator.validate(Tree(size = 3, name = "FirstName")).isValid.shouldBe(false)
    }

    @Test
    fun `should not validate when condition is false`() {
        validator.validate(Tree(size = 3, name = "FalseCondition")).isValid.shouldBe(true)
    }

    @Test
    fun `should validate when all conditions are true`() {
        validator.validate(Tree(floatSize = 3.0f, name = "FirstName", nullableSize = 3)).isValid.shouldBe(false)
    }

    @Test
    fun `should not validate when one of many conditions is false`() {
        validator.validate(Tree(floatSize = 3.0f, name = "FirstName", nullableSize = null)).isValid.shouldBe(true)
    }
}

