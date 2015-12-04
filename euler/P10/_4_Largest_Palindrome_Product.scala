package P10

/**
 * User: user
 * Date: 12/15/2014
 */
object _4_Largest_Palindrome_Product {
  def main( args: Array[String] ) {
    var max = 0
    for (i <- 100 to 999) {
      println("i: " + i)
      for (j <- i to 999) {
        val prod = i * j
        if (prod > max && isPalindromeNumber( prod ))
          max = prod
      }
    }
    println("res = "+max)
  }
  def isPalindromeNumber( num: Int ): Boolean = {
    val s = num.toString
    val l = s.length
    for (i <- 0 to l/2-1)
      if (s.charAt(i) != s.charAt(l-1-i))
        return false
    true
  }
}
