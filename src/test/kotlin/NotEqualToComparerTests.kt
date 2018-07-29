import org.amshove.kluent.shouldEqual
import org.junit.Test

class NotEqualToComparerTests {
    val validator: TreeValidator = TreeValidator()

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size } notEqualTo 9
            validate { it.floatSize } `not equal to` 8.0
            validate { it.name }.notEqualTo("WrongName")
        }
    }

    @Test
    fun `should check for quality when not a number`() {
        validator.validate(Tree(name = "FirstName")).isValid.shouldEqual(false)
    }

    @Test
    fun `should not pass when not equal float`() {
        validator.validate(Tree(floatSize = 8.0F)).isValid.shouldEqual(false)
    }

    @Test
    fun `should fail when not equal float`() {
        validator.validate(Tree(floatSize = 3.0F)).isValid.shouldEqual(false)
    }

    @Test
    fun `should pass when not equal integer`() {
        validator.validate(Tree(size = 8)).isValid.shouldEqual(true)
    }

    @Test
    fun `should fail when equal integer`() {
        validator.validate(Tree(size = 9)).isValid.shouldEqual(false)
    }

    //TODO Extend for Equality with other Properties
}