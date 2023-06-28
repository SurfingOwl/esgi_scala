package fr.esgi.al.funprog

import better.files.File
import funprog.{ConfigLoader, Dimensions, FileParser, Lawn, Lawnmower, LawnmowerInstructionInterpreter, MowerState, Orientation, Point}

object Main extends App with LawnmowerInstructionInterpreter {

  private val instructions: List[String] = FileParser.inputFileAsList(File(ConfigLoader.inputFileUrl))

  private val dimensions: Option[Dimensions] = instructions.headOption.flatMap { line =>
    line.split(" ").map(_.toInt).toList match {
      case x :: y :: Nil => Some(Dimensions(x, y))
      case _ => None
    }
  }

  private val lawnmowers: List[List[String]] = instructions.drop(1).grouped(2).toList

  private val mowersState: List[MowerState] = lawnmowers.flatMap { params =>
    params match {
      case initParams :: instructions :: Nil =>
        initParams.split(" ").toList match {
          case xStr :: yStr :: orientationStr :: Nil =>
            val xOpt = xStr.toIntOption
            val yOpt = yStr.toIntOption
            val orientationOpt = orientationStr match {
              case "N" => Some(Orientation.N)
              case "E" => Some(Orientation.E)
              case "W" => Some(Orientation.W)
              case "S" => Some(Orientation.S)
              case _ => None
            }

            (xOpt, yOpt, orientationOpt) match {
              case (Some(x), Some(y), Some(orientation)) =>
                val startingPosition = Lawnmower(Point(x, y), orientation)
                val mowerInstructions = instructions.trim.split("").toList
                val endingPosition = calculateLawnmowerNewCoordinate(startingPosition, mowerInstructions)
                Some(MowerState(startingPosition, mowerInstructions, endingPosition))
              case _ => None
            }
          case _ => None
        }
      case _ => None
    }
  }

  private val lawn: Lawn = dimensions match {
    case Some(dim) => Lawn(dim, mowersState)
    case None => Lawn(Dimensions(0, 0), Nil)
  }

  val json = FileParser.writeToJson(lawn, File(ConfigLoader.jsonOutputFile))
  val csv = FileParser.writeToCsv(lawn, File(ConfigLoader.csvOutputFile))
}
