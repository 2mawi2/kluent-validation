import org.junit.Assert
import org.junit.Test

class EqualToComparerTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.equalTo(9)
            ruleFor { it.floatSize }.equalTo(9.0)
            ruleFor { it.name }.equalTo("FirstName")
        }
    }

    @Test
    fun `should check for equality when not a number`() {
        Assert.assertFalse(validator.validate(Tree(name = "WrongName")).isValid)
    }

    @Test
    fun `should pass when equal float`() {
        Assert.assertTrue(validator.validate(Tree(floatSize = 9.0F)).isValid)
    }

    @Test
    fun `should fail when not equal float`() {
        Assert.assertFalse(validator.validate(Tree(floatSize = 3.0F)).isValid)
    }

    @Test
    fun `should pass when equal integer`() {
        Assert.assertTrue(validator.validate(Tree(size = 9)).isValid)
    }

    @Test
    fun `should fail when not equal integer`() {
        Assert.assertFalse(validator.validate(Tree(size = 3)).isValid)
    }

    //TODO Extend for Equality with other Properties
}