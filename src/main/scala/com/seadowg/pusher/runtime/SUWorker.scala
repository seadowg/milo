package com.seadowg.pusher.runtime

import scala.actors.Actor
import scala.actors.Actor._

class SUWorker[T](work: () => Unit) extends Actor {
  def act() {
    work()
  }
}
