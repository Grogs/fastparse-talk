package linktext.after

import fastparse.NoWhitespace._
import fastparse.{CharIn, CharsWhile, End, P, Start, EagerOpsStr}

import scala.util.Try

sealed trait Token
case class Variable(name: String) extends Token
case class Literal(value: String) extends Token

case class LinkTextTemplate(tokens: Seq[Token]) {
  def render(variables: Map[String, String]): String = {
    val res = new StringBuilder()
    for (token <- tokens) {
      token match {

        case Literal(text) =>
          res.append(text)

        case Variable(name) =>
          res.append(variables(name))
      }
    }
    res.mkString

  }
}

object LinkTextTemplate {

  object Parser {
    def identifier[_: P] = P(CharIn("a-z_").rep.!)

    def variable[_: P] = P("{" ~/ identifier ~/ "}").map(Variable)

    def literal[_: P] = P(CharsWhile(c => c != '{' && c != '}').!).map(Literal)

    def template[_: P] = P(Start ~ (literal | variable).rep ~ End).map(LinkTextTemplate(_))
  }

  def parse(input: String): Try[LinkTextTemplate] = {
    val parsed = fastparse.parse(input, Parser.template(_), verboseFailures = true)

    Try(parsed.get.value)
  }
}