package org.kakumara.galaxymerchant

import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._


class QueryParserTest extends FlatSpec with Matchers {

  val VALID = true
  val INVALID = false
  val IS_A_STATEMENT = false
  val IS_A_QUERY = true

  val testData = Table(
    ("stmt", "valid", "isQuery", "tokensSize"),
    ("glob  is   I", VALID, IS_A_STATEMENT, 1),
    ("prok   is   V", VALID, IS_A_STATEMENT, 1),
    ("pish is X", VALID, IS_A_STATEMENT, 1),
    ("tegj is L", VALID, IS_A_STATEMENT, 1),
    ("glob  glob  Silver  is 34  Credits ", VALID, IS_A_STATEMENT, 3),
    ("glob prok Gold is 57800 Credits", VALID, IS_A_STATEMENT, 3),
    ("pish pish Iron is 3910 Credits", VALID, IS_A_STATEMENT, 3),
    ("how much is pish tegj glob glob ?", VALID, IS_A_QUERY, 4),
    ("how much is pish tegj glob glob ?", VALID, IS_A_QUERY, 4),
    ("how   many Credits is glob prok Silver ?", VALID, IS_A_QUERY, 3),
    ("how many Credits is glob prok Gold ?", VALID, IS_A_QUERY, 3),
    ("how many Credits is glob prok Iron ?", VALID, IS_A_QUERY, 3),
    ("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?", INVALID, false, 0)
  )

  "QueryParser" should "parse queries and statements" in {

    forAll(testData) { (stmt, expectedValid, query, tokensSize) =>
      val parsedStmt = QueryParser.parse(stmt)
      parsedStmt.isDefined shouldBe expectedValid

      if (expectedValid) {
        parsedStmt.get.isQuery shouldBe query
        parsedStmt.get.getTokens.length shouldBe tokensSize
      }
    }
  }
}
