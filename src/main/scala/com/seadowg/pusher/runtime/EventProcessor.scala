package com.seadowg.pusher.runtime

import scala.actors.Actor
import scala.actors.Actor._

object EventProcessor {
  def start() {
    Processor.start()
  }
  
  private[pusher] def add(work: () => Unit) {
    Processor ! work
  }
  
  private object Processor extends Actor {
    def act() {
      eventloop {
        case work: (() => Unit) => work()
      }
    }
  }
}
