package com.seadowg.pusher.runtime

import scala.actors.Actor
import scala.actors.Actor._

object EventProcessor {
  
}

class Worker extends Actor {
  def act() {
    eventloop {
      case work: (() => Unit) => reply(work())
    }
  }
}
