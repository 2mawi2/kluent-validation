import org.amshove.kluent.shouldEqual
import org.junit.Test

class SmallerThanTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size } smallerThan 10
            validate { it.nullableSize } `smaller than` 10
            validate { it.floatSize }.smallerThan(10)
        }
    }


    @Test
    fun `should fail when value greater than specified`() {
        validator.validate(Tree(size = 11)).isValid.shouldEqual(false)
    }

    @Test
    fun `should fail when value equal to specified`() {
        validator.validate(Tree(size = 10)).isValid.shouldEqual(false)
    }

    @Test
    fun `should pass when value smaller than specified`() {
        validator.validate(Tree(size = 7)).isValid.shouldEqual(true)
    }

    @Test
    fun `should fail when nullable property is null`() {
        validator.validate(Tree(nullableSize = null)).isValid.shouldEqual(false)
    }

    @Test
    fun `should fail when float value is greater then int`() {
        validator.validate(Tree(floatSize = 10.111F)).isValid.shouldEqual(false)
    }
}