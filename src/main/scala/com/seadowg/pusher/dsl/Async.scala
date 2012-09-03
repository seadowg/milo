package com.seadowg.pusher.dsl

import scala.actors.Actor.actor
import com.seadowg.pusher.events.EventStream
import com.seadowg.pusher.runtime.JobProcessor

object Async {
  def async[T](work: => T): EventStream[T] = {
    JobProcessor.process(() => work)
  }
}