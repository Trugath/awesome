package awesome

import org.parboiled2.{Parser => ParBoiledParser, CharPredicate, Rule0, Rule1, ParserInput}

import scala.language.implicitConversions

class Parser(val input: ParserInput) extends ParBoiledParser  {

  implicit def wspStr(s: String): Rule0 = rule {
    zeroOrMore(ch(' ')) ~ str(s) ~ zeroOrMore(ch(' '))
  }

  implicit def wspCh(s: Char): Rule0 = rule {
    zeroOrMore(ch(' ')) ~ ch(s) ~ zeroOrMore(ch(' '))
  }

  def InputLine: Rule1[Module] = rule {
    Program ~ EOI
  }

  def Program: Rule1[Module] = rule {
    oneOrMore( Expression ) ~> ( (x: Seq[Expr]) => Module(x.toList) )
  }

  def Expression: Rule1[Expr] = rule {
    Term ~ zeroOrMore(
      '+' ~ Term ~> ((a: Expr, b: Expr) => BinOp('+', a, b))
    | '-' ~ Term ~> ((a: Expr, b: Expr) => BinOp('-', a, b))) ~ ';'
  }

  def Term: Rule1[Expr] = rule {
    Factor ~ zeroOrMore(
      '*' ~ Factor ~> ((a: Expr, b: Expr) => BinOp('*', a, b))
    | '/' ~ Factor ~> ((a: Expr, b: Expr) => BinOp('/', a, b)))
  }

  def Factor: Rule1[Expr] = rule {
    Number | Parens
  }

  def Parens: Rule1[Expr] = rule {
    '(' ~ Expression ~ ')'
  }

  def Number: Rule1[Num] = rule {
    capture(Digits) ~> ((i: String) => Num(i.toInt))
  }

  def Digits: Rule0 = rule {
    oneOrMore(CharPredicate.Digit)
  }
}

