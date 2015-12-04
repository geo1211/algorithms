package P30

import util.Utils
import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/27/2014
 */
object _26_Reciprocal_Cycles {

  def main( args: Array[String] ): Unit = {
    var maxD = -1
    var maxS = ""

    for (d <- 3 until 1000) {
      var currentQuotients: String = ""
      var existRemainders = Array.empty[Int]
      var numerator = 1
      var remainder = -1
      breakable {
        while (remainder != 0) {
          remainder = numerator % d
          currentQuotients += (numerator / d).toString
          val ind = existRemainders.indexOf( remainder )
          if (ind > -1) {
            val repeat = currentQuotients.substring( ind+1, currentQuotients.length )
            if (repeat.length > maxS.length) {
              maxD = d
              maxS = repeat
            }
            println( d +" -> "+ repeat )
            break()
          }
          existRemainders :+= remainder
          numerator = remainder * 10
        }
      }
    }
    println("")
    println("d = "+maxD)
    println("repeat = "+maxS)
  }


  // this solution is brute force - won't finish in time  (repeat string is 980+ length!)
  def main1( args: Array[String] ) {
    // big enough numerator to avoid fractions
    val numerator = BigInt("10000000000000000000000000000000000000000000000000000000000000000000000")
    var maxRepeated: String = ""
    var maxDenominator: Int = 1
    var maxIndex: Int = -1

    for (d <- 2 until 1000) {
      var resStr = (numerator / BigInt(d)).toString()   // actual fractions are truncated by int/int
      resStr = trimTrailingZeros( resStr.replaceAllLiterally(".", ""))
      println( d +": "+ resStr )

      val tuple = Utils.getRepeatString(resStr)
      val repeated = tuple._2
      if (repeated.length > maxRepeated.length) {
        maxRepeated = repeated
        maxDenominator = d
        maxIndex = tuple._1
      }
    }
    println("d = "+maxDenominator)
    println("r = "+maxRepeated)
  }

  def trimTrailingZeros( str: String ): String = {
    var res = str
    while (res.endsWith("0"))
      res = res.substring(0, res.length-1)
    res
  }
}
