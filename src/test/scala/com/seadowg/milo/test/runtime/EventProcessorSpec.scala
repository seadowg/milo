package com.seadowg.milo.test.runtime

import org.specs2.mutable._
import org.specs2.mock._ 
import com.seadowg.milo.runtime._
import com.seadowg.milo.events.Event

class EventProcessorSpec extends Specification with Mockito {
  "EventProcessor".title
  
  "#start()" should {
    val mockWorker = mock[ThreadWorker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "start the worker" in {
      eventProcessor.start()
      there was one(mockWorker).spawn()
    }
  }
  
  "#process(event, value)" should {
    val mockWorker = mock[ThreadWorker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "send work to the worker" in {
      eventProcessor.process(new Event[Int], 5)
      there was one(mockWorker).send(any[() => Unit])
    }
  }
}