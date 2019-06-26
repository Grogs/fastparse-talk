package linktext.after

object Example extends App {
  val rawTemplates = Seq("Template using {destination}", "Template using {some_var}", "This is an { invalid template")

  val templates = rawTemplates.map(LinkTextTemplate.parse)

  templates.foreach(println)
}