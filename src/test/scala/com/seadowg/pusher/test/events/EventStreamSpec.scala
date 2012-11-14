package com.seadowg.milo.test.events

import org.specs2.mutable._
import com.seadowg.milo.events._


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
    
    "sends correct values to mapped EventStream events" in {
      var sent = 0
      val stream = new EventStream[Int]
      stream.map(value => value * 2).event.bind(value => sent = value)
      stream.event.trigger(1)
      
      sent mustEqual 2
    }
    
    "sends only passing values to a filtered EventStream event" in {
      var sent = 0
      val stream = new EventStream[Int]
      stream.filter(value => value > 1).event.bind(value => sent += value)
      stream.event.trigger(1)
      stream.event.trigger(2)
      
      sent mustEqual 2
    }
  }
}