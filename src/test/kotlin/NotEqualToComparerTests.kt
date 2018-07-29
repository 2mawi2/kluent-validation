import org.amshove.kluent.shouldBe
import org.junit.Assert
import org.junit.Test

class NotEqualToComparerTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size } notEqualTo 9
            ruleFor { it.floatSize } `not equal to` 8.0
            ruleFor { it.name }.notEqualTo("WrongName")
        }
    }

    @Test
    fun `should check for quality when not a number`() {
        validator.validate(Tree(name = "FirstName")).isValid.shouldBe(false)
    }

    @Test
    fun `should not pass when not equal float`() {
        validator.validate(Tree(floatSize = 8.0F)).isValid.shouldBe(false)
    }

    @Test
    fun `should fail when not equal float`() {
        validator.validate(Tree(floatSize = 3.0F)).isValid.shouldBe(false)
    }

    @Test
    fun `should pass when not equal integer`() {
        validator.validate(Tree(size = 8)).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when equal integer`() {
        validator.validate(Tree(size = 9)).isValid.shouldBe(false)
    }

    //TODO Extend for Equality with other Properties
}