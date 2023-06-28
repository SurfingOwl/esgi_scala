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

  def inputFileAsList(inputFile: File): List[String] = inputFile.lines.toList

  def writeToJson(output: Lawn, outputFile: File): File = {
    val file = outputFile.createFileIfNotExists();
    val outputAsJson = Json.stringify(Json.toJson(output))
    file.appendLine(outputAsJson)
  }

  def writeToCsv(output: Lawn, fileOutput: File): File = {
    val file = fileOutput.createFileIfNotExists()
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
    file
  }

}
