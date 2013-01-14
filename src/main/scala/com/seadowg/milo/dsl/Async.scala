package com.seadowg.milo.dsl

import scala.concurrent._
import ExecutionContext.Implicits.global
import com.seadowg.milo.events._
import com.seadowg.milo.runtime.EventProcessor

object Async {
	def async[T](block: => T): EventStream[T] = {
		val event = new Event[T]
		
		future {
			block
		}.onSuccess {
			case value => EventProcessor.process(event, value)
		}
		
		new EventStream[T](event)
	}
}