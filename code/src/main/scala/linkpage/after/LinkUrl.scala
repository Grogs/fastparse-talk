package linkpage.after

import fastparse.NoWhitespace._
import fastparse._

import scala.util.Try

sealed trait Filter
case class PetIncluded(value: Boolean) extends Filter
case class SleepsMinimum(num: Int) extends Filter

sealed trait LinkPage
case object LandingPage extends LinkPage
case class SearchPage(filters: Seq[Filter]) extends LinkPage

object LinkPage {

  object Parser {
    def positiveNumber[_: P]: P[Int] = P(CharIn("0-9").rep(1).!.map(_.toInt))
    def boolean[_:P]: P[Boolean] = P(("true" | "false").!.map(_.toBoolean))

    def petIncluded[_:P]: P[PetIncluded] = P("petIncluded=" ~/ boolean).map(PetIncluded)
    def sleepsMinimum[_:P]: P[SleepsMinimum] = P("sleepsMinimum=" ~/ positiveNumber).map(SleepsMinimum)

    def filter[_:P]: P[Filter] = P(petIncluded | sleepsMinimum)

    def filters[_:P]: P[Seq[Filter]] = P("(" ~ filter.rep(min = 0, ",") ~ ")")


    def landingPage[_: P]: P[LandingPage.type] = P("LandingPage").map(_ => LandingPage)
    def searchPage[_: P]: P[SearchPage] = P("SearchPage" ~/ filters).map(SearchPage)

    def url[_: P]: P[LinkPage] = P(Start ~/ (landingPage | searchPage) ~/ End)
  }

  def parse(input: String): Try[LinkPage] = {
    val parsed = fastparse.parse(input, Parser.url(_), verboseFailures = true)

    Try(parsed.get.value)
  }
}