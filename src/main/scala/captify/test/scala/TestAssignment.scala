package captify.test.scala

import scala.util.Try
import SparseIterators._

import scala.collection.mutable

/**
  * Here are the functions to fill in.
  */
object TestAssignment {
  /**
    * Generate a contiguous sub-sample from given sequence.
    *
    * Iterator provided should be immediately thrown away after calling this method,
    * so don't worry about any side-effects.
    *
    * @param iterator   to be sampled
    * @param after      the index of first element to be included, zero-based
    * @param sampleSize quantity of elements returned
    * @return sampleAfter(iteratorFromOne, 1, 2) should be same as to Seq[BigInt](2,3,4).toIterator
    */
  def sampleAfter(iterator: Iterator[BigInt], after: Int, sampleSize: Int): Iterator[BigInt] = {
    val it = iterator.drop(after)

    it.take(sampleSize + 1)
  }

  /**
    * Get value by index from given iterator.
    *
    * Iterator provided should be immediately thrown away after calling this method,
    * so don't worry about any side-effects.
    *
    * @param iterator to get value from
    * @param position zero-based
    * @return value at given position
    */
  def valueAt(iterator: Iterator[BigInt], position: Int): BigInt = {
    sampleAfter(iterator, position, 1).next()
  }

  /**
    * Produce an iterator which generates values from given subset of input iterators.
    *
    * The iterator returned should conform to following properties:
    * * if incoming sequences are sorted ascending then output iterator is also sorted ascending
    * * duplicates are allowed:
    * * if there're occurrences of the same value across multiple iterators - respective number of dupes are present in merged version
    * * if there're any dupes present in one of input iterators - respective number of dupes are present in merged version
    *
    * @param iterators to be merged
    * @return Iterator with all elements and ascending sorting retained
    */
  def mergeIterators(iterators: Seq[Iterator[BigInt]]): Iterator[BigInt] = {
    val buf = Array.fill[BigInt](iterators.length)(Long.MaxValue)

    for (i <- iterators.indices) {
      val it = iterators(i)
      if (it.hasNext) {
        buf(i) = it.next()
      }
    }
    /** For set of K iterators
      * Space complexity O(K)
      * hasNext() & next()  time complexity O(K)
      *
      */
    new Iterator[BigInt] {
      def findMin(buf: Array[BigInt]): Option[ValueWithIndex[BigInt]] = {
        buf
          .indices
          .filter(i => buf(i) != Long.MaxValue)
          .foldLeft(Option.empty[ValueWithIndex[BigInt]])((min: Option[ValueWithIndex[BigInt]], currentIndex: Int) => min match {
            case None => Some(ValueWithIndex(currentIndex, buf(currentIndex)))
            case Some(x) =>
              if (x.value > buf(currentIndex)) {
                Some(ValueWithIndex(currentIndex, buf(currentIndex)))
              } else {
                Some(x)
              }
          })
      }

      override def hasNext: Boolean = findMin(buf).isDefined

      override def next(): BigInt = {
        findMin(buf) match {
          case None => throw new NoSuchElementException()
          case Some(ValueWithIndex(i, min)) =>
            val s = iterators(i)
            if (s.hasNext) {
              buf(i) = s.next()
            } else {
              buf(i) = Long.MaxValue
            }
            min
        }
      }
    }
  }

  /**
    * How much elements, on average, are included in sparse stream from the general sequence
    *
    * @param sparsity to analyze
    * @param extent   number of sequence elements to analyze
    * @return approximately 0.5 for sparsity=2, 0.33 for sparsity=3, and so on
    */
  def approximateSparsity(sparsity: Int, extent: Int): Double = {
    extent / valueAt(iteratorSparse(sparsity), extent - 1).toDouble
  }

  /**
    * Approximate actual for given range of sparsity values.
    *
    * As approximation is potentially long-running task, try to run calls to approximateSparsity() in parallel.
    * Also, as such calls may end up in exception for some tricky sparsity values, actual estimation should be kept in Try.
    *
    * For example, calling this with sparsityMin=2, sparsityMax=4, extent=1000 should:
    * - incur three calls to approximateSparsity for three respective values of sparsity and extent of 1000
    * - return Seq(2 -> Success(0.5), 3 -> Success(0.33), 4 -> Success(0.25)) (values given are approximates)
    *
    * @param sparsityMin non-negative value, inclusive for the range evaluated
    * @param sparsityMax non-negative value, inclusive for the range evaluated
    * @param extent      this affects precision and time spent
    * @return Seq of (Sparsity, Try[Approximation]) pairs
    */
  def approximatesFor(sparsityMin: Int, sparsityMax: Int, extent: Int): Seq[(Int, Try[Double])] = {
    (sparsityMin until sparsityMax).par.map(i => (i, Try {
      approximateSparsity(i, extent)
    })).seq
  }

  case class ValueWithIndex[T](index: Int, value: T)

}
