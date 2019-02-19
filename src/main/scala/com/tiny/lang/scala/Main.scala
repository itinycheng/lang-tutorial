package com.tiny.lang.scala

object Main {

  def main(args: Array[String]): Unit = {
    implicitly[List[String]].foreach(println)
  }

}
