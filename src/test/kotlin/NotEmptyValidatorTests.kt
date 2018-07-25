import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class NotEmptyValidatorTests {


    @Test
    fun `should validate string to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer()).isValid)
        assertTrue(CustomerValidator().validate(Customer(firstName = "not empty")).isValid)
    }

    @Test
    fun `should fail when string is whitespace`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer(firstName = "  ")).isValid)
    }

    @Test
    fun `should fail when nullable property is null`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.nullableLastName }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer()).isValid)
    }

    @Test
    fun `should validate list to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.list }.notEmpty()
            }
        }

        assertFalse(CustomerValidator().validate(Customer()).isValid)
        assertTrue(CustomerValidator().validate(Customer(list = listOf(1, 2))).isValid)
    }
}