package P20

/**
 * User: user
 * Date: 12/20/2014
 */
object _15_Lattice_Paths {

  val BIGINT_1 = BigInt(1)

  def main( args: Array[String] ){
    println( factorial( BigInt(5) ))
    println( factorial( BigInt(40) ))
    println( factorial( BigInt(20) ))
    println( nCr( BigInt(40), BigInt(20) ))
  }

  def nCr( n: BigInt, r: BigInt ): BigInt = {
    factorial( n ) / (factorial( r ) * factorial( n-r ))
  }

  def factorial( n: BigInt ): BigInt = {
    n match {
      case BIGINT_1 => BIGINT_1
      case _ => n * factorial( n-1 )
    }
  }
}
