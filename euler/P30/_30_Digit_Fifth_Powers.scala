package P30

/**
 * User: user
 * Date: 12/28/2014
 */
object _30_Digit_Fifth_Powers {
  def main( args: Array[String] ): Unit = {
    val UPPERBOUND: Int = 500000  // 9^5*6= 59059*6 = 354294 -> not a good upper really, may not work
    val POWER = 5
    var n = 2
    var sumSoFar = 0
    while (true) {
      if (sumDigitsPower(n, POWER) == n) {
        sumSoFar += n
        println("found! "+ n + " -> sumSoFar: "+sumSoFar)
      }
      n += 1
      // exit condition is by manual ctrl-c!
    }
  }

  def sumDigitsPower( num: Int, power: Int ): Int = {
    num.toString.toCharArray.foldLeft(0)((b,a) => b+math.pow( a.toString.toInt, power ).toInt)
  }
}
