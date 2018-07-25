open class AbstractValidator<T> {
    val rules: ArrayList<ValidationRule<T>> = ArrayList()

    fun <TProperty> completeRule(rule: PropertyRule<T, TProperty>, validator: PropertyValidator) {
        rules.first { it == rule }.validators.add(validator)
    }

    protected fun <TProperty> ruleFor(expression: (T) -> TProperty): RuleBuilder<T, TProperty> {
        val rule = PropertyRule(expression)
        rules.add(rule)
        return RuleBuilder(rule, this)
    }

    fun validate(customer: T): ValidationResult {
        return ValidationResult(
                isValid = rules.map { it.validate(customer) }.all { it }
        )
    }
}

data class ValidationResult(val isValid: Boolean)