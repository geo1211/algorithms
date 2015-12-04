package P10

/**
 * User: user
 * Date: 12/15/2014
 */
object _9_Special_Pythagorean_Triplet {
  def main( args: Array[String] ): Unit = {
    for (a <- 1 to 500) {
      for (b <- 1 to 500) {
        val c = 1000 - a - b
        if (a*a + b*b == c*c)
          println(a +", "+ b +", "+ c +" -> "+ (a*b*c))
      }
    }
  }
}
