package com.seadowg.pusher.test.runtime

import org.specs2.mutable._
import com.seadowg.milo.runtime.EventProcessor

class WorkerSpec extends Specification {
  "Worker" should {
    "execute sent work" in {
      val worker = new EventProcessor.Worker()
      var executed = false
      worker.start()
      
      worker !? {
        () => executed = true
      }
      
      executed must beTrue
    }
    
    "should accept more than one message" in {
      val worker = new EventProcessor.Worker()
      var executed = 0
      worker.start()
      
      val func: () => Unit = {
        () => executed += 1
      }
      
      worker !? func
      worker !? func
      
      executed mustEqual 2
    }
  }
}