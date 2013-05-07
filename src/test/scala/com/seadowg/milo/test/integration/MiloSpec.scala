package com.seadowg.milo.test.integration

import org.specs2.mutable._
import org.specs2.mock._
import com.seadowg.milo.runtime._
import com.seadowg.milo.events._
import com.seadowg.milo.dsl.Async._
import com.seadowg.milo.test.helpers.AsyncHelpers._

class MiloSpec extends Specification with Mockito {
  "Milo".title
  
  "when used" should {
    "function as expected" in {
      EventProcessor.start()
      var done = false
      var passed = "nothing"
      var event = new Event[Int]

      new EventStream(event).map(_.toString).filter(_ != "123").bind {
        v => 
          async {
            v.reverse
          }.bind {
            v => 
              passed = v
              done = true
          }
      }
    
      EventProcessor.process(event, 123)
      EventProcessor.process(event, 567)
      
      waitUntil(() => done) mustEqual true
      passed mustEqual "765"
    }
  }
}