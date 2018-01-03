package ch4

import scala.{Either => _, Left => _, Option => _, Right => _} // hide std library `Option` and `Either`, since we are writing our own in this chapter

/**
  * @author jasnamrb
  */

// Ex. 4.6
sealed trait Either[+E,+A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case Left(a) => Left(a)
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] =  this match {
    case Right(a) => f(a)
    case Left(e) => Left(e)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(_) => b
    case Right(a) => Right(a)
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = for {
    x <- this
    y <-  b
  } yield f(x, y)
}
case class Left[+E](get: E) extends Either[E,Nothing]
case class Right[+A](get: A) extends Either[Nothing,A]

object Either {
  // Ex. 4.7
  def traverse[E,A,B](es: List[A])(f: A => Either[E, B]): Either[E, List[B]] = es match {
    case Nil => Right(Nil)
    case x :: xs => (f(x) map2 traverse(xs)(f))(_ :: _)
  }

  // Ex. 4.7 cont'd
  def sequence[E,A](es: List[Either[E,A]]): Either[E,List[A]] =
    traverse(es)(x => x)

  /* Ex. 4.8
  In this implementation, map2 is only able to report one error, even if both the
  name and the age are invalid. What would you need to change in order to report both
  errors?Would you change map2 or the signature of mkPerson? Or could you create a new
  data type that captures this requirement better than Either does, with some
  additional structure? How would orElse, traverse, and sequence behave differently for
  that data type?
   */

  def mean(xs: IndexedSeq[Double]): Either[String, Double] =
    if (xs.isEmpty)
      Left("mean of empty list!")
    else
      Right(xs.sum / xs.length)

  def safeDiv(x: Int, y: Int): Either[Exception, Int] =
    try Right(x / y)
    catch { case e: Exception => Left(e) }

  def Try[A](a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e) }

}
