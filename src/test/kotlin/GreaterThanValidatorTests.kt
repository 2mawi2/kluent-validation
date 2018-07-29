import org.amshove.kluent.shouldBe
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class GreaterThanValidatorTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.greaterThan(8)
            ruleFor { it.nullableSize }.greaterThan(8)
            ruleFor { it.floatSize }.greaterThan(8)
        }
    }

    @Test
    fun `should fail when value smaller than specified`() {
        validator.validate(Tree(size = 3)).isValid.shouldBe(false)
    }

    @Test
    fun `should fail when value equal to specified`() {
        validator.validate(Tree(size = 8)).isValid.shouldBe(false)
    }

    @Test
    fun `should pass when value greater than specified`() {
        validator.validate(Tree(size = 9)).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when nullable property is null`() {
        validator.validate(Tree(nullableSize = null)).isValid.shouldBe(false)
    }

    @Test
    fun `should fail when float value is smaller then int`() {
        validator.validate(Tree(floatSize = 7.999F)).isValid.shouldBe(false)
    }
}