package P40

/**
 * User: user
 * Date: 12/29/2014
 */
object _33_Digit_Cancelling_Fractions {
  def main( args: Array[String] ): Unit = {
    var resNumerator = 1
    var resDenominator = 1

    println( digitCancelCorrect(49, 98))

    for (n <- 10 to 98) {
      for (d <- n+1 to 99) {
        if (digitCancelCorrect( n, d )) {
          println( n +"/"+ d)
          resNumerator *= n
          resDenominator *= d
        }
      }
    }
    println(resNumerator)
    println(resDenominator)
  }

  // return resulting (numerator, denominator)
  def digitCancelCorrect( numerator: Int, denominator: Int ): Boolean = {
    if (numerator % 10 == 0)
      false
    else {
      val ratio = numerator.toDouble / denominator

      if (numerator.toString.charAt(0) == denominator.toString.charAt(1) &&
          numerator.toString.substring(1).toDouble / denominator.toString.substring(0,1).toInt == ratio )
        true
      else
        if (numerator.toString.charAt(1) == denominator.toString.charAt(0) &&
            numerator.toString.substring(0,1).toDouble / denominator.toString.substring(1).toInt == ratio )
          true
      else
        false
    }
  }
}
