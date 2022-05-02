package io.github.fourlastor.keys.keydetails

import io.github.fourlastor.keys.data.model.LongId
import io.github.fourlastor.keys.keylist.OtpKey

object KeyDetailsNavigation {
  const val ARG_ID = "id"
  const val ROUTE = "key/details/{$ARG_ID}"

  fun go(id: LongId<OtpKey>) = "key/details/${id.id}"
}
