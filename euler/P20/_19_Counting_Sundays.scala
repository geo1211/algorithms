package P20

import java.util.Calendar

/**
 * User: user
 * Date: 12/22/2014
 */
object _19_Counting_Sundays {
  def main( args: Array[String] ) {
    val cal = Calendar.getInstance()
    cal.set( Calendar.YEAR, 1901 )
    cal.set( Calendar.MONTH, 0 )
    cal.set( Calendar.DAY_OF_MONTH, 1 )  // first Sunday

    // add each month, test if it's Sunday
    var count = 0
    while ( cal.get(Calendar.YEAR) < 2001 ) {
      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
        count += 1
        println( cal.getTime )
      }
      val year = cal.get( Calendar.YEAR )
      val month = cal.get( Calendar.MONTH )
      if (month == 11) {  // to next year
        cal.set( Calendar.MONTH, 0 )
        cal.set( Calendar.YEAR, year + 1 )
      } else
        cal.set( Calendar.MONTH, month + 1 )
    }

    println("COUNT = "+count)
  }
}
