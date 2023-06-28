package funprog

import better.files.File
import org.scalatest.funsuite.AnyFunSuite

class LawnSpec extends AnyFunSuite with LawnmowerInstructionInterpreter {

  val lawn: Lawn = Lawn(
    Dimensions(2, 2),
    List(
      MowerState(
        Lawnmower(Point(0, 0), Orientation.N),
        List("A", "D", "D", "G", "A"),
        Lawnmower(Point(1, 1), Orientation.E)
      )
    )
  )


  test("LawnmowerInstructionInterpreter should calculate new lawnmower coordinate") {
    val lawnmower = Lawnmower(Point(0, 0), Orientation.N)
    val instructions = List("A", "D", "D", "G", "A")
    assert(
      Lawnmower(Point(1, 1), Orientation.E) === calculateLawnmowerNewCoordinate(
        lawnmower,
        instructions
      )
    )
  }

  test("""Instruction(ADGV) should throw exception "scala.MatchError" """) {
    assertThrows[scala.MatchError](
      calculateLawnmowerNewCoordinate(
        Lawnmower(Point(0, 0), Orientation.N),
        List("A", "D", "G", "V")
      )
    )
  }

  test("Configloader should load input txt file url") {
    assert(ConfigLoader.inputFileUrl === "src/main/resources/tmp/input.txt")
  }

  test("Configloader should load output json file url") {
    assert(ConfigLoader.jsonOutputFile === "src/main/resources/tmp/output.json")
  }

  test("Configloader should load output csv file url") {
    assert(ConfigLoader.csvOutputFile === "src/main/resources/tmp/output.csv")
  }

  test("FileParser should parse input file") {
    val inputAsList: List[String] = FileParser.inputFileAsList(File("src/test/resources/test_input.txt"))
    assert(inputAsList === List("2 2", "0 0 N", "ADDGA"))
  }

  test("FileParser should correctly write to JSON") {
    val file = FileParser.writeToJson(lawn, File("src/test/resources/test.json"))
    assert(file.exists && file.lines.toList.nonEmpty)
  }

  test("FileParser should correctly write to CSV") {
    val file = FileParser.writeToCsv(lawn, File("src/test/resources/test.csv"))
    assert(file.exists && file.lines.toList.nonEmpty)
  }
}
