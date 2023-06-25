package funprog

import better.files._
import play.api.libs.json._
import play.api.libs.json.Json
object FileParser {

  implicit val dimensionsWrites: Writes[Dimensions] = Json.writes[Dimensions]
  implicit val lawnmowerWrites: Writes[Lawnmower] = Json.writes[Lawnmower]
  implicit val mowerStateWrites: Writes[MowerState] = Json.writes[MowerState]
  implicit val lawnWrites: Writes[Lawn] = Json.writes[Lawn]


  private val inputFile: File = File(ConfigLoader.inputFileUrl)
  val inputFileAsList: List[String] = inputFile.lines.toList

  private val jsonOutputFile = File(ConfigLoader.jsonOutputFile)
//  val csvOutputFile = File(ConfigLoader.csvOutputFile)

  def writeToJson(output: Lawn): File = {
    val file = jsonOutputFile.createFileIfNotExists()
    val outputAsJson = Json.stringify(Json.toJson(output))
    file.appendLine(outputAsJson)
  }

//  def writeToCsv(output: FileOutput): Unit = {

//  }

}
