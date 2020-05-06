package co.tiagoaguiar.evernotekt

import org.mockito.ArgumentCaptor

open class BaseTest {
    open fun <T> captureArg(argCaptor: ArgumentCaptor<T>): T =
        argCaptor.capture()
}