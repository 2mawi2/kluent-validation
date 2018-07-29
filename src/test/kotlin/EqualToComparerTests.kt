import org.amshove.kluent.shouldBe
import org.junit.Assert
import org.junit.Test

class EqualToComparerTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size } equalTo 9
            ruleFor { it.floatSize } `equal to` (9.0)
            ruleFor { it.name }.equalTo("FirstName")
        }
    }

    @Test
    fun `should check for equality when not a number`() {
        validator.validate(Tree(name = "WrongName")).isValid.shouldBe(false)
    }

    @Test
    fun `should pass when equal float`() {
        validator.validate(Tree(floatSize = 9.0F)).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when not equal float`() {
        validator.validate(Tree(floatSize = 3.0F)).isValid.shouldBe(false)
    }

    @Test
    fun `should pass when equal integer`() {
        validator.validate(Tree(size = 9)).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when not equal integer`() {
        validator.validate(Tree(size = 3)).isValid.shouldBe(false)
    }

    //TODO Extend for Equality with other Properties
}
