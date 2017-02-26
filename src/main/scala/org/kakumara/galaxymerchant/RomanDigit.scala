package org.kakumara.galaxymerchant

object RomanDigit extends Enumeration {

  type RomanDigit = Value

  val I = Value(1)
  val V = Value(5)
  val X = Value(10)
  val L = Value(50)
  val C = Value(100)
  val D = Value(500)
  val M = Value(1000)

  def toInt(): Int = {
    Value.id
  }
}
