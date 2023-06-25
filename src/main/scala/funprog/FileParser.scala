package funprog

import better.files._
import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}

object FileParser {

  private val mapper =
    new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true)
  private val inputFile: File = File(ConfigLoader.inputFileUrl)
  val inputFileAsList: List[String] = inputFile.lines.toList

  private val jsonOutputFile = File(ConfigLoader.jsonOutputFile)
//  val csvOutputFile = File(ConfigLoader.csvOutputFile)

  def writeToJson(output: Lawn): File = {
    val file = jsonOutputFile.createFileIfNotExists()
    val outputAsJson = mapper.writeValueAsString(output)
    file.appendLine(outputAsJson)
  }

//  def writeToCsv(output: FileOutput): Unit = {

//  }

}
