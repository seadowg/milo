package com.seadowg.milo.runtime

import scala.actors.Actor
import scala.actors.Actor._

trait WorkerQueue[T] {
  def start(): T
  def run(): Unit
  def send(message: Any): Unit
}

class Worker extends WorkerQueue[Actor] with Actor {
  val act = run
  
  def run() {
    var keepRunning = true
    
    while (keepRunning) {
      self.receive {
        case work: (() => Unit) => reply(work())
        case -1 => keepRunning = false
        case _ => 0
      }
    }
  }
  
  def send(message: Any) {
    this.!(message)
  }
}