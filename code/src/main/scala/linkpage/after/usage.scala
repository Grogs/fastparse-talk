package linkpage.after

object usage extends App {
  val rawUrls = Seq("LandingPage",
                    "SearchPage()",
                    "SearchPage(petIncluded=true)",
                    "SearchPage(petIncluded=true,sleepsMinimum=2)",
                    "OtherPage",
                    "SearchPage(petIncluded=invalid)",
                    "SearchPage(",
                    "SearchPage(invalid)")

  val urls = rawUrls.map(LinkUrl.parse)

  urls.foreach(println)
}