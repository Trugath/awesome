package awesome

import cafebabe._
import ByteCodes._
import AbstractByteCodes._

class CodeGenerator {
  def generateClass(sourceFileName: String, module: Module) = {
    val cf = new cafebabe.ClassFile(binaryNameFor(sourceFileName), None)
    val ch = cf.addMainMethod().codeHandler
    visitModule(ch, module)
    ch << RETURN
    ch.freeze()
    cf.writeToFile(classFileNameFor(sourceFileName))

    val cl = new CafebabeClassLoader
    cl.register(cf)
  }

  def visitModule(ch: CodeHandler, module: Module): Unit = {
    module.expressions foreach { expr =>
      ch << GetStatic("java/lang/System", "out", "Ljava/io/PrintStream;")
      visitExpr(ch, expr)
      ch << InvokeVirtual("java/io/PrintStream", "println", "(I)V")
    }
  }

  def visitExpr(ch: CodeHandler, expr: Expr): Unit = {
    expr match {
      case binop: BinOp => visitBinOp(ch, binop)
      case num: Num => visitNum(ch, num)
    }
  }

  def visitBinOp(ch: CodeHandler, binop: BinOp): Unit = {
    visitExpr(ch, binop.left)
    visitExpr(ch, binop.right)
    binop.op match {
      case "+" => ch << IADD
      case "-" => ch << ISUB
      case "*" => ch << IMUL
      case "/" => ch << IDIV
    }
  }

  def visitNum(ch: CodeHandler, num: Num): Unit = {
    ch << Ldc(num.value)
  }

  /*
   * "examples/HelloWorld.awesome" -> "examples/HelloWorld"
   */
  def binaryNameFor(sourceFileName : String) : String =
    sourceFileName.replaceAll("\\.awesome$", "")

  /*
   * "examples/HelloWorld.awesome" -> "examples.HelloWorld"
   */
  def classNameFor(sourceFileName : String) : String =
    sourceFileName.replaceAll("\\.awesome$", "").replace("/", ".").replace("\\", ".")

  /*
   * "examples/HelloWorld.awesome" -> "examples/HelloWorld.class"
   */
  def classFileNameFor(sourceFileName : String) : String =
    sourceFileName.replaceAll("\\.awesome$", ".class")

}
