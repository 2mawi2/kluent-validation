fun compareNumbers(first: Number, second: Number): Double {
    val first = when (first) {
        is Int, is Long -> first.toDouble()
        is Double, is Float -> first.toDouble()
        else -> throw UnsupportedTypeError()
    }

    val second = when (second) {
        is Int, is Long -> second.toDouble()
        is Double, is Float -> second.toDouble()
        else -> throw UnsupportedTypeError()
    }

    return first - second
}