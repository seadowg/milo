package com.seadowg.milo.runtime

import scala.actors.Actor
import scala.actors.Actor._
import com.seadowg.milo.events.Event

object EventProcessor {
  private val worker = new Worker()
  
  def start() {
    worker.start()
  }
  
  def process[T](event: Event[T], value: T) {
    worker ! {
      () => event.trigger(value)
    }
  }
  
  class Worker extends Actor {
    def act() {
      while (true) {
        receive {
          case work: (() => Unit) => reply(work())
        }
      }
    }
  }
}

