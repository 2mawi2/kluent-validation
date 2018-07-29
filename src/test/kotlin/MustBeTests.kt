import org.amshove.kluent.shouldEqual
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MustBeTests {
    class TreeValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size }.mustBe {
                this `greater than` 8 //infix notation in mustBe only works with this in Kotlin
                smallerThan(15)
            } notEqualTo 9
        }
    }

    val validator = TreeValidator()

    @ParameterizedTest
    @ValueSource(ints = [10, 11, 12, 13, 14])
    fun `should pass when using mustBe`(value: Int) {
        validator.validate(Tree(size = value)).isValid.shouldEqual(true)
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 8, 9, 15, 16])
    fun `should fail when one of the mustBe validation rules fail`(value: Int) {
        validator.validate(Tree(size = value)).isValid.shouldEqual(false)
    }
}

