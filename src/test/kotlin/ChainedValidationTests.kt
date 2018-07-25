import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
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
        val result = validator.validate(Tree(size = 9))
        assertTrue(result.isValid)
    }

    @Test
    fun `should fail when if one of the validation rules fail`() {
        val result = validator.validate(Tree(size = 10))
        assertFalse(result.isValid)
    }
}