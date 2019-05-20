package br.com.andreguedes.alodjinha.helper

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class StringHelperTest {

    @Test
    fun shouldReturnTrueDoubleInCurrencyValue() {
        val doubleValue = 9.32
        val currencyValue = StringHelper.formatCurrencyNew(doubleValue)

        assertEquals("R$ 9,32", currencyValue)
    }

    @Test
    fun shouldReturnFalseDoubleInCurrencyValue() {
        val doubleValue = 9.32
        val currencyValue = StringHelper.formatCurrencyNew(doubleValue)

        assertNotEquals("R$ 9.32", currencyValue)
    }

}