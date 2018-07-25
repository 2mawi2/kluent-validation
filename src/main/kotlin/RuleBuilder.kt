class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun setValidator(validator: PropertyValidator) {
        parent.completeRule(rule, validator)
    }


    fun greaterThan(num: Int) {
        setValidator(GreaterThanValidator(num))
    }

    fun notEmpty() {
        setValidator(NotEmptyValidator())
    }

    fun smallerThan(num: Int) {
        setValidator(SmallerThanValidator(num))
    }
}