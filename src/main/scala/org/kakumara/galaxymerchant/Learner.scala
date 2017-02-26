package org.kakumara.galaxymerchant

import org.kakumara.galaxymerchant.RomanDigit.RomanDigit
import scala.collection.mutable

object Learner extends Learner

trait Learner {

  val ROMAN_DIGIT_LITERALS: mutable.Map[RomanDigit, List[String]] = mutable.Map.empty
  val METAL_PRICES: mutable.Map[String, Double] = mutable.Map.empty[String, Double]

  def feed(statement: Statement) = {
    if (statement.isLiteralUpdate) {
      updateDigitLiterals(statement.getRomanDigit, statement.getTokens.head)
    } else if (statement.isRateAdjustment) {
      updateMetalPrices(statement.getTokens, statement.getCreditValue)
    }

  }

  private def updateDigitLiterals(romanDigit: RomanDigit, literal: String) = {
    val literals = ROMAN_DIGIT_LITERALS.getOrElse(romanDigit, List.empty[String])
    ROMAN_DIGIT_LITERALS += (romanDigit -> (literal :: literals))
  }

  private def makeRomanString(tokensWithMetal: Seq[String]) = {
    val tokens = tokensWithMetal.dropRight(1)

    tokens.flatMap(token =>
      ROMAN_DIGIT_LITERALS.filter(entry => entry._2.contains(token))
        .map(entry => entry._1.toString))
        .mkString
  }

  private def updateMetalPrices(tokensWithMetal: Seq[String], creditForUnits: Int) = {
    val metal = tokensWithMetal.last
    val romanString = makeRomanString(tokensWithMetal)
    val noOfUnits = RomanNumber(romanString).toInt

    METAL_PRICES.put(metal, (creditForUnits / noOfUnits).toDouble)
  }

}
