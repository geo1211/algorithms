package P40

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/29/2014
 */
object _31_Coin_Sums {

  val _UNITS_ = Array( 1, 2, 5, 10, 20, 50, 100, 200 )
  //val _UNITS_ = Array( 20, 50, 100, 200 )

  // recursive - because UNITS.length is small, we don't need to @tailrec
  def getNumOfWays( goal: Int, units: Array[Int] ): Int = {
    //println("goal, units = "+goal+", ["+units.mkString(",")+"]")
    if (goal == 0 || goal == 1 || units.length == 1)
      1
    else {
      var newUnits: Array[Int] = null
      var biggestCoin: Int = 0
      var biggerCoin: Int = 0
      breakable {
        for (i <- 0 until units.length)
          if (units(i) <= goal) {
            biggestCoin = units(i)
            biggerCoin = units(math.max(i-1,0))
          }
          else {
            newUnits = units.slice(0, math.max(i-1,1))
            break()
          }
      }
      if (newUnits == null)  // all units are <= goal, newUnits simply -1
        newUnits = units.slice(0, math.max(units.length-1,1))

      var res = 0
      val quotient = goal / biggestCoin
      for (i <- 0 to quotient) // when biggestCoin is used, i >= 1
        res += getNumOfWays(goal - i*biggestCoin, newUnits)
      res
    }
  }

  def main( args: Array[String] ): Unit = {
    println( getNumOfWays( 200, _UNITS_ ))
  }
}
