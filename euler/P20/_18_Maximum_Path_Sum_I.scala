package P20

/**
 * User: user
 * Date: 12/21/2014
 */
object _18_Maximum_Path_Sum_I {

  val TRIANGLE = Array(
    Array(75),
    Array(95,64),
    Array(17,47,82),
    Array(18,35,87,10),
    Array(20, 4,82,47,65),
    Array(19, 1,23,75, 3,34),
    Array(88, 2,77,73, 7,63,67),
    Array(99,65, 4,28, 6,16,70,92),
    Array(41,41,26,56,83,40,80,70,33),
    Array(41,48,72,33,47,32,37,16,94,29),
    Array(53,71,44,65,25,43,91,52,97,51,14),
    Array(70,11,33,28,77,73,17,78,39,68,17,57),
    Array(91,71,52,38,17,14,91,43,58,50,27,29,48),
    Array(63,66, 4,68,89,53,67,30,73,16,69,87,40,31),
    Array( 4,62,98,27,23, 9,70,98,73,93,38,53,60, 4,23)
  )

  val MAX = Array(
    Array(0),
    Array(0, 0),
    Array(0, 0, 0),
    Array(0, 0, 0, 0),
    Array(0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
    Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
  )

  def main( args: Array[String] ) {
    // do the out most slash and backslash MAX first
    MAX(0)(0) = TRIANGLE(0)(0)
    for (i <- 1 until MAX.length) {
      MAX(i)(0) = MAX(i-1)(0) + TRIANGLE(i)(0)
      MAX(i)(i) = MAX(i-1)(i-1) + TRIANGLE(i)(i)
    }

    // then calculate from 3nd row onwards
    for (i <- 2 until MAX.length)
      for (j <- 1 to i-1)
        MAX(i)(j) = math.max( MAX(i-1)(j-1), MAX(i-1)(j)) + TRIANGLE(i)(j)

    // finally go through last row to get max of MAX
    MAX.foreach( r => {
      r.foreach( c => print(c+", "))
      println("")
    })
    var finalMax = 0
    MAX(MAX.length-1).foreach( c => if (c>finalMax) finalMax=c )
    println("MAX = " + finalMax)
  }
}
