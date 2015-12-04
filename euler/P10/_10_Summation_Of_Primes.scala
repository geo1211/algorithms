package P10

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/16/2014
 */
object _10_Summation_Of_Primes {
  def main( args: Array[String] ) {
    var sum = BigInt(0)
    var primes = Array.empty[Int]
    primes :+= 2;  sum += 2
    primes :+= 3;  sum += 3
    // 2 primes so far

    for (i <- 5 to 2000000 if i%2==1) {
      if (i % 10000 == 1) println(i+"... "+primes.length+" found.")
      var isPrime = true
      breakable { primes.foreach( p => {
        if (i % p == 0) {
          isPrime = false
          break()
        }
        if (p > i/2) {
          break()
        }
      }) }
      if (isPrime) {
        //println("found " + i)
        primes :+= i
        sum += i
      }
    }
    println("sum = "+sum)
  }
}
