package ch2

object Main {

  def identity(num: Int): Int = num

  def fib(num: Int): Int = {
    @annotation.tailrec
    def go(n: Int, current: Int, next: Int): Int = {
      if (n <= 0) current
      else go(n - 1, next, current + next)
    }
    go(num, 0, 1)
  }

  def formatResult(name: String, n: Int, f: Int => Int): String =
    "The %s of %d is %d.".format(name, n, f(n))

  def main(args: Array[String]) : Unit = {
    println(s"fib: ${(0 to 8).map(fib).mkString(", ")}")
  }
}
