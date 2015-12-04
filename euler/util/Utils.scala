package util

import scala.util.control.Breaks._

/**
 * User: user
 * Date: 12/26/2014
 */
object Utils {

  // sum value of each digit
  def sumDigits( n: Int ): Int = {
    n.toString.toCharArray.foldLeft(0)((b,a) => b+a.toString.toInt)
  }

  // if a char is used more than once in the string, true
  def charRepeated( s: String ): Boolean = {
    var res = false
    val set = scala.collection.mutable.HashSet.empty[Char]
    breakable {
      s.toCharArray.foreach(ch => {
        if (set.contains(ch)) {
          res = true
          break()
        } else
          set += ch
      })
    };res
  }

  // get all factors from 1 until (excluding) n;  not sorted
  def getFactors( n: Int ): Array[Int] = {
    var factors: Array[Int] = Array(1) // 1 is always a factor
    val sqrt: Int = math.sqrt(n).floor.toInt
    for (i <- 2 to sqrt)
      if (n % i == 0) {
        val j = n / i
        factors :+= i
        if (i != j) factors :+= j
      }
    factors
  }

  // move the anchor from left to right, and each time finding longest mirror on both sides
  // returns the mid index, and the actual repeat string
  // NOTE that 33333333 won't return "3333", but only "3" - should be named "getReciprocalCycleString" instead
  //
  def getRepeatString( str: String ): (Int, String) = {
    var longest = ""
    var at = -1
    for (ch <- 1 to str.length - 2) {
      val maxRepeatLen = math.min(ch, str.length - ch)
      for (len <- math.max(1, longest.length+1) to maxRepeatLen) {  // if length <= longest so far, no need
        val left = str.substring(ch-len, ch)
        val right = str.substring(ch, ch+len)
        if (left == right) {
          val reduced = reduceString( left )
          if (reduced.length==left.length || longest.length<reduced.length) {
            //println("left,reduced = "+left+","+reduced)
            longest = right
            at = ch
          }
        }
      }
    }
    (at, longest)
  }

  def isRepeatingChar( str: String ): Boolean = {
    if (str.length < 2) true
    else {
      for (i <- 1 until str.length)
        if (str.charAt(i-1).toInt != str.charAt(i).toInt)
          return false
      true
    }
  }

  // 345345345->345;  111111->1
  def reduceString( str: String ): String = {
    if (str.length < 2) str
    else {
      val upper = math.floor(str.length/2).toInt
      for (i <- 1 to upper) {
        if (str.length % i == 0) {  // not reducible if length not divisible
          var reducible = true
          val token = str.substring(0, i)
          for (iter <- 1 until str.length/i)
            if (token != str.substring( i*iter, i*(iter+1) ))
              reducible = false
          if (reducible)
            return token
        }
      }
      str
    }
  }

  val BIGINT_0 = BigInt(0)
  val BIGINT_1 = BigInt(1)
  def factorialBigInt( n: BigInt ): BigInt = {
    n match {
      case BIGINT_0 => BIGINT_1
      case BIGINT_1 => BIGINT_1
      case _ => n * factorialBigInt( n-1 )
    }
  }
  def factorial( n: Int ): Int = {
    n match {
      case 0 => 1
      case 1 => 1
      case _ => n * factorial( n-1 )
    }
  }

  def getPermutations( prefix: String, str: String ): Array[String] = {
    val len = str.length
    if (len == 0) Array(prefix)
    else {
      var res = Array.empty[String]
      for (c <- 0 until len)
        res ++= getPermutations(
          prefix + str.substring(c,c+1),
          str.substring(0,c) + str.substring(c+1)
        )
      res
    }
  }
  def getPermutations( str: String ): Array[String] = {
    getPermutations( "", str )
  }


  def getRotations( str: String ): Array[String] = {
    var res = Array.empty[String]
    for (i <- 1 to str.length)
      res :+= str.substring(i-1) +  str.substring(0, i-1)
    res
  }


  def containsEvenDigit( n: Int ): Boolean = {
    n.toString.toCharArray.foldLeft(false)((b,a) => b || (a.toString.toInt%2==0))
  }


  def main( args: Array[String] ): Unit = {
    println( getRotations("197").mkString(", "))
    println( getRotations("2513").mkString(", "))

    println( containsEvenDigit(13570))
    println( containsEvenDigit(13571))
    println( containsEvenDigit(5525))
    println( getPermutations( "abcd" ).mkString("\n"))

    println( sumDigits( 123456789 ))
    println( sumDigits( 234567891 ))
    println( sumDigits( 234506781 ))

    println( reduceString("789789789"))
    println( reduceString("45454545"))
    println( reduceString("111111"))
    println( reduceString("555"))
    println( reduceString("3434"))
    println("")
    println( getRepeatString( "11123411561112341156" )._2 )
    println( getRepeatString( "111" )._2 )
    println( getRepeatString( "129876987621" )._2 )
    println( getRepeatString( "1212555555" )._2 )   // should be 12, but bug will give 555
    println( getRepeatString( "343434343434" )._2 ) // should be 34, but bug will give 343434
    println( getRepeatString( "000142857142857000" )._2 )
  }
}
