package P20

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/20/2014
 */
object _12_Highly_Divisible_Triangular_Number {


  // 30015, 25024
  def main( args: Array[String] ){
    breakable {
      for (n <- 1 until 99999) {
        val NUM = n * (n+1) / 2
        val factors = getNumOfFactors( NUM )
        println(n+", "+NUM +" -> "+ factors)

        if (factors > 500) {
          println("RES = " + NUM + " from "+n)
          break()
        }
      }
    }
  }

  def getNumOfFactors( n: Int ): Int = {
    var res = 0
    val sqrt = Math.floor(Math.sqrt(n)).toInt
    for (i <- 1 to sqrt) {
      if (n % i == 0)
        res += 2
      if (sqrt * sqrt == n)
        res -= 1
    }
    res
  }
}
