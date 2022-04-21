package io.github.fourlastor.keys.data

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

interface Var<T> {
  fun observe(): Flow<T>

  suspend fun update(fn: Update<T>)

  companion object {
    fun <T> create(initialValue: T): Var<T> =
      ChannelVar(initialValue = initialValue)
  }
}

typealias Update<T> = (T) -> T

private class ChannelVar<T>(
  private val initialValue: T,
) : Var<T> {

  private val updates: Channel<Update<T>> = Channel(capacity = 0)
  private val state: Channel<T> = Channel(capacity = 1)

  @OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
  private val initialization =
    GlobalScope.async(Dispatchers.Unconfined, start = CoroutineStart.LAZY) {
      updates.consumeAsFlow()
        .scan(initialValue) { state, update -> update.invoke(state) }
        .collect { state.send(it) }
    }

  private val ensureInitialized by lazy {
    initialization.start()
  }

  override fun observe(): Flow<T> {
    ensureInitialized
    return state.receiveAsFlow()
  }

  override suspend fun update(fn: Update<T>) {
    ensureInitialized
    updates.send(fn)
  }
}
