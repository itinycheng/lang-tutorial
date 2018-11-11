package com.tiny.lang.scala.generic

/**
  * @author tiny.wang
  */
object GenericMain2 {

  /**
    * 类型约束
    * T =:= U 测试T是否等同于U
    * T <:< U 测试T是否U的子类
    * T <%< U 测试T是否可被隐式转换为U (deprecated)
    */
  def main(args: Array[String]): Unit = {
    test0(new Orange)
    test1(List(new Orange, new Apple))
    test2(List(new Orange, new Apple))

  }

  /**
    * restrict: T eq Orange
    */
  def test0[T](i: T)(implicit ev: =:=[T, Orange]): Unit = {
    println("-------test0---------")
    println(i)
  }

  /**
    * same as [[test0()]]
    */
  def test1[T](i: T)(implicit ev: T =:= List[Fruit]): Unit = {
    println("-------test1---------")
    i.foreach(println)
  }


  /**
    * TODO
    * difference with {{{T <: Fruit}}}
    */
  def test2[T](list: List[T])(implicit ev: T <:< Fruit): Unit = {
    println("-------test1---------")
    val lis = new Fruit :: list
    lis.foreach(println)
  }

  /*// <%< has been deprecated
  def test2[T](list: List[T])(implicit ev: T <%< Juice): Unit = {
    println("-------test1---------")
    val lis = new Fruit :: list
    lis.foreach(println)
  }*/

}
