package ch2

import ch2.Main._
import org.scalatest.FlatSpec

class MainTest extends FlatSpec {
  behavior of "A fib"

  it should "return None for negative indices" in {
    assert(fib(-1).isEmpty)
    assert(fib(-42).isEmpty)
  }

  it should "return the correct values for positive indices" in {
    assert(fib(0).contains(0))
    assert(fib(1).contains(1))
    assert(fib(2).contains(1))
    assert(fib(3).contains(2))
    assert(fib(4).contains(3))
    assert(fib(5).contains(5))
    assert(fib(6).contains(8))
    assert(fib(7).contains(13))
  }
}
