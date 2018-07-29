interface Condition<T> {
    fun validate(entity: T): Boolean
}

class WhenTrueCondition<T>(private val condition: (T) -> Boolean) : Condition<T> {
    override fun validate(entity: T): Boolean = condition(entity)
}

class UnlessCondition<T>(private val condition: (T) -> Boolean) : Condition<T> {
    override fun validate(entity: T): Boolean = condition(entity).not()
}