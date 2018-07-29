import org.amshove.kluent.shouldBe
import org.junit.Test


class ChainedValidationTests {

    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.greaterThan(8).smallerThan(11)
            ruleFor { it.size }.equalTo(9)
        }
    }

    val validator = TreeValidator()

    @Test
    fun `should pass when combined chained result is true`() {
        validator.validate(Tree(size = 9)).shouldBe(true)
    }

    @Test
    fun `should fail when one of the validation rules fail`() {
        validator.validate(Tree(size = 10)).shouldBe(false)
    }
}

