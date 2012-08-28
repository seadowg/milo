package com.seadowg.pusher.events

import com.seadowg.pusher.runtime.EventProcessor

class EventSource[T] extends EventStream[T] {
  protected def occur(value: T) {
    EventProcessor.add {
      () => this.trigger(value)
    }
  }
}