package funprog

case class Lawn(cases: List[List[Case]]) {

  def init(xSize: Int, ySize: Int): Lawn = {
    val rows = for (x <- 0 until xSize) yield {
      val columns = for (y <- 0 until ySize) yield {
        Case(x, y, occupied = false)
      }
      columns.toList
    }
    Lawn(rows.toList)
  }
}
