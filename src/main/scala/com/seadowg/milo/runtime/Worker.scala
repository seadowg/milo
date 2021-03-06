package com.seadowg.milo.runtime

import scala.collection.mutable

trait Worker {
  def spawn()
  def send(message: () => Unit)
}

class ThreadWorker extends Worker {
	private val queue = new mutable.Queue[() => Unit]
	
	def spawn() {
		new Thread(new WorkerRunner(this)).start()
	}
  
  def send(message: () => Unit) {
		this.synchronized {
			this.queue += message
			this.notify()
		}
  }
	
	private def receive(): () => Unit = {
		this.synchronized {
			while (this.queue.length < 1) { this.wait() }
			this.queue.dequeue()
		}
	}
	
  private def run() {
    Stream.continually(this.receive()).foreach(work => work())
  }
	
	private class WorkerRunner(worker: ThreadWorker) extends Runnable {
		def run() {
			worker.run()
		}
	}
}