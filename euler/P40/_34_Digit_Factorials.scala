package P40

import util.Utils

/**
 * User: user
 * Date: 12/29/2014
 */
object _34_Digit_Factorials {
  val factorialArray = {
    val tmp = new Array[Int]( 10 )
    for (i<-0 to 9) tmp(i) = Utils.factorial(i)
    tmp
  }

  var res: Int = 0
  def main( args: Array[String] ): Unit = {
    println(sumDigitFactorials(999))
    println(sumDigitFactorials(543))

    for (n <- 10 to 9999999) {
      if (n % 100000 == 0) println(n+"...")
      if (n == sumDigitFactorials(n)) {
        res += n
        println("found - " + n + " -> " + res)
      }
    }
    println("res = "+res)
  }

  def sumDigitFactorials( n: Int ): Int = {
    n.toString.toCharArray.foldLeft(0)((b,a) => b+factorialArray(a.toString.toInt))
  }
}
