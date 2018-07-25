import junit.framework.Assert
import org.junit.Test

class GreaterThenValidatorTests {
    data class Tree(
            val size: Int,
            val treeName: String
    )


    @Test
    fun `should validate generic types`() {
        class TreeValidator : AbstractValidator<Tree>() {
            init {
                ruleFor { it.treeName }.notEmpty()
            }
        }
        Assert.assertFalse(TreeValidator().validate(Tree(size = 3, treeName = "")))
    }

    @Test
    fun `should validate generic property types`() {
        class TreeValidator : AbstractValidator<Tree>() {
            init {
                ruleFor { it.size }.greaterThen(0)
            }
        }
        Assert.assertTrue(TreeValidator().validate(Tree(size = 3, treeName = "")))
    }
}