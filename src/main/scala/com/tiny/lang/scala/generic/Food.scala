package com.tiny.lang.scala.generic

trait Food

trait Medicine

class Juice(food: Food) {
  def juice = "Juice"

  def printName(): Unit = {
    println("Juice")
  }
}

class Soup(food: Food) {
  def soup = "Soup"

  def printName(): Unit = {
    println("Soup")
  }
}

class Fruit extends Food {
  def printName(): Unit = {
    println("Fruit")
  }
}

class Vegetable extends Food {
  def printName(): Unit = {
    println("Vegetable")
  }
}

class Apple extends Fruit {
  override def printName(): Unit = {
    println("Apple")
  }
}

class Orange extends Fruit with Medicine {
  override def printName(): Unit = {
    println("Orange")
  }
}

