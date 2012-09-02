package com.seadowg.pusher.runtime

import scala.actors.Actor.actor
import com.seadowg.pusher.events.Event
import com.seadowg.pusher.events.EventStream

object JobProcessor {
  def process[T](work: => T): EventStream[T] = {
    val event = new Event[T] {
      actor {
        EventProcessor.process(this, work)
      }
    }
    
    new EventStream(event)
  }
}