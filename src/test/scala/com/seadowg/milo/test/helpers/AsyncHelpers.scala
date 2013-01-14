package com.seadowg.milo.test.helpers

import com.seadowg.milo.runtime.WorkerQueue

object AsyncHelpers {
	def waitUntil(condition: () => Boolean): Boolean = {
		var waited = 0
		var waiting = true

		while(waiting) {
			if (condition()) {
				waiting = false
			}
			
			else {
				if (waited == 200) {
					return false
				}
				
				else {
					waited += 1
					Thread.sleep(5)
				}
			}
		}
		
		true
	}
	
  class TestWorker extends WorkerQueue {
    def spawn() {}
    def send(message: () => Unit) {}
  }
}

