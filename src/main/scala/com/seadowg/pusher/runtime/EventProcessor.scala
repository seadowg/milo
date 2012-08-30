package com.seadowg.pusher.runtime

import com.seadowg.pusher.events.Event

object EventProcessor {
  val worker = new Worker()
  
  def start() {
    worker.start()
  }
  
  def process[T](event: Event[T], value: T) {
    worker ! {
      () => event.trigger(value)
    }
  }
}