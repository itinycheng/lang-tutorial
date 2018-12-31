package com.tiny.lang.scala.generic


object Implicits {

  implicit object FruitOrdering extends Ordering[Fruit] {
    override def compare(x: Fruit, y: Fruit): Int = {
      x.getClass.getName.compareTo(y.getClass.getName)
    }
  }

  /**
    * similar to `FruitOrdering`
    *
    * @return
    */
  implicit def ordering: Ordering[Fruit] = new Ordering[Fruit] {
    override def compare(x: Fruit, y: Fruit): Int = x.getClass.getName.compareTo(y.getClass.getName)
  }

  implicit def fruitToJuice(fruit: Fruit): Juice = {
    new Juice(fruit)
  }

  implicit def vegetableToJuice(vegetable: Vegetable): Juice = {
    new Juice(vegetable)
  }

  implicit def vegetableToSoup(vegetable: Vegetable): Soup = {
    new Soup(vegetable)
  }

}

