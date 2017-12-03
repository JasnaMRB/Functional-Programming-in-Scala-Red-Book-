package ch2

import ch2.Main._
import org.scalatest.FlatSpec

class MainTest extends FlatSpec {
  behavior of "A fib"

  it should "return 0 for negative indices" in {
    assert(fib(-1) == 0)
    assert(fib(-42) == 0)
  }

  it should "return the correct values for positive indices" in {
    assert(fib(0) == 0)
    assert(fib(1) == 1)
    assert(fib(2) == 1)
    assert(fib(3) == 2)
    assert(fib(4) == 3)
    assert(fib(5) == 5)
    assert(fib(6) == 8)
    assert(fib(7) == 13)
  }

  behavior of "A formatResult"

  it should "return correct string for identity" in {
    assert(formatResult("identity", 42, identity) == "The identity of 42 is 42.")
  }

  it should "return correct string for fib" in {
    assert(formatResult("fib", 7, fib) == "The fib of 7 is 13.")
  }
}
