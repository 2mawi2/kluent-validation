import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal


class CompareUtilsTests {

    class MyNumber : Number() {
        override fun toByte(): Byte = Byte.MAX_VALUE
        override fun toChar(): Char = '2'
        override fun toDouble(): Double = 1.2
        override fun toFloat(): Float = 1.3f
        override fun toInt(): Int = 1
        override fun toLong(): Long = 1.toLong()
        override fun toShort(): Short = 1.toShort()
    }

    @Test
    fun `should compare integer with float`() {
        compareNumbers(1, 5.0f).shouldEqual(-4.0)
    }

    @Test
    fun `should compare long with float`() {
        compareNumbers(1.toLong(), 5.0f).shouldEqual(-4.0)
    }

    @Test
    fun `should compare double with float`() {
        compareNumbers(1.5, 5.0f).shouldEqual(-3.5)
    }

    @Test
    fun `should compare float with float`() {
        compareNumbers(1.75f, 5.0f).shouldEqual(-3.25)
    }

    @Test
    fun `should compare big decimal with float`() {
        compareNumbers(1.75f.toBigDecimal(), 5.0f).shouldEqual(-3.25)
    }

    @Test
    fun `should compare short with float`() {
        compareNumbers(1.toShort(), 5.0f).shouldEqual(-4.0)
    }

    @Test
    fun `should throw error if type of first not supported`() {
        assertThrows<UnsupportedTypeError> { compareNumbers(MyNumber(), 5.0f) }
    }

    @Test
    fun `should throw error if type of second not supported`() {
        assertThrows<UnsupportedTypeError> { compareNumbers(5, MyNumber()) }
    }
}

