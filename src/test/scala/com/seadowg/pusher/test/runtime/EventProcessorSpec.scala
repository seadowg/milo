package com.seadowg.pusher.test.runtime

import org.specs2.mutable._
import org.specs2.mock._ 
import com.seadowg.milo.runtime._
import com.seadowg.milo.events.Event

class EventProcessorSpec extends Specification with Mockito {
  "start" should {
    val mockWorker = mock[Worker]
    val eventProcessor = new EventProcessor(mockWorker)
    mockWorker.start() returns null
    
    "start its worker" in {
      eventProcessor.start()
      there was one(mockWorker).start()
    }
  }
  
  "process" should {
    val mockWorker = mock[Worker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "send work to the worker" in {
      eventProcessor.process(new Event[Int], 5)
      there was one(mockWorker).send(any[() => Unit])
    }
  }
}