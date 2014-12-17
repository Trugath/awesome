package awesome

import scala.util.{Success, Failure}

object Main {
  def main(args : Array[String]): Unit = {
    val sourceFileName = args(0)
    val lines =  io.Source.fromFile(sourceFileName).getLines().toList.mkString
    new Parser(lines).InputLine.run() match {
      case Failure(err)    => err.printStackTrace()
      case Success(module) => CodeGenerator.generateClass(sourceFileName, module)
    }
  }
}

