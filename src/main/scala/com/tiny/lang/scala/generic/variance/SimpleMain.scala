package com.tiny.lang.scala.generic.variance

/**
  * what's the difference between Function[T,R] and Function[-T, +R]
  */
object SimpleMain {

  def main(args: Array[String]): Unit = {
    // 协变
    val list = List[String]("A", "B", "CC")
    println(toInt(list))
    // 逆变
    val fun = (v1: CharSequence) => v1.toString.map(_.toInt)
    println(toInt(fun, "ABC"))
  }

  def toInt(fun: String => Seq[Int], in: String): Seq[Int] = fun(in)

  def toInt(list: List[CharSequence]): Seq[Int] = list.map(_.length)
}
