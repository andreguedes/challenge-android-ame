package br.com.andreguedes.alodjinha.helper

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class InternetHelperTest {

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var connectivityManager: ConnectivityManager

    @Mock
    private lateinit var networkInfo: NetworkInfo

    @Mock
    private lateinit var packageManager: PackageManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        `when`(context.packageManager).thenReturn(packageManager)
        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        `when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    @Test
    fun shouldReturnTrueWhenNetworkIsAvailable() {
        `when`(networkInfo.isConnected).thenReturn(true)

        InternetHelper.isNetworkAvailable(context)

        assertEquals(networkInfo.isConnected, true)
    }

    @Test
    fun shouldReturnFalseWhenNetworkIsAvailable() {
        `when`(networkInfo.isConnected).thenReturn(false)

        InternetHelper.isNetworkAvailable(context)

        assertEquals(networkInfo.isConnected, false)
    }

}