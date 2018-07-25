class GreaterThanValidator(val num: Int) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (property) {
            null -> false
            is Float -> property > num.toFloat()
            is Int -> property > num
            is List<*> -> property.isNotEmpty()
            else -> throw UnsupportedTypeError("Type: ${(property.javaClass)} is not supported")
        }
    }
}

class SmallerThanValidator(val num: Int) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (property) {
            null -> false
            is Float -> property < num.toFloat()
            is Int -> property < num
            is List<*> -> property.isNotEmpty()
            else -> throw UnsupportedTypeError("Type: ${(property.javaClass)} is not supported")
        }
    }
}




