package com.seadowg.milo.runtime

import scala.actors.Actor.actor
import com.seadowg.milo.events.Event
import com.seadowg.milo.events.EventStream

object JobProcessor {
  def process[T](work: () => T): EventStream[T] = {
    val event = new Event[T] {
      actor {
        EventProcessor.process(this, work())
      }
    }
    
    new EventStream(event)
  }
}