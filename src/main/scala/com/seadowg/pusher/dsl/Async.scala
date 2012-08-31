package com.seadowg.pusher.dsl

import scala.actors.Actor.actor
import com.seadowg.pusher.events.Event
import com.seadowg.pusher.events.EventStream
import com.seadowg.pusher.runtime.EventProcessor

object Async {
  def async[T](work: => T): EventStream[T] = {
    val event = new EventStream[T]
    actor {
      EventProcessor.process(event, work)
    }
    
    event
  }
}