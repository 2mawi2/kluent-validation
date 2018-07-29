open class AbstractValidator<T> {
    val rules: ArrayList<ValidationRule<T>> = ArrayList()
    val conditions: ArrayList<Condition<T>> = ArrayList()

    fun <TProperty> setValidator(rule: PropertyRule<T, TProperty>, validator: PropertyValidator) {
        rules.first { it == rule }.validators.add(validator)
    }

    fun addCondition(condition: Condition<T>) {
        conditions.add(condition)
    }

    protected fun <TProperty> validate(expression: (T) -> TProperty): RuleBuilder<T, TProperty> {
        val rule = PropertyRule(expression)
        rules.add(rule)
        return RuleBuilder(rule, this)
    }

    fun validate(entity: T): ValidationResult {
        var isValid = true

        if (conditions.isEmpty() || conditions.map { it.validate(entity) }.all { it }) {
            isValid = rules.map { it.validate(entity) }.all { it }
        }

        return ValidationResult(
                isValid = isValid
        )
    }
}

data class ValidationResult(val isValid: Boolean)