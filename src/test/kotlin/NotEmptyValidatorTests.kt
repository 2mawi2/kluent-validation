import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class NotEmptyValidatorTests {
    data class Customer(
            val firstName: String = "",
            val nullableLastName: String? = null,
            val list: List<Int> = listOf()
    )

    @Test
    fun `should validate string to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer()))
        assertTrue(CustomerValidator().validate(Customer(firstName = "not empty")))
    }

    @Test
    fun `should fail when string is whitespace`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer(firstName = "  ")))
    }

    @Test
    fun `should fail when nullable property is null`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.nullableLastName }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer()))
    }

    @Test
    fun `should validate list to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.list }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer()))
        assertTrue(CustomerValidator().validate(Customer(list = listOf(1, 2))))
    }
}