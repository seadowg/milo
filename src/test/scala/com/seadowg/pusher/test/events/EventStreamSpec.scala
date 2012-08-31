package com.seadowg.pusher.test.events

import org.specs2.mutable._
import com.seadowg.pusher.events._


class EventStreamSpec extends Specification {
  "EventStream" should {
    "always have an event" in {
      val stream1 = new EventStream(new Event[Int])
      stream1.event must_!= null
      
      val stream2 = new EventStream()
      stream2.event must_!= null
    }
    
    "should pass binded functions to its Event" in {
      var executed = false
      val stream = new EventStream[Boolean]
      stream.bind {
        value => executed = value
      }
      
      stream.event.trigger(true)
      executed must beTrue
    }
  }
}