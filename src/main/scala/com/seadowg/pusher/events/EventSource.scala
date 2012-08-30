package com.seadowg.pusher.events

import com.seadowg.pusher.runtime.EventProcessor

class EventSource[T] extends EventStream[T] {
  def occur(value: T) {
    EventProcessor.process(this, value)
  }
}