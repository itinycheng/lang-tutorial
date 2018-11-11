package com.tiny.lang.scala.generic

/**
  * @author tiny.wang
  */
object GenericExtraMain {

  def main(args: Array[String]): Unit = {
    val arr = test1[Fruit](new Apple, new Orange)
    arr.foreach(f => f.printName())
  }

  /**
    * focus on byte code decompile
    */
  def test1[T: Manifest](first: T, second: T): Array[T] = {
    println("-------test1---------")
    val arr = new Array[T](2)
    arr(0) = first
    arr(1) = second
    arr
  }

}
