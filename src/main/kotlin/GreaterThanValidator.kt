class GreaterThanValidator(private val greater: Number) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return Comparision.validate(property, greater) { it > 0 }
    }
}

class SmallerThanValidator(private val smaller: Number) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return Comparision.validate(property, smaller) { it < 0 }
    }
}

class EqualToValidator(private val equal: Any) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (equal) {
            is Number -> Comparision.validate(property, equal) { it == 0.0 }
            else -> equal == property
        }
    }
}

class NotEqualToValidator(private val notEqual: Any) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (notEqual) {
            is Number -> Comparision.validate(property, notEqual) { it != 0.0 }
            else -> notEqual != property
        }
    }
}

object Comparision {
    fun validate(property: Any?, comparable: Number, predicate: (Double) -> Boolean): Boolean {
        return when (property) {
            null -> false
            is Number -> predicate(compareNumbers(property, comparable))
            else -> throw UnsupportedTypeError("Type: ${(property.javaClass)} is not supported")
        }
    }
}




