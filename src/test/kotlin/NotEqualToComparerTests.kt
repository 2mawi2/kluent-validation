import org.junit.Assert
import org.junit.Test

class NotEqualToComparerTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.notEqualTo(9)
            ruleFor { it.floatSize }.notEqualTo(8.0)
            ruleFor { it.name }.notEqualTo("WrongName")
        }
    }

    @Test
    fun `should check for quality when not a number`() {
        Assert.assertFalse(validator.validate(Tree(name = "FirstName")).isValid)
    }

    @Test
    fun `should not pass when not equal float`() {
        Assert.assertFalse(validator.validate(Tree(floatSize = 8.0F)).isValid)
    }

    @Test
    fun `should fail when not equal float`() {
        Assert.assertFalse(validator.validate(Tree(floatSize = 3.0F)).isValid)
    }

    @Test
    fun `should pass when not equal integer`() {
        Assert.assertTrue(validator.validate(Tree(size = 8)).isValid)
    }

    @Test
    fun `should fail when equal integer`() {
        Assert.assertFalse(validator.validate(Tree(size = 9)).isValid)
    }

    //TODO Extend for Equality with other Properties
}