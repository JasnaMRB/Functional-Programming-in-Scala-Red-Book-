package ch3

import org.scalatest.FlatSpec
import ch3.List._

/**
  * @author jblemberg
  */
class ListTest extends FlatSpec {
  "x"  should "equal 3" in {
    assert(List.x == 3)
  }

  "drop" should "drop the first n elements in a list" in {
    assert(drop(List(1, 2, 3), 1) == List(2, 3))
    assert(drop(List(1, 2, 3), 0) == List(1, 2, 3))
    assert(drop(List("a", "b"), 2) == Nil)
    assert(drop(List(1, 2), 3) == Nil)
    assert(drop(Nil, 1) == Nil)
  }

  "dropWhile" should "drop elements in a list while a condition is met" in {
    assert(dropWhile(List(1, 2, 3), (x: Int) => x < 2) == List(2,3))
    assert(dropWhile(List(1, 2, 3), (x: Int) => x > 2) == List(1,2,3))
    assert(dropWhile(List(1, 2, 3), (x: Int) => x > 0) == Nil)
    assert(dropWhile(Nil, (x: Int) => x > 0) == Nil)
  }

  "init" should "return list of elements from given list except for last element" in {
    assert(init(List(1, 2, 3)) == List(1,2))
    assert(init(List(1)) == Nil)
  }

}
