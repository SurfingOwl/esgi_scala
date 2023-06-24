package funprog

import better.files._
object FileParser {

  private val inputFile: File = File(ConfigLoader.inputFileUrl)
  val inputFileAsList = inputFile.lines.toList

  val jsonOutputFile = File(ConfigLoader.jsonOutputFile)
  val csvOutputFile = File(ConfigLoader.csvOutputFile)

  def writeToJson(lawn: Lawn): Unit = {

  }

  def writeToCsv(lawn: Lawn): Unit = {

  }

}
