package ch1

import org.scalatest.FlatSpec

class FooTest extends FlatSpec {
  behavior of "A foo"

  it should "return a valid foo" in {
    val foo = new Foo()
    assert(foo.foo() == "foo")
  }
}
