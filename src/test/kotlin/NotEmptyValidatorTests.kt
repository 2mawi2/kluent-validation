import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldBe
import org.junit.Test


class NotEmptyValidatorTests {
    @Test
    fun `should validate string to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer()).isValid.shouldBe(false)
        CustomerValidator().validate(Customer(firstName = "not empty")).isValid.shouldBe(true)
    }

    @Test
    fun `should fail when string is whitespace`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer(firstName = "  ")).isValid.shouldBe(false)
    }

    @Test
    fun `should fail when nullable property is null`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.nullableLastName }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer()).isValid.shouldBe(false)
    }

    @Test
    fun `should validate list to be empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.list }.notEmpty()
            }
        }

        CustomerValidator().validate(Customer()).isValid.shouldBe(false)
        CustomerValidator().validate(Customer(list = listOf(1, 2))).isValid.shouldBe(true)
    }
}