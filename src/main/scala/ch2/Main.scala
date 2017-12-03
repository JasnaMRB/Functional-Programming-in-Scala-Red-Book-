package ch2

object Main {

  def fib(idx: Int): Option[Int] = {
    @annotation.tailrec
    def go(n: Int, current: Int, next: Int): Option[Int] = {
      if (n < 0) None
      else if (n == 0) Some(current)
      else go(n - 1, next, current + next)
    }
    go(idx, 0, 1)
  }

  def main(args: Array[String]) : Unit = {
    println(s"fib: ${(0 to 8).flatMap(fib).mkString(", ")}")
  }
}
