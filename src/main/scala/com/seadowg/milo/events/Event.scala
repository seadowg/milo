package com.seadowg.milo.events

import scala.collection.mutable.ArrayBuffer

class Event[T] {
  private val callbacks: ArrayBuffer[T => Unit] = new ArrayBuffer()
  
  def bind(func: T => Unit) {
    callbacks += func
  }
  
  def trigger(value: T) {
    callbacks.foreach {
      callback => callback(value)
    }
  }
}