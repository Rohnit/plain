package io.plainapp.core.dagger

import `in`.uncod.android.bypass.Bypass
import `in`.uncod.android.bypass.Markdown
import android.util.DisplayMetrics
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * Provide [Markdown] to this app's components.
 */
@Module
class MarkdownModule @Inject constructor(
        private val displayMetrics: DisplayMetrics,
        private val options: Bypass.Options = Bypass.Options()
){

    @Provides fun provideMarkDown(): Markdown = Bypass(displayMetrics, options)

}