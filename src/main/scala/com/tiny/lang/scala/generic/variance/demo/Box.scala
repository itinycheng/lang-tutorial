package com.tiny.lang.scala.generic.variance.demo

import scala.collection.mutable.ListBuffer

/**
  * instances of Box are read-only because of covariance
  *
  */
class Box[+T](list: List[T]) {

  /**
    * compile error: because of covariance is readonly
    *
    * class Box[+T]
    * val list: ListBuffer[_ <: T] = new ListBuffer[T]
    * // instances of parent can't add to the List of child
    * def put[U >: T](u: U)(op: U => T) = list += op(u)
    * def addList(lis: ListBuffer[_ <: T]): Unit = list ++= lis
    */

  def head: T = list.head

  def foreach[U](f: T => U): Unit = list.foreach(f)

  def accept[R](boxVisitor: BoxVisitor[T, R]): R = {
    val list = new ListBuffer[R]
    foreach((t: T) => list += boxVisitor.visit(t))
    list.head
  }

}

object Box {

  def apply[T](list: List[T]): Box[T] = new Box(list)

}

class Fuel

class Plant

class Bamboo

class Energy

