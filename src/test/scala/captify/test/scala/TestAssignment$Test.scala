package captify.test.scala

import java.util.NoSuchElementException

import captify.test.scala.TestAssignment.{sampleAfter, valueAt}
import org.scalatest.{FunSuite, Matchers}

class TestAssignment$Test extends FunSuite with Matchers {

  test("sampleAfter should shrink input iterator") {
    sampleAfter(Seq[BigInt](1, 2, 3, 4, 5).iterator, 1, 2).toList should be(List(2, 3, 4))
    sampleAfter(Seq[BigInt](1, 2, 3, 4, 5).iterator, 0, 2).toList should be(List(1, 2, 3))
  }


  test("sampleAfter should return empty iterator from empty input") {
    sampleAfter(Seq[BigInt]().iterator, 1, 2).toList should be(List())
  }

  test("sampleAfter should behave correctly with infinite iterators") {
    sampleAfter(SparseIterators.iteratorFromOne, 1, 2).toList should be(List(2, 3, 4))
  }

  test("valueAt should return nth value from iterator") {
    valueAt(Seq[BigInt](1, 2, 3, 4).iterator, 1) should be(2)
    valueAt(Seq[BigInt](1, 2, 3, 4).iterator, 0) should be(1)
  }

  test("valueAt: iterators can't be reused") {
    val it = Seq[BigInt](1, 2, 3, 4).iterator
    valueAt(it, 1) should be(2)
    valueAt(it, 0) should not be 1
  }

  test("valueAt: exception for value out of bounds") {
    val it = Seq[BigInt](1, 2).iterator
    assertThrows[NoSuchElementException] {
      valueAt(it, 3)
    }
  }


  test("sampleAfter: iterators can't be reused") {

    val it = Seq[BigInt](1, 2, 3, 4, 5).iterator
    sampleAfter(it, 1, 2).toList should be(List(2, 3, 4))
    sampleAfter(it, 0, 2).toList should not be List(1, 2, 3)
  }
}
