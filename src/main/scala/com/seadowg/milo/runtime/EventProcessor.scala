package com.seadowg.milo.runtime

import com.seadowg.milo.events.Event

class EventProcessor(private val worker: Worker) {  
  def start() {
    worker.start()
  }
  
  def process[T](event: Event[T], value: T) {
    worker ! {
      () => event.trigger(value)
    }
  }
}

object EventProcessor {
  private var processor: EventProcessor = null
  
  def start() {
    if (this.processor == null) {
       this.processor = new EventProcessor(new Worker())
       this.processor.start()
    }
    
    else {
      this.processor.start()
    }
  }
  
  def process[T](event: Event[T], value: T) {
    this.processor.process(event, value)
  }
}