package com.tiny.lang.scala.generic

/**
  * @author tiny.wang
  */
object GenericMain3 {

  /**
    * 协变 & 逆变 & 不变
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

    // invariance
    val pair0: Pair0[Fruit] = new Pair0[Fruit](new Apple, new Orange)
    val pair0_0: Pair0[Fruit] = pair0
    println(pair0_0)

    // covariant
    val pair1: Pair1[Fruit] = new Pair1[Fruit](new Apple, new Orange)
    val pair1_0: Pair1[Fruit] = pair1
    val pair1_1: Pair1[Food] = pair1
    println(pair1_0 + ", " + pair1_1)

    // contravariant
    val pair2: Pair2[Fruit] = new Pair2[Fruit](new Apple, new Orange)
    val pair2_0: Pair2[Fruit] = pair2
    val pair2_1: Pair2[Apple] = pair2
    println(pair2_0 + ", " + pair2_1)
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

  def first[U](u: U)(op: (T, U) => U): T = {
    t1
  }

  def second[U >: T](u: U)(op: (T, U) => U): U = {
    t2
  }
}

class Pair2[-T](t1: T, t2: T) {

  def first[U](t: T): U = null.asInstanceOf[U]

  def second[U <: T](t: T): U = null.asInstanceOf[U]

}
