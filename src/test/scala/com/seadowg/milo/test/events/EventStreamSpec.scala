package com.seadowg.milo.test.events

import org.specs2.mutable._
import org.specs2.mock._
import com.seadowg.milo.events._


class EventStreamSpec extends Specification with Mockito {
  "EventStream" should {
    "bind(func)" should {
      "pass the function to the the stream's event.bind" in {
        val mockEvent = mock[Event[Int]]
        val stream = new EventStream(mockEvent)
        val func: Int => Unit = { i => i }

        stream.bind(func)
        there was one(mockEvent).bind(func)
      }
    }

    "map(func)" should {
      "return a stream with the function applied to every element of the original" in {
        var sent = 0
        val stream = new EventStream[Int]
        stream.map(value => value * 2).event.bind(value => sent = value)
        stream.event.trigger(1)

        sent mustEqual 2
      }
    }

    "filter(func)" should {
      "return a stream containing only the elements that pass the filter" in {
        var sent = 0
        val stream = new EventStream[Int]
        stream.filter(value => value < 2).event.bind(value => sent = value)
        stream.event.trigger(1)
        stream.event.trigger(2)

        sent mustEqual 1
      }
    }

    "always have an event" in {
      val stream1 = new EventStream(new Event[Int])
      stream1.event must_!= null

      val stream2 = new EventStream()
      stream2.event must_!= null
    }
  }
}
