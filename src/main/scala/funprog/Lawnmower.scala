package funprog

class Lawnmower(x: Int, y: Int, orientation: Orientation.orientation) {

  def getCoordinate: (Int, Int) = (x, y)
  def getOrientation: Orientation.orientation = orientation
}
