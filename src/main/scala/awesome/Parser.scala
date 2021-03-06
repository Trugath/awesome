package awesome

import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader

import scala.util.parsing.combinator.RegexParsers

class Parser extends RegexParsers {

  import scala.language.postfixOps

  def program: Parser[Module] =
    ((expression <~ ";")*) ^^ { case expressions => Module(expressions) }

  def expression: Parser[Expr] =
    sum

  def sum: Parser[Expr] = {
    (product ~ ((("+" | "-") ~ product)*)) ^^ { case head ~ tail =>
      foldBinOps(head, tail)
    }
  }

  def product: Parser[Expr] = {
    (number ~ ((("*" | "/") ~ number)*)) ^^ { case head ~ tail =>
      foldBinOps(head, tail)
    }
  }

  def number: Parser[Num] = {
    ("[0-9]+" r) ^^ { case value =>
      Num(Integer.parseInt(value))
    }
  }

  def foldBinOps(head: Expr, tail: List[String ~ Expr]): Expr = {
    tail.foldLeft(head)((left, right) => BinOp(right._1, left, right._2) )
  }

  def parse(inputStream: InputStream): Either[String, Module] = {
    val reader = new BufferedReader( new InputStreamReader(inputStream) )
    parseAll(program, reader) match {
      case Success(module, _) => Right(module)
      case NoSuccess(err, _)  => Left(err)
    }
  }
}

