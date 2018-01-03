package ch3

/**
  * @author jasnamrb
  */
sealed trait List[+A] // `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing] // A `List` data constructor representing the
// empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another
`List[A]`,
which may be `Nil` or another `Cons`.
 */

case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List { // `List` companion object. Contains functions for creating and working
  // with lists.
  def sum(ints: List[Int]): Int = ints match { // A function that uses pattern matching
    // to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x, xs) => x + sum(
      xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the
    // list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // Ex 3.1
  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  // x should == 3


  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = // Utility functions
    as match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(
      _ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar


  // Ex. 3.2
  def tail[A](l: List[A]): List[A] =
    l match {
      case Nil => Nil
      case Cons(_, xs) => xs
    }

  // Ex 3.3
  def setHead[A](l: List[A], h: A): List[A] =
    l match {
      case Nil => throw new Error("Can't set head on empty list")
      case Cons(x, xs) => Cons(h, xs)
    }

  // Ex 3.4
  def drop[A](l: List[A], n: Int): List[A] = {
    if (n <= 0) {
      l
    }
    else {
      l match {
        case Nil => Nil
        case Cons(_, xs) => drop(xs, n - 1)
      }
    }
  }

  // Ex. 3.5
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Cons(x, xs) => if (f(x)) dropWhile(xs, f) else l
      case Nil => Nil
    }
  }

  // Ex. 3.6
  def init[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(x, xs) => Cons(x, init(xs))
    }
  }

  // Ex. 3.9
  def length[A](l: List[A]): Int = foldRight(l, 0)((x, xs) => xs + 1)

  // 3.10
  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
    l match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
    }
  }

  // 3.11
  def sumFoldLeft(ints: List[Int]): Int = foldLeft(ints, 0)(_ + _)


  def productFoldLeft(ints: List[Int]): Int = foldLeft(ints, 1)( _ * _)


  def lengthFoldLeft[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)

  // 3.12
  def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((acc, x) => Cons(x, acc))

  // 3.13 HARD
  // Write a foldLeft in terms of a foldRight

  def foldRightViaFoldLeft[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    foldLeft(reverse(l), z)((b,a) => f(a,b))

  def foldRightViaFoldLeft_1[A,B](l: List[A], z: B)(f: (A,B) => B): B =
    foldLeft(l, (b:B) => b)((g,a) => b => g(f(a,b)))(z)

  def foldLeftViaFoldRight[A,B](l: List[A], z: B)(f: (B,A) => B): B =
    foldRight(l, (b:B) => b)((a,g) => b => g(f(b,a)))(z)

  // 3.14 in terms of either fold
  def appendWithFold[A](l: List[A], r: List[A]): List[A] = foldRight(l, r)(Cons(_, _))

  // 3.15 HARD aka concat
  def flatten[A](l: List[List[A]]): List[A] = foldRight(l, Nil: List[A])(append)

  // 3.16
  def plus1(ints: List[Int]): List[Int] = foldRight(ints, Nil: List[Int])((x, xs) => Cons(x + 1, xs))

  // 3.17 can use toString
  def doubleToStrings(doubles: List[Double]): List[String] = foldRight(doubles, Nil: List[String])((x, xs) => Cons(x.toString, xs))

  // 3.18
  def map[A, B](l: List[A])(f: A => B): List[B] = foldRight(l, Nil: List[B])((x, xs) => Cons(f(x), xs))

  def map2[A, B](as: List[A])(f: A => B): List[B] = flatMap(as)(a => Cons(f(a), Nil))

  // 3.19
  def filter[A](as: List[A])(f: A => Boolean): List[A] = foldRight(as, Nil: List[A])((x, xs) => if (f(x)) Cons(x, xs) else xs)

  // 3.20
  def flatMap[A,B](as: List[A])(f: A => List[B]): List[B] = flatten(map(as)(f))

  // 3.21
  def filterFlatMap[A](l: List[A])(f: A => Boolean) = flatMap(l)(a => if (f(a)) List(a) else Nil)

  // 3.22
  def add(a: List[Int], b: List[Int]): List[Int] = {
    (a, b) match {
      case (Nil, _) | ( _, Nil) => Nil
      case (Cons(x, xs), Cons(y, ys)) => Cons(x + y, add(xs, ys))
    }
  }

  // 3.23 generalize above so it's not just ints and addition
  def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] = {
    (a, b) match {
      case (Nil, _) => Nil
      case ( _, Nil) => Nil
      case (Cons(x, xs), Cons(y, ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
    }
  }


}