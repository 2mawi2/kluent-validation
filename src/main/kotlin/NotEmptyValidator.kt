class NotEmptyValidator : PropertyValidator {
    override fun validate(property: Any): Boolean {
        when (property) {
            is String -> return property.isNotBlank()
            else -> throw Exception()
        }
    }
}