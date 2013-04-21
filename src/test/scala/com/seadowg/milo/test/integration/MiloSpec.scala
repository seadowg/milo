package com.seadowg.milo.test.integration

import org.specs2.mutable._
import org.specs2.mock._
import com.seadowg.milo.runtime._
import com.seadowg.milo.events._
import com.seadowg.milo.dsl.Async._
import com.seadowg.milo.test.helpers.AsyncHelpers._

class MiloSpec extends Specification with Mockito {
  "Milo" should {
    "work as designed" in {
      EventProcessor.start()
      var executed = false
      
      val event = new Event[Int]
      event.bind {
        v => executed = true
      }
      
      EventProcessor.process(event, 5)
      
      waitUntil(() => executed) mustEqual true
      executed = false
      
      event.bind {
        v => 
          async {
            v
          }.bind {
            v => executed = true
          }
      }
      
      event.trigger(5)
      
      waitUntil(() => executed) mustEqual true
    }
  }
}