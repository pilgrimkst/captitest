package captify.test.scala

import org.scalatest.{FunSuite, Matchers}

class TestAssignment$Test extends FunSuite with Matchers {

  test("sampleAfter should shrink input iterator") {
    TestAssignment.sampleAfter(Seq[BigInt](1, 2, 3, 4, 5).iterator, 1, 2).toList should be(List(2, 3, 4))
  }


  test("sampleAfter should return empty iterator from empty input") {
    TestAssignment.sampleAfter(Seq[BigInt]().iterator, 1, 2).toList should be(List())
  }

  test("sampleAfter should behave correctly with infinite iterators") {
    TestAssignment.sampleAfter(SparseIterators.iteratorFromOne, 1, 2).toList should be(List(2, 3, 4))
  }

}
