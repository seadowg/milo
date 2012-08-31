package com.seadowg.pusher.events

class EventStream[T](val event: Event[T]) {
  def this() {
    this(new Event[T])
  }
  
  def bind(func: T => Unit) {
    this.event.bind(func)
  }
}