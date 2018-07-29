class GreaterThanValidator(private val greater: Number) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return validate(property, greater) { it > 0 }
    }
}

class SmallerThanValidator(private val smaller: Number) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return validate(property, smaller) { it < 0 }
    }
}

class EqualToValidator(private val equal: Any) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (equal) {
            is Number -> validate(property, equal) { it == 0.0 }
            else -> equal == property
        }
    }
}

class NotEqualToValidator(private val notEqual: Any) : PropertyValidator {
    override fun validate(property: Any?): Boolean {
        return when (notEqual) {
            is Number -> validate(property, notEqual) { it != 0.0 }
            else -> notEqual != property
        }
    }
}





