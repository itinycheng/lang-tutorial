package com.tiny.lang.scala.generic

import scala.collection.mutable.ListBuffer

/**
  * T <: upperBound（类型上界）
  * T >: lowerBound （类型下界）
  * T >: lowerBound <: upperBound （多重界定）
  *
  */
object GenericMain0 {

  def main(args: Array[String]): Unit = {
    val fruits = ListBuffer[Fruit](new Apple, new Orange, new Fruit)
    val fruitMed = ListBuffer[Orange](new Orange)
    val foods = ListBuffer[Food](new Orange, new Apple, new Food {})
    val objects = ListBuffer[AnyRef](new Apple, new Orange, new AnyRef)

    // compile error: generic must be specified clearly
    // val objects = ListBuffer[_](new Apple, new Orange, new AnyRef)
    test0(fruits)
    test1(fruits)
    test2(objects)
    // multi bounds
    test3(objects)
    test4(objects)
    test5(objects)
    test6(fruitMed)
    // upper & lower bounds
    test7(foods)
    test8(foods)
  }

  /**
    * define a variable whose type is ListBuffer(parent of the generic is `Fruit`)
    */
  def test0(list: ListBuffer[_ <: Fruit]): Unit = {
    println("-------test0---------")
    // compile error: list ++= List(new Fruit,new Orange,new Apple)
    list.foreach(_.printName())
  }

  /**
    * define a generic type T which has an upper bounds `Fruit`
    *
    */
  def test1[T <: Fruit](list: ListBuffer[T]): Unit = {
    println("-------test1---------")
    // compile error :list ++= List(new Fruit,new Orange,new Apple)
    list.foreach(_.printName())
  }

  /**
    * @see [[test0()]]
    */
  def test2(list: ListBuffer[_ >: Fruit]): Unit = {
    println("-------test2---------")
    list ++= List(new Apple, new Fruit)
    list.foreach(println)
  }

  /**
    * multi lower bounds
    *
    */
  def test3[T >: Fruit with Medicine](list: ListBuffer[T]): Unit = {
    println("-------test3---------")
    //compile error: list ++= List(new Food{})
    list.foreach(println)
  }

  /**
    *
    * @see [[test3()]]
    */
  def test4(list: ListBuffer[_ >: Fruit with Medicine]): Unit = {
    println("-------test4---------")
    // compile error: list ++= List(new Fruit)
    list.foreach(println)
  }

  /**
    * compare to [[test4()]], diff place is `++=`
    */
  def test5(list: ListBuffer[_ >: Fruit with Food]): Unit = {
    println("-------test5---------")
    list ++= List(new Fruit)
    list.foreach(println)
  }

  /**
    * multi upper bounds
    *
    * @see [[test3()]]
    */
  def test6[T <: Fruit with Food with Medicine](list: ListBuffer[T]): Unit = {
    println("-------test6---------")
    //compile error: list ++= List(new Orange)
    list.foreach(println)
  }


  /**
    * have upper & lower bounds at the same time
    * TODO: lower bounds seam have no effect.
    */
  def test7[T >: Apple <: Food](list: ListBuffer[T]): Unit = {
    println("-------test7---------")
    // compile error: list += new Fruit
    list.foreach(println)
  }

  /**
    * >: Apple & >: Orange are erased at compile time
    */
  def test8[T >: Apple <: Food](list: ListBuffer[T]): List[_ >: Apple <: Food] = {
    println("-------test8---------")
    list.foreach(println)
    List(new Orange, new Apple)
  }

  /**
    *
    */

}
