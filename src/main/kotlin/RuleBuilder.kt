class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun setValidator(validator: PropertyValidator): RuleBuilder<T, TProperty> {
        parent.completeRule(rule, validator)
        return this
    }

    fun greaterThan(greater: Number): RuleBuilder<T, TProperty> {
        return setValidator(GreaterThanValidator(greater))
    }

    fun notEmpty(): RuleBuilder<T, TProperty> {
        return setValidator(NotEmptyValidator())
    }

    fun smallerThan(smaller: Number): RuleBuilder<T, TProperty> {
        return setValidator(SmallerThanValidator(smaller))
    }

    fun equalTo(equal: Any): RuleBuilder<T, TProperty> {
        return setValidator(EqualToValidator(equal))
    }

    fun notEqualTo(notEqual: Any): RuleBuilder<T, TProperty> {
        return setValidator(NotEqualToValidator(notEqual))
    }
}