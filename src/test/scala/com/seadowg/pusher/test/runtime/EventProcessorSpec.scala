package com.seadowg.pusher.test.runtime

import org.specs2.mutable._
import org.specs2.mock._ 
import com.seadowg.milo.runtime._
import com.seadowg.milo.events.Event

class EventProcessorSpec extends Specification with Mockito {
  "start" should {
    val mockWorker = mock[TestWorker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "start its worker" in {
      eventProcessor.start()
      there was one(mockWorker).start()
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
  
  "kill" should {
    val mockWorker = mock[TestWorker]
    val eventProcessor = new EventProcessor(mockWorker)
    
    "send a kill (-1) value to the worker" in {
      eventProcessor.kill()
      there was one(mockWorker).send(-1)
    }
  }
  
  class TestWorker extends WorkerQueue[Int] {
    def start() = 0
    def run() {}
    def send(message: Any) {}
  }
}