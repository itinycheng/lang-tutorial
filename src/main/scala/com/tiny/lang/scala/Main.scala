package com.tiny.lang.scala

import com.sun.tools.javac.util.ListBuffer
import com.tiny.lang.scala.generic.Food

object Main {

  def main(args: Array[String]): Unit = {
    val name3 = new ListBuffer
    /*val name2 = new ListBuffer[_ <: Any]
    val name1 = new ListBuffer[_ <: Fruit]
    val buf4 = new ListBuffer[_ <: Fruit with Medicine with Serializable]*/
  }
}

/**
  *
  */
class A[_ <: Food]
