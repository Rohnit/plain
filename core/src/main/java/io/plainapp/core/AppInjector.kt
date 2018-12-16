package io.plainapp.core

import okhttp3.logging.HttpLoggingInterceptor

/**
 * File providing different dependencies that are shared at the app level.
 *
 * Once we have a dependency injection framework or a service locator, this should be removed.
 */

val debugLevel = if (BuildConfig.DEBUG) {
    HttpLoggingInterceptor.Level.BODY
} else {
    HttpLoggingInterceptor.Level.NONE
}

@Deprecated("Use Dagger LoggingInterceptorModule instead")
val loggingInterceptor = HttpLoggingInterceptor().apply { level = debugLevel }