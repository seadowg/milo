package com.seadowg.milo.dsl

import scala.actors.Actor.actor
import com.seadowg.milo.events._
import com.seadowg.milo.runtime.EventProcessor

object Async {
  def async[T](work: => T): EventStream[T] = {
    val event = new Event[T] {
      actor {
        EventProcessor.process(this, work)
      }
    }
    
    new EventStream(event)
  }
}