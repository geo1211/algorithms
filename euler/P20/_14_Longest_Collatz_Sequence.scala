package P20

/**
 * User: user
 * Date: 12/20/2014
 */
object _14_Longest_Collatz_Sequence {

  val BIGINT_2 = BigInt(2)
  val BIGINT_0 = BigInt(0)

  def main( args: Array[String] ) {
    var maxTerms = 1
    var maxN = 1

    for (n <- 13 to 1000000) {
      var i = BigInt(n)
      var terms = 1
      print(i)
      while (i > 1) {
        if (i % BIGINT_2 == BIGINT_0) i /= 2
        else i = 3*i + 1
        //print(" -> "+i)
        terms += 1
      }
      println("")
      if (terms > maxTerms) {
        println("overtakes: " + terms)
        maxTerms = terms
        maxN = n
      }
    }

    println(maxN +", "+maxTerms)
  }
}
