import org.junit.Assert
import org.junit.Test

class SmallerThanTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.smallerThan(10)
            ruleFor { it.nullableSize }.smallerThan(10)
            ruleFor { it.floatSize }.smallerThan(10)
        }
    }


    @Test
    fun `should fail when value greater than specified`() {
        val isValid = validator.validate(Tree(size = 11)).isValid
        Assert.assertFalse(isValid)
    }

    @Test
    fun `should fail when value equal to specified`() {
        val isValid = validator.validate(Tree(size = 10)).isValid
        Assert.assertFalse(isValid)
    }

    @Test
    fun `should pass when value smaller than specified`() {
        val isValid = validator.validate(Tree(size = 7)).isValid
        Assert.assertTrue(isValid)
    }

    @Test
    fun `should fail when nullable property is null`() {
        val isValid = validator.validate(Tree(nullableSize = null)).isValid
        Assert.assertFalse(isValid)
    }

    @Test
    fun `should fail when float value is greater then int`() {
        val isValid = validator.validate(Tree(floatSize = 10.111F)).isValid
        Assert.assertFalse(isValid)
    }
}