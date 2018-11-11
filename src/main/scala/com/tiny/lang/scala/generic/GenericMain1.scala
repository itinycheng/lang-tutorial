package com.tiny.lang.scala.generic

import com.tiny.lang.scala.generic.Implicits.{FruitOrdering, fruitToJuice, vegetableToJuice, vegetableToSoup}

import scala.collection.mutable.ListBuffer

/**
  * T: contextBound （上下文界定），存在ContextBound[T]的隐式对象，
  * T <% viewBound （视图界定），存在从T到ViewBound的隐式转换，
  * view bounds are deprecated,use implicit parameter instead.
  */
object GenericMain1 {

  def main(args: Array[String]): Unit = {
    val fruits = ListBuffer[Fruit](new Apple, new Orange, new Fruit)
    val vegetable = ListBuffer(new Vegetable)

    // view bound
    test0(fruits)
    test1(fruits)
    // multi view-bounds
    test2(vegetable)
    test3(vegetable)
    // context bound
    test4(new Apple, new Orange)
    // multi context-bounds
    test5(new Apple, new Orange)

  }


  /**
    * view bounds are deprecated,use implicit parameter instead.
    * input parameter `T` can convert to `Juice`
    */
  def test0[T <% Juice](list: ListBuffer[T]): Unit = {
    println("-------test0---------")
    list.foreach(_.printName())
  }

  /**
    * same as [[test0()]]
    */
  def test1[T](list: ListBuffer[T])(implicit fun: T => Juice): Unit = {
    println("-------test1---------")
    list.foreach(_.printName())
  }

  /**
    * multi view bound
    */
  def test2[T <% Juice <% Soup](list: ListBuffer[T]): Unit = {
    println("-------test2---------")
    list.foreach(println)
    list.foreach(i => println(i.juice))
    list.foreach(i => println(i.soup))
  }

  /**
    * @see [[test2()]]
    */
  def test3[T](list: ListBuffer[T])(implicit ev$1: T => Juice, ev$2: T => Soup): Unit = {
    println("-------test3---------")
    // compile error: list.foreach(_.printName())
    list.foreach(println)
    list.foreach(i => println(i.juice))
    list.foreach(i => println(i.soup))
  }

  /**
    * use `implicit` parameter or else `Fruit extends Ordering[Fruit]`
    * 必须存在一个类型为`Ordering[T]`的隐式值
    */
  def test4[T: Ordering](first: T, second: T)(implicit ordering: Ordering[T]): Unit = {
    println("-------test4---------")
    // compile error: val arr = new Array[T](2)
    val small = if (ordering.compare(first, second) < 0) first else second
    println(small)
  }

  /**
    * TODO Manifest vs ClassTag <br/>
    * multi context bounds <br/>
    * PreDef.scala contains implicit function `def manifest[T](implicit m: Manifest[T])` <br/>
    *
    * [[test4()]]
    */
  def test5[T: Ordering : Manifest](first: T, second: T)(implicit ordering: Ordering[T]): Unit = {
    println("-------test5---------")
    val arr = new Array[T](2)
    val small = if (ordering.compare(first, second) < 0) first else second
    println(arr)
    println(small)
  }

}
