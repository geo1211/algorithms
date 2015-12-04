package P20

/**
 * User: user
 * Date: 12/21/2014
 */
object _17_Number_Letter_Counts {
  def main( args: Array[String] ) {
    // no need for computation
    // simple math
    // let a be the number of characters used from one to ninety-nine
    // then from 100-199, 13*100 + a - 3 characters are used  (13 = one hundred and, -3 = no And for one hundred)
    // from 200-999, switch 13 with 14 or 15 depending on the length of (one, two, three, etc.)
    // total
    //   = a + (13*100+a-3)*3 + (14*100+a-3)*3 + (15*100+a-3)*3 + 11  (11 = one thousand)
    //   = 854 + (1300+854-3)*3 + (1400+854-3)*3 + (1500+854-3)*3 + 11
    //   = 854 + 6453 + 6753 + 7053 + 11
    //   = 21124
  }
}
