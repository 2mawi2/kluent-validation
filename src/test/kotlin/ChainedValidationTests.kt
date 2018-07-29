import org.amshove.kluent.shouldBe
import org.junit.Test


class ChainedValidationTests {

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size } `greater than` 8 smallerThan 11
            ruleFor { it.size } `equal to` 9
        }
    }

    val validator = TreeValidator()

    @Test
    fun `should pass when combined chained result is true`() {
        validator.validate(Tree(size = 9)).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when one of the validation rules fail`() {
        validator.validate(Tree(size = 10)).isValid.shouldBe(false)
    }
}

