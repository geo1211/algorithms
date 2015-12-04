package P30

import util.Utils

/**
 * User: user
 * Date: 12/22/2014
 */
object _21_Amicable_Numbers {

  def main( args: Array[String] ) {
    var amicables = Array.empty[Int]
    var considered = Array.empty[Int]

    for (n <- 1 to 10000) {
      if (!considered.contains(n)) {
        val factors: Array[Int] = Utils.getFactors( n )
        val sum = factors.foldLeft(0)((b, a) => b + a)
        if (sum == n) {
          // perfect number - do nothing
//          amicables :+= n
//          println(n +":  "+ amicables.mkString(","))
        }
        else if (sum > n) {
          val bigFactors = Utils.getFactors( sum )
          val bigSum = bigFactors.foldLeft(0)((b, a) => b + a)
          if (bigSum == n) {
            amicables :+= n
            amicables :+= sum  // amicable number pair
            println(n +":  "+ amicables.mkString(","))
          }
          considered :+= sum
        }
        else {
          // do nothing;  < is already considered in previous iterations
        }
      }
    }

    // finally, print sum
    println("sum = "+amicables.foldLeft(0)((b,a)=>b+a))
  }
}
