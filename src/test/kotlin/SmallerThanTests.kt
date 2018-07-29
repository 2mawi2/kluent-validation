import org.amshove.kluent.shouldBe
import org.junit.Assert
import org.junit.Test

class SmallerThanTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size } smallerThan 10
            ruleFor { it.nullableSize } `smaller than` 10
            ruleFor { it.floatSize }.smallerThan(10)
        }
    }


    @Test
    fun `should fail when value greater than specified`() {
        validator.validate(Tree(size = 11)).isValid.shouldBe(false)
    }

    @Test
    fun `should fail when value equal to specified`() {
        validator.validate(Tree(size = 10)).isValid.shouldBe(false)
    }

    @Test
    fun `should pass when value smaller than specified`() {
        validator.validate(Tree(size = 7)).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when nullable property is null`() {
        validator.validate(Tree(nullableSize = null)).isValid.shouldBe(false)
    }

    @Test
    fun `should fail when float value is greater then int`() {
        validator.validate(Tree(floatSize = 10.111F)).isValid.shouldBe(false)
    }
}