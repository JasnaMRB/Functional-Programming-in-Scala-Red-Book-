package ch3

import org.scalatest.FlatSpec
import ch3.List._

/**
  * @author jasnamrb
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

  // 3.7
  /*
  product, implemented with foldRight, cannot halt immediately when there is a value 0.0
  because before calling the function, we have to traverse to the end of the list
   */

  // 3.8
  "foldRight" should "do its thing" in {
    assert(foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)) == Cons(1, Cons(2, Cons(3, Nil))))
  }

  "length" should "give length of a list" in {
    assert(length(List(1, 2, 3)) == 3)
    assert(length(List(1, 2, 3, 4, 9, 10)) == 6)
  }

  "sumFoldLeft" should "sum up the a list of ints using foldLeft" in {
    assert(sumFoldLeft(List(1, 2, 3, 4, 9, 10)) == 29)
  }

  "productFoldLeft" should "multiply ints in a list and return the product using foldLeft" in {
    assert(productFoldLeft(List(1, 2, 3, 4)) == 24)
  }

  "lengthFoldLeft" should "give length of list using foldLeft" in {
    assert(lengthFoldLeft(List(1, 2, 3, 4, 10)) == 5)
  }

  "appendWithFold" should "append a list to another list using a fold" in {
    assert(appendWithFold(List(4, 5, 6), List(1, 2, 3)) == List(4, 5, 6, 1, 2, 3))
  }

  "concat" should "put together a list of lists into a single lists" in {
    assert(flatten(List(List(1,2), List(4, 8), List(2, 3))) == List(1, 2, 4, 8, 2, 3))
  }

  "plus1" should "add 1 to each element in a list of ints" in {
    assert(plus1(List(6, 2, 5, 7)) == List(7, 3, 6, 8))
  }

  "reverse" should "reverse a list" in {
    assert(reverse(List(1, 2, 3)) == List(3, 2, 1))
  }

  "doublesToStrings" should "convert a list of doubles to a list of string equivalents" in {
    assert(doubleToStrings(List(0.0, 4.6, 9.1)) == List("0.0", "4.6", "9.1"))
  }

}
