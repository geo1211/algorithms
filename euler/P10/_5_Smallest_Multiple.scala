package P10

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/15/2014
 * 232792560
 */
object _5_Smallest_Multiple {
  def main( args: Array[String] ) {

    //for (i <- 2522 to 99999999) {
    for (i <- 99999999 to 999999999) {
      if (i % 10000 == 0) println("i... "+i)
      var evenlyDivisible = true
      breakable { for (f <- 2 to 20) {
        if (i % f > 0) {
          evenlyDivisible = false
          break()
        }
      }}
      if (evenlyDivisible) {
        println("res = " + i)
        sys.exit()
      }
    }
  }
}
