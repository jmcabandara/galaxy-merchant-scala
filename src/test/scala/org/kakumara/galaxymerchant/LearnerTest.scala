package org.kakumara.galaxymerchant

import org.kakumara.galaxymerchant.RomanDigit._
import org.kakumara.galaxymerchant.Statement.statement
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

class LearnerTest extends FlatSpec with Matchers with BeforeAndAfter {

  before {
    Learner.feed(statement(Array[String]("aaa"), romanDigit = I))
    Learner.feed(statement(Array[String]("bbb"), romanDigit = V))
    Learner.feed(statement(Array[String]("ccc"), romanDigit = X))
    Learner.feed(statement(Array[String]("ddd"), romanDigit = L))
    Learner.feed(statement(Array[String]("fff"), romanDigit = C))
    Learner.feed(statement(Array[String]("ggg"), romanDigit = D))
    Learner.feed(statement(Array[String]("hhh"), romanDigit = M))
  }

  "Learner" should "feed digit literal correctly" in {
    Learner.feed(statement(Array[String]("ccc", "aaa", "aaa", "Silver"), creditValue = 1200))

    Learner.METAL_PRICES("Silver") shouldBe 100.0
  }

}
