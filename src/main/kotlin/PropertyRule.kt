class PropertyRule<T, TProperty>(val expression: (T) -> TProperty) : ValidationRule<T> {
    override fun validate(entity: T): Boolean {
        return validator.validate(expression(entity) as Any)
    }

    override lateinit var validator: PropertyValidator
}