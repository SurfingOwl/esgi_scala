package fr.esgi.al.funprog

import funprog.{Dimensions, FileParser, Lawn, Lawnmower, LawnmowerInstructionInterpretor, MowerState, Orientation}

object Main extends App with LawnmowerInstructionInterpretor {

  private val instructions: List[String] = FileParser.inputFileAsList

  private val dimensions = instructions.headOption match {
    case Some(line) =>
      line.split(" ").map(_.toInt).toList match {
        case x :: y :: Nil => Some(Dimensions(x, y))
        case _ => None
      }
    case None => None
  }

  private val lawnmowers = instructions.drop(1).grouped(2).toList

  private val mowersState = lawnmowers.flatMap { params =>
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
                val startingPosition = Lawnmower(x, y, orientation)
                val mowerInstructions = instructions.trim.split("").toList
                val endingPosition = calculateLawnmowerNewCoordinate(startingPosition, mowerInstructions)
                Some(MowerState(startingPosition, endingPosition, mowerInstructions))
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
  val file = FileParser.writeToJson(lawn)
}
