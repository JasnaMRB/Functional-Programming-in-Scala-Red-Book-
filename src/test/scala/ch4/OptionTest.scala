package ch4

import org.scalatest.FlatSpec
import ch4.Option._

/**
  * author: bryanesmith
**/

class OptionTest extends FlatSpec {

  val intNone:Option[Int] = None
  val intNil:List[Int] = Nil
  val optIntNil:List[Option[Int]] = Nil

  "map" should "handle Some" in
    assert(Some(4).map(_ + 1) == Some(5))

  it should "handle None" in
    assert(intNone.map(_ + 1) == None)

  "flatMap" should "handle Some" in
    assert(Some(4).flatMap(i => Some(i + 1)) == Some(5))

  "flatMap" should "handle Nones" in {
    assert(intNone.flatMap(i => Some(i + 1)) == None)
    assert(Some(4).flatMap(_ => None) == None)
  }

  "getOrElse" should "handle Some" in
    assert(Some(4).getOrElse(5) == 4)

  it should "handle None" in
    assert(None.getOrElse(5) == 5)

  "orElse" should "handle Some" in
    assert(Some(4).orElse(None) == Some(4))

  it should "handle None" in {
    assert(intNone.orElse(Some(5)) == Some(5))
    assert(intNone.orElse(None) == None)
  }

  "filter" should "handle Some" in {
    assert(Some(4).filter(_ > 3) == Some(4))
    assert(Some(4).filter(_ <= 3) == None)
  }

  it should "handle None" in
    assert { intNone.filter(_ > 3) == None }

  "map2" should "handle Some" in
    assert { map2(Some(1), Some(2))(_ + _) == Some(3) }

  it should "handle None" in {
    assert { map2(Some(1), None)(_ + _) == None }
    assert { map2(intNone, Some(2))(_ + _) == None }
  }

  "sequence" should "handle empty list" in
    assert { sequence(optIntNil) == Some(Nil) }

  it should "handle non-empty list of all Some" in {
    assert { sequence(List(Some(1.0))) == Some(List(1.0)) }
    assert { sequence(List(Some(1.0), Some(2.0))) == Some(List(1.0, 2.0)) }
  }

  it should "handle non-empty list containing None" in {
    assert { sequence(List(intNone)) == None }
    assert { sequence(List(Some(1.0), None)) == None }
    assert { sequence(List(intNone, Some(2.0))) == None }
  }

  "traverse" should "handle empty list" in
    assert { traverse(intNil)(a => Some(a)) == Some(Nil) }

  it should "handle non-empty lists that will contain all Some" in {
    assert { traverse(List(1.0))(a => Some(a)) == Some(List(1.0))}
    assert { traverse(List(1.0, 2.0))(a => Some(a)) == Some(List(1.0, 2.0))}
  }

  it should "handle non-empty lists that will contain None" in {
    assert { traverse(List(1.0))(a => None) == None }
    assert { traverse(List(1.0, 2.0))(a => if (a==1.0) Some(a) else None) == None }
  }
}
