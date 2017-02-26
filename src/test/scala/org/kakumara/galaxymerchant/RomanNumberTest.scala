package org.kakumara.galaxymerchant

import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table

class RomanNumberTest extends FlatSpec with Matchers {

  val testData = Table(
    ("roman", "decimal"),
    ("I", 1),
    ("II", 2),
    ("X", 10),
    ("VIII", 8),
    ("XII", 12),
    ("IX", 9),
    ("IV", 4),
    ("XXX", 30),
    ("XLIV", 44),
    ("XLVI", 46),
    ("XC", 90),
    ("D", 500),
    ("CD", 400),
    ("CM", 900),
    ("CMXCIX", 999),
    ("MCIII", 1103),
    ("MMM", 3000)
  )

  "RomanNumber" should "convert to decimal" in {
    forAll(testData) { (roman, decimal) =>
      RomanNumber(roman).toInt shouldBe decimal
    }
  }
}
