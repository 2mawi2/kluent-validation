data class Tree(
        val size: Int = 9,
        val nullableSize: Int? = 9,
        val floatSize: Float = 9.0F,
        val name: String = "FirstName"
)


data class Customer(
        val firstName: String = "",
        val nullableLastName: String? = null,
        val list: List<Int> = listOf()
)