import org.amshove.kluent.shouldBe
import org.junit.Test

class RuleSetTests {
    class TreeValidator : AbstractValidator<Tree>() {
        init {
            ruleFor { it.size }.ruleSet {
                greaterThan(8)
                smallerThan(11)
            }.equalTo(9)
        }
    }

    val validator = TreeValidator()

    @Test
    fun `should pass when using ruleSet`() {
        val result = validator.validate(Tree(size = 9))
        result.isValid.shouldBe(true)
    }

    @Test
    fun `should fail when one of the ruleSet validation rules fail`() {
        val result = validator.validate(Tree(size = 10))
        result.isValid.shouldBe(false)
    }
}