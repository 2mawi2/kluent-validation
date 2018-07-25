class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun greaterThan(num: Int) {
        setValidator(GreaterThanValidator(num))
    }

    fun notEmpty() {
        setValidator(NotEmptyValidator())
    }

    //complete builder and set validators
    fun setValidator(validator: PropertyValidator) {
        parent.completeRule(rule, validator)
    }
}