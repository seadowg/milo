package com.seadowg.milo.runtime

import scala.actors.Actor
import scala.actors.Actor._

trait EventWorkerQueue[T] {
  def start(): T
  def act(): Unit
  def !(message: Any): Unit
  def !?(message: Any): Any
}

class Worker extends EventWorkerQueue[Actor] with Actor {
  def act() {
    while (true) {
      receive {
        case work: (() => Unit) => reply(work())
      }
    }
  }
}