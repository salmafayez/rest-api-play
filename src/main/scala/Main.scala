object Main {
  def main(args: Array[String]): Unit = {
    val properties = List("1", "2")
    val services = List("1", "2","3", "4")
    val servicesProperties = List(("1", "1"), ("1", "2"), ("1", "3"), ("2", "1"))

    val s = for {
      p  <- properties if p == "2"
      sp <- servicesProperties if sp._1 == p
      s  <- services if sp._2 == s
    }yield s

    s.foreach(println)
  }
}