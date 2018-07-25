class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun setValidator(validator: PropertyValidator) {
        parent.completeRule(rule, validator)
    }

    fun greaterThan(greater: Number) = setValidator(GreaterThanValidator(greater))

    fun notEmpty() = setValidator(NotEmptyValidator())

    fun smallerThan(smaller: Number) = setValidator(SmallerThanValidator(smaller))

    fun equalTo(equal: Any) = setValidator(EqualToValidator(equal))

    fun notEqualTo(notEqual: Any) = setValidator(NotEqualToValidator(notEqual))
}