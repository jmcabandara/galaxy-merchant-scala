package org.kakumara.galaxymerchant

import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.Tables.Table


class QueryParserTest extends FlatSpec with Matchers {
  val IS_A_STATEMENT = false
  val IS_A_QUERY = true

  val validTestData = Table(
    ("stmt", "isQuery", "tokensSize"),
    ("glob  is   I", IS_A_STATEMENT, 1),
    ("prok   is   V", IS_A_STATEMENT, 1),
    ("pish is X", IS_A_STATEMENT, 1),
    ("tegj is L", IS_A_STATEMENT, 1),
    ("glob  glob  Silver  is 34  Credits ", IS_A_STATEMENT, 3),
    ("glob prok Gold is 57800 Credits", IS_A_STATEMENT, 3),
    ("pish pish Iron is 3910 Credits", IS_A_STATEMENT, 3),
    ("how much is pish tegj glob glob ?", IS_A_QUERY, 4),
    ("how much is pish tegj glob glob ?", IS_A_QUERY, 4),
    ("how   many Credits is glob prok Silver ?", IS_A_QUERY, 3),
    ("how many Credits is glob prok Gold ?", IS_A_QUERY, 3),
    ("how many Credits is glob prok Iron ?", IS_A_QUERY, 3)
  )

  val invalidTestData = Table(
    "stmt",
    "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?",
    "gelb isn't I"
  )

  "QueryParser" should "parse valid queries and statements" in {

    forAll(validTestData) { (stmt, query, tokensSize) =>
      val parsedStmt = QueryParser.parse(stmt)
      parsedStmt.isDefined shouldBe true
      parsedStmt.get.isQuery shouldBe query
      parsedStmt.get.getTokens.length shouldBe tokensSize
    }

  }

  "QueryParser" should "not parse invalid queries and statements" in {
    forAll(invalidTestData) { (stmt) =>
      val parsedStmt = QueryParser.parse(stmt)
      parsedStmt.isDefined shouldBe false
    }
  }
}
