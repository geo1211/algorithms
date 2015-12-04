package P10

/**
 * User: user
 * Date: 12/15/2014
 */
object _6_Sum_Square_Difference {
  def main( args: Array[String] ): Unit = {
    var sumSqr = 0
    for (i <- 1 to 100)
      sumSqr += i * i
    println("sumSqr = " + sumSqr)
    var sqrSum = 100*101/2
    sqrSum *= sqrSum
    println("sqrSum = " + sqrSum)
    println("diff = " + (sqrSum - sumSqr))
  }
}
