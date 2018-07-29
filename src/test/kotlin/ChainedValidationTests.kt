import org.amshove.kluent.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource


class ChainedValidationTests {

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            validate { it.size } `greater than` 8 smallerThan 15
            validate { it.size } `not equal to` 9
        }
    }

    val validator = TreeValidator()

    @ParameterizedTest
    @ValueSource(ints = [10, 11, 12, 13, 14])
    fun `should pass when using mustBe`(value: Int) {
        validator.validate(Tree(size = value)).isValid.shouldBe(true)
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 8, 9, 15, 16])
    fun `should fail when one of the mustBe validation rules fail`(value: Int) {
        validator.validate(Tree(size = value)).isValid.shouldBe(false)
    }
}

