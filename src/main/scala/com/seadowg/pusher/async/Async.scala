package com.seadowg.pusher.async

import com.seadowg.pusher.runtime.SUWorker
import com.seadowg.pusher.events.EventSource

class Async[T](work: => T) extends EventSource[T] {
  new SUWorker(() => this.execute()).start()
  
  private def execute() {
    val result = work
    this.occur(result)
  }
}

object Async {
  def async[T](work: => T) {
    new Async(work)
  }
}