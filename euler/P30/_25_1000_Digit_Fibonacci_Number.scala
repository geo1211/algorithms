package P30

/**
 * User: user
 * Date: 12/27/2014
 */
object _25_1000_Digit_Fibonacci_Number {

  def main( args: Array[String] ) {
    val DIGITS_1000 = {
      var tmp = ""; for (d<-1 to 999) tmp += "0"
      BigInt( "1"+tmp )
    }
    var f_2 = BigInt(1)
    var f_1 = BigInt(1)
    for (i <- 3 until 999999) {
      if (i % 10000 == 0) println(i)
      val f = f_1 + f_2
      if (f > DIGITS_1000) {
        println("res = "+f)
        println("n = "+i)
        sys.exit(0)
      }
      f_2 = f_1
      f_1 = f
    }

  }
}
