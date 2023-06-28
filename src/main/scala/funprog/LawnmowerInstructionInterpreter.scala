package funprog

import funprog.Orientation._

trait LawnmowerInstructionInterpreter {

  def calculateLawnmowerNewCoordinate(lawnmower: Lawnmower, instructions: List[String]): Lawnmower = {
    instructions.foldLeft(lawnmower) { (lawnmower, instruction) =>
      instruction match {
        case "A" =>
          lawnmower.direction match {
            case N => Lawnmower(Point(lawnmower.point.x, lawnmower.point.y + 1), lawnmower.direction)
            case E => Lawnmower(Point(lawnmower.point.x + 1, lawnmower.point.y), lawnmower.direction)
            case W => Lawnmower(Point(lawnmower.point.x - 1, lawnmower.point.y), lawnmower.direction)
            case S => Lawnmower(Point(lawnmower.point.x, lawnmower.point.y - 1), lawnmower.direction)
          }
        case "D" =>
          lawnmower.direction match {
            case N => Lawnmower(lawnmower.point, E)
            case E => Lawnmower(lawnmower.point, S)
            case W => Lawnmower(lawnmower.point, N)
            case S => Lawnmower(lawnmower.point, W)
          }
        case "G" =>
          lawnmower.direction match {
            case N => Lawnmower(lawnmower.point, W)
            case E => Lawnmower(lawnmower.point, N)
            case W => Lawnmower(lawnmower.point, S)
            case S => Lawnmower(lawnmower.point, E)
          }
      }
    }
  }
}
