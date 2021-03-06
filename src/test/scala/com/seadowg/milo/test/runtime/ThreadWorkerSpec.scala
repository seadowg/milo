package com.seadowg.milo.test.runtime

import org.specs2.mutable._
import org.specs2.mock._ 
import com.seadowg.milo.test.helpers.AsyncHelpers._
import com.seadowg.milo.runtime._

class ThreadWorkerSpec extends Specification with Mockito {
  "ThreadWorker".title
  
  "when sent work" should {
    "execute work it is sent" in {
      val worker = new ThreadWorker()
      var executed = false
    
      worker.spawn()
    
      worker.send(() => executed = true)
      waitUntil(() => executed) mustEqual true
      executed = false
    
      worker.send(() => executed = true)
      waitUntil(() => executed) mustEqual true
    }
  }
}

