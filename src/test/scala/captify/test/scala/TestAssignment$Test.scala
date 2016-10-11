package captify.test.scala

import java.util.NoSuchElementException

import captify.test.scala.TestAssignment.{sampleAfter, valueAt, mergeIterators}
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

  test("mergeIterators should merge iterators in  ascending order") {
    val i1 = Seq[BigInt](3, 4, 10).iterator
    val i2 = Seq[BigInt](2, 8, 12).iterator
    val i3 = Seq[BigInt](1, 6, 9).iterator

    mergeIterators(Seq(i1, i2, i3)).toList should be(Seq(1, 2, 3, 4, 6, 8, 9, 10, 12))

    var iterators = Seq(
      Seq[BigInt](1, 2, 3).toIterator,
      Seq[BigInt]().toIterator,
      Seq[BigInt](0, 10, 20).toIterator)

    mergeIterators(iterators).toList should be(Seq(0, 1, 2, 3, 10, 20))

    iterators = Seq(Seq[BigInt](1, 2, 3).toIterator, Seq[BigInt](Long.MaxValue).toIterator, Seq[BigInt](0, 10, 20).toIterator)


    mergeIterators(iterators).toList should be(Seq(0, 1, 2, 3, 10, 20, Long.MaxValue))
  }
}
