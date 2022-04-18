package io.github.fourlastor.keys.keylist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fourlastor.keys.id.LongId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class KeyListViewModel
@Inject
constructor() : ViewModel() {
  fun observeKeys(): Flow<List<OtpKey>> = flowOf(
    listOf(
      OtpKey(
        id = LongId("first".hashCode().toLong()),
        accountName = "first",
        issuer = "ACME Co",
        secret = "HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ",
        algorithm = OtpKey.Algorithm.SHA1,
        digits = 6,
        period = 30,
      ),
      OtpKey(
        id = LongId("second".hashCode().toLong()),
        accountName = "second",
        issuer = "ACME Co",
        secret = "HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ",
        algorithm = OtpKey.Algorithm.SHA1,
        digits = 6,
        period = 30,
      ),
      OtpKey(
        id = LongId("third".hashCode().toLong()),
        accountName = "third",
        issuer = "ACME Co",
        secret = "HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ",
        algorithm = OtpKey.Algorithm.SHA1,
        digits = 6,
        period = 30,
      ),
    )
  )
}
