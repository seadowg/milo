# milo

![](http://f.cl.ly/items/2u1n1c2A3S1e1r2X0R2n/pusher3.jpg)

## Warning

This readme represents the eventual goals of milo, not its current capabilities.

## Description

Inspired by Functional Reactive Programming and async frameworks such as node.js 'milo' combines declarative stream-like
events with asynchronous multithreaded programming. There are two key concepts in milo: `EventStream`s and `async` blocks.

### EventStream

An EventStream represents an infinite stream of values. We think of each value as occurring at a specific (but implicit) point in time.
EventStreams can have callbacks registered to them that are executed on each occurrence. For instance:

    val socket: EventStream[String] = new SocketEventStream(1337)
    socket.bind {
      message => println message
    }
    
EventStreams can also support standard stream operations. For instance we can `map` EventStreams:

    val loweredSocket = socket.map(string => string.toLower())
    
Or, `filter` them:
    
    val shortSocket = socket.filter(string => string.length < 5)
    
In many ways EventStreams can be treated in a similar manner to Backbone.js' Event but with extra functionality.

### 'async' Block

Most of the basic work in milo can be carried out using callbacks on EventStreams but sometimes you won't want to hold up
the callback with long running operations such as I/O. To deal with this you can use the `async` block:

    async {
      database.query("SELECT * FROM users WHERE age == 25")
    }
    
Here the this database operation would be executed asynchronously in a separate thread. Of course you will probably need access to the
returned value for some `async` blocks. To facilitate this an `aync` block returns an EventStream that occurs when the block has finished
executing with the returned value:

    async {
      database.query("SELECT * FROM users WHERE age == 25")
    }.bind {
      result => 
        result.foreach {
          row => println(row)
        }
    }
    
This allows EventStreams to be used in a similar manner to [Futures](http://docs.scala-lang.org/sips/pending/futures-promises.html)
but with more generalised semantics (Futures refer to a value that may not have been set yet where as an EventStream represents a 
stream of incoming values).
    
## Using

Here is a short example of a program written using milo and a SocketEventStream (not actually implemented in milo) that represents a unix socket
with an EventStream:

    val socket = new SocketEventStream(1337)
    socket.bind {
      message =>
        async {
          println(message)
        }
    }
    
    EventProcessor.start()
    
This program accepts `String` messages from a socket and prints them out (asynchronously). You'll notice that at the end of the program we do the following:

    EventProcessor.start()
    
This call starts milo's event loop that processes occurring Events and runs callbacks. This should always be the last line in your milo code.