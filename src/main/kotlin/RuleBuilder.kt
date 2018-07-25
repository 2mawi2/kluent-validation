class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun greaterThen(num: Int) {
        setValidator(GreaterThenValidator(num))
    }

    fun notEmpty() {
        setValidator(NotEmptyValidator())
    }

    //complete builder and set validators
    fun setValidator(validator: PropertyValidator) {
        parent.completeRule(rule, validator)
    }
}