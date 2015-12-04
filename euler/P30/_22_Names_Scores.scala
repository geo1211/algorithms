package P30

/**
 * User: user
 * Date: 12/25/2014
 */
object _22_Names_Scores {

  def main( args: Array[String] ) {
    var namesArr = io.Source.fromFile( new java.io.File("E:\\p022_names.txt")).mkString.replaceAllLiterally("\"","").split(",")
    namesArr = namesArr.sortWith(_ < _)

    var sum = BigInt(0)
    for (i <- 1 to namesArr.length) {
      print( namesArr(i-1) +", ")
      val aValue = getAlphabeticalValue(namesArr(i - 1))
      print( aValue+", ")
      sum += BigInt(i * aValue)
      println( sum )
    }
    println("sum = "+ sum)
  }


  def getAlphabeticalValue( s: String ): Int = {
    s.toCharArray.foldLeft(0)((b,a) => b+(a.toInt-64))
  }
}
