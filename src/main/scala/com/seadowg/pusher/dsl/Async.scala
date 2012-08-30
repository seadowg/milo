package com.seadowg.pusher.dsl

import scala.actors.Actor.actor
import com.seadowg.pusher.events.Event
import com.seadowg.pusher.runtime.EventProcessor

object Async {
  def async[T](work: => T) {
    actor {
      new Event[T] {
        EventProcessor.process(this, work)
      }
    }
  }
}