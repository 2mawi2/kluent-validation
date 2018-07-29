import java.math.BigDecimal
import kotlin.math.abs

fun compareNumbers(first: Number, second: Number): Double {
    val first = when (first) {
        is Int, is Short, is Long, is Double, is Float, is BigDecimal -> first.toDouble()
        else -> throw UnsupportedTypeError()
    }

    val second = when (second) {
        is Int, is Short, is Long, is Double, is Float, is BigDecimal -> second.toDouble()
        else -> throw UnsupportedTypeError()
    }

    return first - second
}

fun validate(property: Any?, comparable: Number, predicate: (Double) -> Boolean): Boolean {
    return when (property) {
        null -> false
        is Number -> predicate(compareNumbers(property, comparable))
        else -> throw UnsupportedTypeError("Type: ${(property.javaClass)} is not supported")
    }
}