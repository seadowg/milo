package com.seadowg.milo.runtime

import scala.collection.mutable.Queue

trait Worker {
  def spawn(): Unit
  def send(message: () => Unit): Unit
}

class ThreadWorker extends Worker {
	private val queue = new Queue[() => Unit]
	
	def spawn() {
		new Thread(new WorkerRunner(this)).start()
	}
  
  def send(message: () => Unit) {
		this.synchronized {
			this.queue += message
			this.notify()
		}
  }
	
  private def run() {
    Stream.continually(this.receive()).foreach(work => work())
  }
	
	private def receive(): () => Unit = {
		this.synchronized {
			if (this.queue.length < 1) this.wait()
			this.queue.dequeue()
		}
	}
	
	private class WorkerRunner(worker: ThreadWorker) extends Runnable {
		def run() {
			worker.run()
		}
	}
}