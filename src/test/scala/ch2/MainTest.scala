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

  it should "return correct string for identity" in
    assert(formatResult("identity", 42, identity) == "The identity of 42 is 42.")

  it should "return correct string for fib" in
    assert(formatResult("fib", 7, fib) == "The fib of 7 is 13.")

  behavior of "isSorted"

  private def lt(i1: Int, i2: Int) = i1 < i2

  it should "return true if nothing in array" in
    assert(isSorted(Array(), lt))

  it should "return true if one thing in array" in
    assert(isSorted(Array(7), lt))

  it should "return true if two things are in sorted order" in
    assert(isSorted(Array(5, 7), lt))

  it should "return false if two things are in unsorted order" in
    assert(!isSorted(Array(7,5), lt))

  it should "return true if five things in sorted order" in
    assert(isSorted(Array(2, 3, 5, 6, 7), lt))

  it should "return false if five things in unsorted order" in
    assert(!isSorted(Array(3, 5, 6, 2, 7), lt))

  "curry" should "curry a function" in
    assert(curry(Math.min)(5)(2) == 2)

  "uncurry" should "uncurry a function" in
    assert(uncurry(curry(Math.min))(5,2) == 2)

  private def addOne(i1: Int) = i1 + 1

  "compose" should "compose two functions" in
    assert(compose(addOne, addOne)(1) == 3)
}
