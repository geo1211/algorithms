package P10

import scala.util.control.Breaks._
/**
 * User: user
 * Date: 12/15/2014
 */
object _7_10001st_Prime {
  def main( args: Array[String] ): Unit = {
    var primes = Array.empty[Int]
    primes :+= 2
    primes :+= 3
    // 2 primes so far

    breakable { for (i <- 4 to 999999999) {
      var isPrime = true
      breakable { primes.foreach( p => {
        if (i % p == 0) {
          isPrime = false
          break()
        }
      }) }
      if (isPrime) {
        primes :+= i
        println(i)
      }
      if (primes.length == 10001) {
        println( "res = "+ primes(10000))
        sys.exit()
      }
    } }
  }
}
