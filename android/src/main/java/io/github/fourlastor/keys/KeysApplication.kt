package io.github.fourlastor.keys

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeysApplication : Application() {
  // non-empty body because Hilt otherwise crashes at runtime
}
