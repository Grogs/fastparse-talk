package linktext.after

object ParsingExample extends App {
  val rawTemplates = Seq("Template using {destination}",
                         "Template using {some_var}",
                         "This is { invalid")

  val templates = rawTemplates.map(LinkTextTemplate.parse)

  templates.foreach(println)
}


object UsageExample extends App {
  val template = LinkTextTemplate.parse("Template using {a} with {b}").get

  println(
    template.render(Map("a" -> "scala", "b" -> "fastparse"))
  )
}