package com.seadowg.milo.runtime

import scala.actors.Actor
import scala.actors.Actor._

trait EventWorkerQueue[T] {
  def start(): T
  def run(): Unit
  def send(message: Any): Unit
}

class Worker extends EventWorkerQueue[Actor] with Actor {
  val act = run
  
  def run() {
    while (true) {
      receive {
        case work: (() => Unit) => reply(work())
      }
    }
  }
  
  def send(message: Any) {
    this.!(message)
  }
}