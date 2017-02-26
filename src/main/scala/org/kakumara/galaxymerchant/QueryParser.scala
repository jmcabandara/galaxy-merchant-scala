package org.kakumara.galaxymerchant

import org.kakumara.galaxymerchant.RomanDigit._
import org.kakumara.galaxymerchant.Statement.{query, statement}

import scala.util.matching.Regex.Match

object QueryParser extends QueryParser

trait QueryParser {

  val validQueryPattern = """how\s+(many|much)(\s+credits)?\s+(is)\s+(.*)\s+(\?)""".r

  val validStatementPatterns = Seq(
    """(.*)\s+(is)\s+(i|v|x|l|c|d|m)""".r,
    """(.*)\s+(is)\s+(\d+)\s+(credits)""".r)

  private def tokenizeInput(matches: Match, groupIdx: Int) = {
    matches.group(groupIdx).split(" ")
  }

  private def isNumeric(str: String): Boolean = {
    str matches """\d+"""
  }

  def parse(queryOrStmt: String): Option[Statement] = {
    val formattedStatement = queryOrStmt.trim.toLowerCase.replaceAll("\\s+", " ")

    validQueryPattern.findFirstMatchIn(formattedStatement) map {
      matches => return Some(query(tokenizeInput(matches, matches.groupCount - 1)))
    }

    validStatementPatterns.foreach {
      regex =>
        regex.findFirstMatchIn(formattedStatement) map {
          matches =>
            val value = matches.group(3)
            val tokens = tokenizeInput(matches, 1)
            if (isNumeric(value)) {
              return Some(statement(tokens, Integer.parseInt(value)))
            } else {
              return Some(statement(tokens, romanDigit = RomanDigit.withName(value.toUpperCase)))
            }
        }
    }
    None
  }

}

