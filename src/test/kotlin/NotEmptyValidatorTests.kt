import org.amshove.kluent.shouldEqual
import org.junit.Test


class NotEmptyValidatorTests {
    @Test
    fun `should validate string to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                validate { it.firstName }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer()).isValid.shouldEqual(false)
        CustomerValidator().validate(Customer(firstName = "not empty")).isValid.shouldEqual(true)
    }

    @Test
    fun `should fail when string is whitespace`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                validate { it.firstName }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer(firstName = "  ")).isValid.shouldEqual(false)
    }

    @Test
    fun `should fail when nullable property is null`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                validate { it.nullableLastName }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer()).isValid.shouldEqual(false)
    }

    @Test
    fun `should validate list to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                validate { it.list }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer()).isValid.shouldEqual(false)
        CustomerValidator().validate(Customer(list = listOf(1, 2))).isValid.shouldEqual(true)
    }
}