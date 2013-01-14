package com.seadowg.milo.runtime

import scala.collection.mutable.Queue

trait WorkerQueue {
  def spawn(): Unit
  def send(message: () => Unit): Unit
}

class ThreadWorker extends WorkerQueue {
	private val queue = new Queue[() => Unit]
	
	def spawn() {
		val runner = this
		new Thread(new Runnable() {
			def run() {
				runner.run()
			}
		}).start()
	}
  
  def send(message: () => Unit) {
		this.synchronized {
			this.queue += message
		}
  }
	
  private def run() {
    while (true) {
      this.receive().foreach(work => work())
    }
  }
	
	private def receive(): Option[() => Unit] = {
		this.synchronized {
			if (this.queue.length > 0) { 
				Some(this.queue.dequeue())
			}
			
			else {
				None
			}
		}
	}
}