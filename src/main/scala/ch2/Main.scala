package ch2

object Main {


  // Ex. 2.1
  def fib(n: Int): Int = {

    def fibLoop(acc: Int, prev: Int, n: Int): Int = {
      println("acc: " + acc + " prev: " + prev + " n: " + n)
      if (n <= 0)
        prev
      else {
        fibLoop(acc + prev, acc, n-1)
      }
    }
    fibLoop(1, 0, n)
  }

  // Ex. 2.2
  def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
    def isSortedLoop(as: Array[A]): Boolean = {
      if (as.length <= 1)
        true
      else if (ordered(as.head, as.tail.head))
        isSortedLoop(as.tail)
      else {
        false
      }
    }
    isSortedLoop(as)
  }

  // Ex. 2.3
  def curry[A,B,C](f: (A, B) => C): A => (B => C) =
    (a) => (b) => f(a,b)

  // Ex. 2.4
  def uncurry[A,B,C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)

  // Ex. 2.5
  def compose[A,B,C](f: B => C, g: A => B): A => C =
    a => f(g(a))

  // Helpers
  def identity(num: Int): Int = num

  def formatResult(name: String, n: Int, f: Int => Int): String =
    "The %s of %d is %d.".format(name, n, f(n))

  def main(args: Array[String]) : Unit = {
    println(s"fib: ${(0 to 8).map(fib).mkString(", ")}")
  }
}
