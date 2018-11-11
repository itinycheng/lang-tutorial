package com.tiny.lang.scala.generic

/**
  * @author tiny.wang
  */
object GenericMain3 {

  /**
    * 协变 & 逆变
    *
    * Covariant：case List[+V] : when (T extend R) then List[T] extends List[R]
    * Contravariant：case List[-V] : when (T extend R) then List[R] extends List[T]
    */
  def main(args: Array[String]): Unit = {
    test0(new Pair0[Fruit](new Orange, new Apple))
    test1(new Pair1[Fruit](new Apple, new Orange))
    test1(new Pair1[Apple](new Apple, new Apple))
    test1(new Pair1[Orange](new Orange, new Orange))
    test2(new Pair2[Food](new Apple, new Orange))
  }

  /**
    * 不变
    */
  def test0(pair: Pair0[Fruit]): Unit = {
    println("-------test0---------")
    println(pair)
  }

  /**
    * 协变点
    */
  def test1(pair: Pair1[Fruit]): Unit = {
    println("-------test1---------")
    println(pair)
  }

  /**
    * 逆变点
    */
  def test2(pair: Pair2[Fruit]): Unit = {
    println("-------test2---------")
    println(pair)
  }

}


class Pair0[T](t1: T, t2: T) {

  def first[U <: T](u: U)(op: (T, U) => T): T = {
    op(t1, u)
  }

  def second[U >: T](u: U)(op: (T, U) => T): T = {
    op(t2, u)
  }

}

class Pair1[+T](t1: T, t2: T) {

  def first[U](u: U)(op: (T, U) => U): U = {
    op(t1, u)
  }

  def second[U >: T](u: U)(op: (T, U) => U): U = {
    op(t2, u)
  }
}

class Pair2[-T](t1: T, t2: T) {
  def first[U <: T]: U = null.asInstanceOf[U]
}


