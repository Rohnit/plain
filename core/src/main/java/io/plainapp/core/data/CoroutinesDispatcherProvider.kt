package io.plainapp.core.data

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Provide coroutines context.
 */
data class CoroutinesDispatcherProvider(
        val main: CoroutineDispatcher,
        val computation: CoroutineDispatcher,
        val io: CoroutineDispatcher
)