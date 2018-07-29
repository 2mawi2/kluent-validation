class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun setValidator(validator: PropertyValidator): RuleBuilder<T, TProperty> {
        parent.completeRule(rule, validator)
        return this
    }

}

fun <T, TProperty> RuleBuilder<T, TProperty>.greaterThan(greater: Number): RuleBuilder<T, TProperty> {
    return setValidator(GreaterThanValidator(greater))
}

fun <T, TProperty> RuleBuilder<T, TProperty>.notEmpty(): RuleBuilder<T, TProperty> {
    return setValidator(NotEmptyValidator())
}

fun <T, TProperty> RuleBuilder<T, TProperty>.smallerThan(smaller: Number): RuleBuilder<T, TProperty> {
    return setValidator(SmallerThanValidator(smaller))
}

fun <T, TProperty> RuleBuilder<T, TProperty>.equalTo(equal: Any): RuleBuilder<T, TProperty> {
    return setValidator(EqualToValidator(equal))
}

fun <T, TProperty> RuleBuilder<T, TProperty>.notEqualTo(notEqual: Any): RuleBuilder<T, TProperty> {
    return setValidator(NotEqualToValidator(notEqual))
}

fun <T, TProperty> RuleBuilder<T, TProperty>.ruleSet(
        ruleSet: RuleBuilder<T, TProperty>.() -> RuleBuilder<T, TProperty>): RuleBuilder<T, TProperty> {
    ruleSet()
    return this
}