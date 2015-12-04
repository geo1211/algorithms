package P30

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/28/2014
 */
object _27_Quadratic_Primes {

  val PRIME_UPPER_LIMIT = 999999
  var primes = Array.empty[Int]
  val primesF = new Array[Boolean]( PRIME_UPPER_LIMIT )

  val LIMIT_A = 1000
  val LIMIT_B = 1000

  var maxN = 0
  var maxA = Int.MinValue
  var maxB = Int.MinValue

  def main( args: Array[String] ): Unit = {
    println("populating primes...")
    populatePrimes()
    println("... done.")
    for (a <- -LIMIT_A+1 until LIMIT_A) {
      println(a)
      for (b <- -LIMIT_A+1 until LIMIT_A) {
        var isPrime = true
        var n = 0
        while (isPrime) {
          val func = n*n + a*n + b
          if (func <= 0 || func%2 == 0 || !primesF(func))
            isPrime = false
          n += 1
        }
        if (n-1 > maxN) {
          println(s"n,a,b == $n,$a,$b")
          maxN = n
          maxA = a
          maxB = b
        }
      }
    }
    println(s"n, a, b, r == $maxN, $maxA, $maxB, {$maxA*$maxB}")
  }


  def populatePrimes() {
    primesF(1) = true  // for internal purpose, 1 is not added to "primes"
    primesF(2) = true;  primes :+= 2
    primesF(3) = true;  primes :+= 3
    for ( i <- 4 to PRIME_UPPER_LIMIT ) {
      if (i % 50000 == 0)
        println(i+"...")
      var isPrime = true
      breakable { primes.foreach( p => {
        if (i % p == 0) {
          isPrime = false
          break()
        }
      }) }
      if (isPrime) {
        primes :+= i
        primesF(i) = true
      }
    }
  }
}
