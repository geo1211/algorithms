package P20

import util.Utils

/**
 * User: user
 * Date: 12/22/2014
 */
object _20_Factorial_Digit_Sum {
  def main( args: Array[String] ) {
    println( Utils.factorialBigInt(100).toString().toCharArray.foldLeft(0)((b,a) => b+a.toString().toInt ))
  }
}
