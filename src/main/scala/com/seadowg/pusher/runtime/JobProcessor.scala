package com.seadowg.pusher.runtime

import scala.actors.Actor.actor
import com.seadowg.pusher.events.EventStream

private[pusher] object JobProcessor {
  def process[T](work: => T): EventStream[T] = {
    new EventStream[T] {
      actor {
        EventProcessor.process(this, work)
      }
    }
  }
}