package com.seadowg.milo.runtime

import scala.actors.Actor
import scala.actors.Actor._

class Worker extends Actor {
  def act() {
    while (true) {
      receive {
        case work: (() => Unit) => reply(this.execute(work))
      }
    }
  }
  
  def execute(work: () => Unit) {
    work()
  }
}