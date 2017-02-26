package org.kakumara.galaxymerchant

import org.kakumara.galaxymerchant.RomanDigit._

object Statement {

  def query(tokens: Array[String]): Statement = {
    new Statement(tokens.toSeq, true)
  }

  def statement(tokens: Array[String], creditValue: Int = 0, romanDigit: RomanDigit = null): Statement = {
    new Statement(tokens.toSeq, false, creditValue, romanDigit)
  }

}

class Statement(tokens: Seq[String], query: Boolean, creditValue: Int = 0, romanDigit: RomanDigit = null) {

  def isQuery() = this.query

  def getTokens() = tokens
}




