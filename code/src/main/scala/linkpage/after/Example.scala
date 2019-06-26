package linkpage.after

object Example extends App {
  val rawUrls = Seq("LandingPage", "SearchPage()", "SearchPage(petIncluded=true)", "SearchPage(petIncluded=true,sleepsMinimum=2)", "OtherPage", "SearchPage(petIncluded=invalid)","SearchPage(", "SearchPage(invalid)")

  val urls = rawUrls.map(LinkPage.parse)

  urls.foreach(println)
}