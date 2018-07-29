import org.amshove.kluent.shouldEqual
import org.junit.Test

class GreaterThanValidatorTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size } greaterThan 8
            validate { it.nullableSize } `greater than` 8
            validate { it.floatSize }.greaterThan(8)
        }
    }

    @Test
    fun `should fail when value smaller than specified`() {
        validator.validate(Tree(size = 3)).isValid.shouldEqual(false)
    }

    @Test
    fun `should fail when value equal to specified`() {
        validator.validate(Tree(size = 8)).isValid.shouldEqual(false)
    }

    @Test
    fun `should pass when value greater than specified`() {
        validator.validate(Tree(size = 9)).isValid.shouldEqual(true)
    }

    @Test
    fun `should fail when nullable property is null`() {
        validator.validate(Tree(nullableSize = null)).isValid.shouldEqual(false)
    }

    @Test
    fun `should fail when float value is smaller then int`() {
        validator.validate(Tree(floatSize = 7.999F)).isValid.shouldEqual(false)
    }
}
