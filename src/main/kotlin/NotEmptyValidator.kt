class NotEmptyValidator : PropertyValidator {
    override fun validate(property: Any): Boolean {
        when (property) {
            is String -> return property.isNotBlank()
            is List<*> -> return property.isNotEmpty()
            else -> throw UnsupportedTypeError("Type: ${(property.javaClass)} is not supported")
        }
    }
}