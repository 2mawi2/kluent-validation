import org.junit.Assert
import org.junit.Test

class SmallerThanTests {
    data class Tree(
            val size: Int = 7,
            val nullableSize: Int? = 7,
            val floatSize: Float = 7.0F
    )

    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.smallerThan(8)
            ruleFor { it.nullableSize }.smallerThan(8)
            ruleFor { it.floatSize }.smallerThan(8)
        }
    }


    @Test
    fun `should fail when value greater than specified`() {
        val isValid = validator.validate(Tree(size = 9)).isValid
        Assert.assertFalse(isValid)
    }

    @Test
    fun `should fail when value equal to specified`() {
        val isValid = validator.validate(Tree(size = 8)).isValid
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
        val isValid = validator.validate(Tree(floatSize = 8.111F)).isValid
        Assert.assertFalse(isValid)
    }
}