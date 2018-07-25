interface ValidationRule<T> {
    var validators: ArrayList<PropertyValidator>
    fun validate(entity: T): Boolean
}

class PropertyRule<T, TProperty>(private val expression: (T) -> TProperty) : ValidationRule<T> {
    override fun validate(entity: T): Boolean {
        val value = expression(entity) as? Any
        return validators.map { it.validate(value) }.all { it }
    }

    override var validators: ArrayList<PropertyValidator> = arrayListOf()
}