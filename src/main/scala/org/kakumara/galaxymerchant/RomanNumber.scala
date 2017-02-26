package org.kakumara.galaxymerchant

import org.kakumara.galaxymerchant.RomanDigit._

object RomanNumber {
  def apply(romanString: String): RomanNumber = new RomanNumber(romanString)
}

class RomanNumber(romanString: String) {
  val consecutiveChars = """(.*)\\1\\1\\1""".r
  val validPreDigits = Map(V -> I, X -> I, L -> X, C -> X, D -> C, M -> C)

  require(!romanString.isEmpty && !consecutiveChars.findFirstMatchIn(romanString).isDefined, "Invalid roman string")

  def toInt(): Int = {
    convert(romanString.toCharArray, 0)
  }

  private def convert(romanChars: Array[Char], total: Int): Int = {
    if (romanChars.length == 1) {
      val lastChar = romanChars(0)
      total + RomanDigit.withName(lastChar.toString).id
    } else {
      val lastRoman = RomanDigit.withName(romanChars(romanChars.length - 1).toString)
      val prevRoman = RomanDigit.withName(romanChars(romanChars.length - 2).toString)

      validPreDigits.getOrElse(lastRoman, null) match {
        case rd if rd == prevRoman =>
          val newTotal = total + (lastRoman.id - rd.id)
          if (romanChars.length == 2) newTotal
          else convert(romanChars.dropRight(2), newTotal)

        case _ => convert(romanChars.dropRight(1), total + lastRoman.id)
      }

    }
  }
}
