class RuleBuilder<T, TProperty>(var rule: PropertyRule<T, TProperty>, var parent: AbstractValidator<T>) {
    val validators: ArrayList<PropertyValidator> = ArrayList()

    fun setValidator(validator: PropertyValidator): RuleBuilder<T, TProperty> {
        parent.completeRule(rule, validator)
        return this
    }

}


infix fun <T, TProperty> RuleBuilder<T, TProperty>.`equal to`(equal: Any) = equalTo(equal)
infix fun <T, TProperty> RuleBuilder<T, TProperty>.`not equal to`(equal: Any) = notEqualTo(equal)
infix fun <T, TProperty> RuleBuilder<T, TProperty>.`greater than`(greater: Number) = greaterThan(greater)
infix fun <T, TProperty> RuleBuilder<T, TProperty>.`smaller than`(smaller: Number) = smallerThan(smaller)


infix fun <T, TProperty> RuleBuilder<T, TProperty>.greaterThan(greater: Number): RuleBuilder<T, TProperty> {
    return setValidator(GreaterThanValidator(greater))
}

fun <T, TProperty> RuleBuilder<T, TProperty>.notEmpty(): RuleBuilder<T, TProperty> {
    return setValidator(NotEmptyValidator())
}

infix fun <T, TProperty> RuleBuilder<T, TProperty>.smallerThan(smaller: Number): RuleBuilder<T, TProperty> {
    return setValidator(SmallerThanValidator(smaller))
}

infix fun <T, TProperty> RuleBuilder<T, TProperty>.equalTo(equal: Any): RuleBuilder<T, TProperty> {
    return setValidator(EqualToValidator(equal))
}

infix fun <T, TProperty> RuleBuilder<T, TProperty>.notEqualTo(notEqual: Any): RuleBuilder<T, TProperty> {
    return setValidator(NotEqualToValidator(notEqual))
}

fun <T, TProperty> RuleBuilder<T, TProperty>.ruleSet(
        ruleSet: RuleBuilder<T, TProperty>.() -> RuleBuilder<T, TProperty>): RuleBuilder<T, TProperty> {
    ruleSet()
    return this
}