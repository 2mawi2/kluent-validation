class NotEmptyValidator : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (property) {
            null -> false
            is String -> property.isNotBlank()
            is List<*> -> property.isNotEmpty()
            else -> throw UnsupportedTypeError("Type: ${(property.javaClass)} is not supported")
        }
    }
}