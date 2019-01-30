package com.tiny.lang.scala.generic.variance.demo

object Main {

  def main(args: Array[String]): Unit = {
    val boxInt = Box(1,2,3)
    println(boxInt.head)
    val boxBamboo = Box(new Bamboo,new Bamboo)
    println(boxBamboo.accept(new Panda))
  }

}
