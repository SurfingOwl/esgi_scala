package funprog

import funprog.Orientation._

trait LawnmowerInstructionInterpretor {

  def calculateLawnmowerNewCoordinate(lawnmower: Lawnmower, instructions: List[String]): Lawnmower = {
    instructions.foldLeft(lawnmower) { (lawnmower, instruction) =>
      instruction match {
        case "A" =>
          lawnmower.orientation match {
            case N => Lawnmower(lawnmower.x, lawnmower.y + 1, lawnmower.orientation)
            case E => Lawnmower(lawnmower.x - 1, lawnmower.y, lawnmower.orientation)
            case W => Lawnmower(lawnmower.x + 1, lawnmower.y, lawnmower.orientation)
            case S => Lawnmower(lawnmower.x, lawnmower.y - 1, lawnmower.orientation)
          }
        case "D" =>
          lawnmower.orientation match {
            case N => Lawnmower(lawnmower.x, lawnmower.y, E)
            case E => Lawnmower(lawnmower.x, lawnmower.y, S)
            case W => Lawnmower(lawnmower.x, lawnmower.y, N)
            case S => Lawnmower(lawnmower.x, lawnmower.y, W)
          }
        case "G" =>
          lawnmower.orientation match {
            case N => Lawnmower(lawnmower.x, lawnmower.y, W)
            case E => Lawnmower(lawnmower.x, lawnmower.y, N)
            case W => Lawnmower(lawnmower.x, lawnmower.y, S)
            case S => Lawnmower(lawnmower.x, lawnmower.y, E)
          }
      }
    }
  }
}
