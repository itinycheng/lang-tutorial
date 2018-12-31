package com.tiny.lang.scala.generic.variance.demo

/**
  * write-only means T can't be returned
  * imply: can't be used as a return value of method
  */
trait BoxVisitor[-T, +R] {

  /**
    * compile error
    * def visit0(t: T): T
    */

  /**
    * def visit(t: T): R is invariance,
    * but because of Polymorphism（多态）, it's similar as method a def bellow
    * Polymorphism: childType U can be treated as ParentType
    */
  def visit[U <: T](u: U): R

  def visit[U <: T](u: U)(op: U => T): R
}

/**
  * 熊猫只吃竹子
  */
class Panda extends BoxVisitor[Bamboo, Energy] {

  /**
    * Type U accept Bamboo and his subClasses
    */
  override def visit[U <: Bamboo](bamboo: U): Energy = eat(bamboo)

  def eat(bamboo: Bamboo) = new Energy

  override def visit[U <: Bamboo](u: U)(op: U => Bamboo): Energy = eat(op(u))
}
