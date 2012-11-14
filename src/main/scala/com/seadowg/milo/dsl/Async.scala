package com.seadowg.milo.dsl

import scala.actors.Actor.actor
import com.seadowg.milo.events.EventStream
import com.seadowg.milo.runtime.JobProcessor

object Async {
  def async[T](work: => T): EventStream[T] = {
    JobProcessor.process(() => work)
  }
}