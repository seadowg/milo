package com.seadowg.milo.test.runtime

import org.specs2.mutable._
import org.specs2.mock._ 
import com.seadowg.milo.runtime._
import com.seadowg.milo.test.helpers.AsyncHelpers._
import com.seadowg.milo.events.Event

class EventProcessorSpec extends Specification with Mockito {
  "start" should {
    val mockWorker = mock[TestWorker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "start its worker" in {
      eventProcessor.start()
      there was one(mockWorker).spawn()
    }
  }
  
  "process" should {
    val mockWorker = mock[TestWorker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "send work to the worker" in {
      eventProcessor.process(new Event[Int], 5)
      there was one(mockWorker).send(any[() => Unit])
    }
  }
}