package com.tiny.lang.scala.generic.variance

/**
  * covariance：readonly, non-writable
  * 即：入参：限定不能将A & A的子类传递给Foo
  * 返回值：无限制
  */
trait Foo0[+A] {

  // compile successfully, return type is unlimited.
  def fun0(): A
  def fun1[B](): B

  // Covariant type A occurs in contravariant position in type A of value B
  //def fun2[B <: A](): B
  def fun3[B >: A](): B

  // Covariant type A occurs in contravariant position in type A of value b
 //def fun4[B >: A](a: A): A

  def fun5[B >: A](b: B): A

  def fun6[B](b: B)(op: A => B): A

  def fun7[B >: A](b: B)(op: A => B): B

  def fun8[B >: A](b: B)(op: B => B): B

  //def fun9[B >: A](b: B)(op: A => A): B

  //def fun10[B >: A](b: B)(op: B => A): B
}

/**
  * write-only
  * 返回值不能为A
  */
trait Foo1[-A] {

  def fun1[B <: A](): B

  // def fun1[B >: A](): B

  def fun2[B <: A](a: A): B

  // def fun2[B <: A](a: B): B

  // def fun3[B <: A](a: B): A

  def fun4[B <: A](a: B)(op: B => A): B

  // def fun4[B <: A](a: B)(op: B => B): B

  // def fun4[B <: A](a: B)(op: A => A): B

  // def fun4[B <: A](a: B)(op: A => B): B

  // def fun1[B <: A](u: B)(op: B => A): A => B

  // def fun1[B <: A](u: B)(op: B => A): B => B

  // def fun1[B <: A](u: B)(op: B => A): B => A

  // def fun1[B <: A](u: B)(op: B => A): A => A
}
