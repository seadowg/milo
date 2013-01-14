package com.seadowg.pusher.test.runtime

import org.specs2.mutable._
import org.specs2.mock._ 
import com.seadowg.milo.runtime._
import com.seadowg.milo.events.Event

class ActorWorkerSpec extends Specification with Mockito {
  "ActorWorker" should {
		"execute work it is sent" in {
			val worker = new ActorWorker()
			var executed = false
			
			worker.spawn()
			worker.send(() => executed = true)
			
			waitUntil(() => executed)
			worker.send(-1)
		}
	}
	
	def waitUntil(condition: () => Boolean) = {
		var waiting = true
		var waited = 0

		while(waiting) {
			if (condition()) {
				waiting = false
			}
			
			else {
				if (waited == 200) {
					condition() mustEqual true
				}
				
				waited += 1
				Thread.sleep(5)
			}
		}
		
		condition() mustEqual true
	}
}

