package com.seadowg.milo.runtime

import scala.actors.Actor
import scala.actors.Actor._

trait WorkerQueue[T] {
  def spawn(): Unit
  def run(): Unit
  def send(message: Any): Unit
}

class ActorWorker extends WorkerQueue[Actor] with Actor {
	def act() = run()
	
	def spawn() {
		this.start()
	}
  
  def run() {
    var keepRunning = true
    
    while (keepRunning) {
      self.receive {
        case work: (() => Unit) => work()
        case -1 => keepRunning = false
        case _ => 0
      }
    }
  }
  
  def send(message: Any) {
    this.!(message)
  }
}