interface ValidationRule<T> {
    var validator: PropertyValidator
    fun validate(entity: T): Boolean
}