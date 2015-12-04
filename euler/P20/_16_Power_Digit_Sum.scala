package P20

/**
 * User: user
 * Date: 12/21/2014
 */
object _16_Power_Digit_Sum {
  val BIGINT_2 = BigInt(2)
  def main( args: Array[String] ) {
    var res = BigInt(1)
    for (i <- 1 to 1000)
      res *= BIGINT_2
    println(res)

    var sum = 0
    val resStr = res.toString()
    for (c <- 0 until resStr.length) {
      val ch = resStr.charAt(c).toString.toInt
      sum += ch
    }
    println(sum)
  }
}
