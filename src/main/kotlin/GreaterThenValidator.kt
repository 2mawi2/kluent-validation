class GreaterThenValidator(val num: Int) : PropertyValidator {
    override fun validate(property: Any): Boolean {
        when (property) {
            is Int -> return property > num
            else -> throw Exception()
        }
    }
}


