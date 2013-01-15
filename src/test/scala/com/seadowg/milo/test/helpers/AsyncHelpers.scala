package com.seadowg.milo.test.helpers

import com.seadowg.milo.runtime.Worker

object AsyncHelpers {
	def waitUntil(condition: () => Boolean): Boolean = {
		Stream.continually(waitAndReturn(5, condition())).take(200).takeWhile(!_).length != 200
	}
	
  class TestWorker extends Worker {
    def spawn() {}
    def send(message: () => Unit) {}
  }
	
	private def waitAndReturn[T](time: Int, exp: => T): T = {
		Thread.sleep(time)
		exp
	}
}

