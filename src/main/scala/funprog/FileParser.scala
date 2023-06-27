package funprog

import better.files._
import play.api.libs.json._
import play.api.libs.json.Json

object FileParser {

  implicit val dimensionsWrites: Writes[Dimensions] = Json.writes[Dimensions]
  implicit val pointWrites: Writes[Point] = Json.writes[Point]
  implicit val lawnmowerWrites: Writes[Lawnmower] = Json.writes[Lawnmower]
  implicit val mowerStateWrites: Writes[MowerState] = Json.writes[MowerState]
  implicit val lawnWrites: Writes[Lawn] = Json.writes[Lawn]

  private val inputFile: File = File(ConfigLoader.inputFileUrl)
  val inputFileAsList: List[String] = inputFile.lines.toList

  private val jsonOutputFile = File(ConfigLoader.jsonOutputFile)
  private val csvOutputFile = File(ConfigLoader.csvOutputFile)

  def writeToJson(output: Lawn): File = {
    val file = jsonOutputFile.createFileIfNotExists()
    val outputAsJson = Json.stringify(Json.toJson(output))
    file.appendLine(outputAsJson)
  }

  def writeToCsv(output: Lawn): Unit = {
    val file = csvOutputFile.createFileIfNotExists()
    file.appendLine(
      "numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions"
    )

    output.tondeuses.zipWithIndex.foreach {
      case (mowerState: MowerState, index: Int) =>
        file.appendLine(
          String.format(
            "%d;%d;%d;%s;%d;%d;%s;%s",
            index + 1,
            mowerState.debut.point.x,
            mowerState.debut.point.y,
            mowerState.debut.direction.toString,
            mowerState.fin.point.x,
            mowerState.fin.point.y,
            mowerState.fin.direction.toString,
            mowerState.instructions.mkString("")
          )
        )
    }
  }

}
