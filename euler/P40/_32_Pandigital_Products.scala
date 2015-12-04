package P40

import util.Utils

/**
 * User: user
 * Date: 12/29/2014
 */
object _32_Pandigital_Products {
  val TOTAL_SUM_DIGIT = 45  // 9*10/2  n*(n+1)/2

  def main( args: Array[String] ): Unit = {
    val res = scala.collection.mutable.HashSet.empty[Int]

    // LHS must be always 5 digits total from reasoning
    for (d1 <- 1 to 9;  d2 <- 1 to 9;  d3 <- 1 to 9;  d4 <- 1 to 9;  d5 <- 1 to 9) {
      val leftString: String = d1.toString + d2.toString + d3.toString + d4.toString + d5.toString
      if (!Utils.charRepeated( leftString )) {
        // 1x4 digits
        var a = d1
        var b = (d2.toString + d3.toString + d4.toString + d5.toString).toInt
        var prod = a * b
        if ( prod <= 9999 && prod.toString.indexOf('0')<0 &&
            !Utils.charRepeated( prod.toString ) &&
            !overlapDigits( leftString, prod.toString )) {
          res += prod
          println( a +" * "+ b+" = "+prod)
        }
        else {
          // 2x3 digits
          a = (d1.toString + d2.toString).toInt
          b = (d3.toString + d4.toString + d5.toString).toInt
          prod = a * b
          if ( prod <= 9999 && prod.toString.indexOf('0')<0 &&
              !Utils.charRepeated( prod.toString ) &&
              !overlapDigits( leftString, prod.toString )) {
            res += prod
            println( a +" * "+ b+" = "+prod)
          }
        }
      }
    }
    println("res = "+res.foldLeft(0)((b,a) => b+a))
  }


  // n2 is smaller length = 4
  def overlapDigits( n1: String, n2: String ): Boolean = {
    val ASSUMED_TOTAL_LEN = 9
    if (n1.toString.length + n2.toString.length != ASSUMED_TOTAL_LEN) {
      throw new Exception("length problem! " + n1+", "+n2)
    }
    val chArr1 = n1.toCharArray
    for (i <- 0 until 4)
      if (chArr1.contains(n2.charAt(i)))
        return true
    false
  }
}
