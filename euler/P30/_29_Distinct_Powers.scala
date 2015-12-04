package P30

import scala.collection.mutable

/**
 * User: user
 * Date: 12/28/2014
 */
object _29_Distinct_Powers {

  def main( args: Array[String] ): Unit = {
    var res = mutable.HashSet.empty[BigInt]
    for (a <- 2 to 100)
      for (b <- 2 to 100) {
        val r = bigIntPower(a, b)
        res += r
        println(s"a,b,r = $a,$b,$r")
      }
    println(res.size)
  }

  def bigIntPower( base: Int, power: Int ): BigInt = {
    var res = BigInt(1)
    val baseBig = BigInt(base)
    for (i <- 0 until power)
      res *= baseBig
    res
  }
}
