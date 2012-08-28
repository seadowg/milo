package com.seadowg.pusher.test

import org.specs2.mutable._
import com.seadowg.pusher.events.Event

class EventSpec extends Specification {
  "Event" should {
    "not execute callbacks without trigger" in {
      var executed = false
      val event = new Event[Int] {
        this.bind {
          value => executed = true
        }
      }
      
      executed must beFalse
    }
    
    "execute callbacks on trigger" in {
      var executed = false
      val event = new Event[Int] {
        this.bind {
          value => executed = true
        }
        
        this.trigger(0)
      }
      
      executed must beTrue
    }
    
    "passes the correct value to callbacks" in {
      var passed = 0
      val event = new Event[Int] {
        this.bind {
          value => passed = value
        }
        
        this.trigger(1)
      }
      
      passed mustEqual 1
    }
  }
}