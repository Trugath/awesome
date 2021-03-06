package awesome

sealed trait Expr
final case class Module(expressions : List[Expr])
final case class BinOp(op : String, left : Expr, right : Expr) extends Expr
final case class Num(value : Int) extends Expr

