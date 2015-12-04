package P30

import util.Utils
import scala.collection.mutable
import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/25/2014
 */
object _23_Non_Abundant_Sums {
  val ANALYTICAL_LIMIT: Int = 28123

  def main( args: Array[String] ) {
    var abundants = mutable.LinkedHashSet.empty[Int]
    var abundantsArr: Array[Int] = null

    for (i <- 12 to ANALYTICAL_LIMIT) {
      val factors = Utils.getFactors( i )
      val sum = factors.foldLeft(0)((b,a) => b+a)
      if (sum > i)
        abundants += i
    }
    println(abundants.size +": "+ abundants.mkString(", "))
    println("")
    abundantsArr = abundants.toArray

    var finalSum = 0
    for (i <- 1 to 23) finalSum += i  // 24 is the first # that can possibly be sum of 2 abundant numbers (12 + 12)
    for (n <- 24 to ANALYTICAL_LIMIT) {
      var canBeSum = false
      breakable {
        for (a <- 0 until abundantsArr.length) {
          if (abundantsArr(a) >= n)
            break()
          val diff = n - abundantsArr(a)
          if (abundants(diff)) {
            println(n+" = "+abundantsArr(a)+" + "+diff)
            canBeSum = true
            break()
          }
        }
      }
      if (!canBeSum)
        finalSum += n
    }
    println("final sum = "+ finalSum)
  }
}
