package P30

/**
 * User: user
 * Date: 12/28/2014
 */
object _28_Number_Spiral_Diagonals {
  val BIGINT_2 = BigInt(2)

  def main( args: Array[String] ): Unit = {
    var sum = BigInt(25)  // center + 3x3
    for (i <- 5 to 1001 by 2) {
      val oneColumn = i*i + (i-2)*(i-2)+(i-2)+1
      sum += BIGINT_2 * BigInt(oneColumn)
    }
    println(sum)
  }
}
