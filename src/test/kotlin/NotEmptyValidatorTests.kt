import org.junit.Test


class NotEmptyValidatorTests {
    data class Customer(
            val firstName: String,
            val lastName: String
    )


    @Test
    fun `should validate is empty`() {
        class CustomerValidator : AbstractValidator<Customer>() {
            init {
                ruleFor { it.firstName }.notEmpty()
            }
        }

        val customer = Customer(firstName = "not empty", lastName = "")
        val result = CustomerValidator().validate(customer)
        assert(result)
    }
}