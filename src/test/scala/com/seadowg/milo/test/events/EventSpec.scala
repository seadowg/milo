package com.seadowg.milo.test.events

import org.specs2.mutable._
import com.seadowg.milo.events.Event

class EventSpec extends Specification {
  "Event".title
  
  "#bind(func)" should {
  	"returns the Event itself" in {
      val event = new Event[Int]
      val returned = event.bind {
        value => 5
      }
		
  		returned mustEqual event
  	}
  }
  
  "#trigger(value)" should {
    "executes callbacks using the passed value" in {
      var passed = 0
      val event = new Event[Int]
      event.bind {
        value => passed = value
      }
      event.trigger(1)
    
      passed mustEqual 1
    }
  }
}