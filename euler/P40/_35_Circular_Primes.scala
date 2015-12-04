package P40

import util.Utils

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/29/2014
 */
object _35_Circular_Primes {

  val primes = new Array[Boolean]( 1000000 )
  primes(2) = true
  primes(3) = true


  def main( args: Array[String] ): Unit = {
    // prepare
    var primeCount = 2
    for (n <- 5 to 999999 by 2) {  // odd numbers only
      if (n % 10000 == 0) println("doing prime " + n)
      if (isPrime(n)) {
        primes(n) = true;  primeCount += 1
        //println("prime " +  n)
      }
    }; println("# primes = " + primeCount)  // 78498

    // now go through it all
    var count = 4  // 2,3,5,7
    for (n <- 11 to 999999 by 2) {  // by 2 = odd numbers only
      if ( isCircularPrime( n )) {
        println("found "+n)
        count += 1
      }
    }
    println("total count = "+count)
  }


  def isCircularPrime( n: Int ): Boolean = {
    if (Utils.containsEvenDigit( n ))
      return false
    if (!primes(n))
      return false
    val circulars = Utils.getRotations(n.toString)
    circulars.forall(a => primes(a.toInt))
  }


  // assumes results < n already in the "primes" variable yet, but not n
  def isPrime( n: Int ): Boolean = {
    val sqrt = math.sqrt( n.toDouble ).ceil.toInt
    var res = true
    breakable { for (i <- 3 to sqrt by 2) {  // odd numbers only
      if (primes(i) && (n%i == 0)) {
        res = false
        break()
      } }}
    res
  }
}
