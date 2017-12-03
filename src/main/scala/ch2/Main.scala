package ch2

object Main {

  // Exercise 2.1
  def fib(num: Int): Int = {
    @annotation.tailrec
    def go(n: Int, current: Int, next: Int): Int = {
      if (n <= 0) current
      else go(n - 1, next, current + next)
    }
    go(num, 0, 1)
  }

  // Exercise 2.2
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def go(idx: Int): Boolean = {
      if (idx + 1 >= as.length) true
      else if (!ordered(as(idx), as(idx+1))) false
      else go(idx + 1)
    }
    go(0)
  }

  // Exercise 2.3
  def curry[A,B,C](f: (A,B) => C): A => (B => C) = (a) => (b) => f(a,b)

  // Exercise 2.4
  def uncurry[A,B,C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

  // Helpers
  def identity(num: Int): Int = num

  def formatResult(name: String, n: Int, f: Int => Int): String =
    "The %s of %d is %d.".format(name, n, f(n))

  def main(args: Array[String]) : Unit = {
    println(s"fib: ${(0 to 8).map(fib).mkString(", ")}")
  }
}
