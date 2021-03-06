package com.seadowg.milo.test.helpers

object AsyncHelpers {
	def waitUntil(condition: () => Boolean): Boolean = {
		Stream.continually(waitAndReturn(5, condition())).take(200).takeWhile(!_).length != 200
	}
	
	private def waitAndReturn[T](time: Int, exp: => T): T = {
		Thread.sleep(time)
		exp
	}
}

