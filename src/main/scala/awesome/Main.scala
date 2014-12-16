package awesome

import java.io.FileInputStream

object Main {
  def main(args : Array[String]): Unit = {
    val sourceFileName = args(0)
    val inputStream = new FileInputStream(sourceFileName)
      new Parser().parse(inputStream) match {
      case Left(err)      => sys.error(err)
      case Right(module)  => CodeGenerator.generateClass(sourceFileName, module)
    }
    inputStream.close()
  }
}

