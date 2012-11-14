package com.seadowg.milo.events

class EventStream[T](val event: Event[T]) {
  def this() {
    this(new Event[T])
  }
  
  def bind(func: T => Unit) {
    this.event.bind(func)
  }
  
  def map[U](func: T => U): EventStream[U] = {
    val event = new Event[U]
    this.bind(value => event.trigger(func(value)))
    new EventStream(event)
  }
  
  def filter(func: T => Boolean): EventStream[T] = {
    val event = new Event[T]
    this.bind {
      value =>
        if (func(value)) {
          event.trigger(value)
        }
    }
    
    new EventStream(event)
  }
}