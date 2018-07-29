import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

class EqualToComparerTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size } equalTo 9
            validate { it.floatSize } `equal to` (9.0)
            validate { it.name }.equalTo("FirstName")
        }
    }

    @Test
    fun `should check for equality when not a number`() {
        validator.validate(Tree(name = "WrongName")).isValid.shouldEqual(false)
    }

    @Test
    fun `should pass when equal float`() {
        validator.validate(Tree(floatSize = 9.0F)).isValid.shouldEqual(true)
    }

    @Test
    fun `should fail when not equal float`() {
        validator.validate(Tree(floatSize = 3.0F)).isValid.shouldEqual(false)
    }

    @Test
    fun `should pass when equal integer`() {
        validator.validate(Tree(size = 9)).isValid.shouldEqual(true)
    }

    @Test
    fun `should fail when not equal integer`() {
        validator.validate(Tree(size = 3)).isValid.shouldEqual(false)
    }

    //TODO Extend for Equality with other Properties
}
