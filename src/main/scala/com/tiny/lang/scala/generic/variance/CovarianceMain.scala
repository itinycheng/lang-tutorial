package com.tiny.lang.scala.generic.variance

import com.tiny.lang.scala.generic.{Apple, Food, Fruit, Orange}

import scala.language.higherKinds

/**
  * @author tiny.wang
  */
object CovarianceMain {

  def main(args: Array[String]): Unit = {
    // List
    val fruits: List[Fruit] = List(new Apple, new Orange)
    val foods: List[Food] = listFunctor.map(fruits)(fruit => fruit: Food)

    // Read
    val fruitRead: Read[Fruit] = new Read[Fruit] {
      override def read(s: String): Option[Fruit] = Some(new Apple)
    }
    val foodRead: Read[Food] = readFunctor.map(fruitRead)(fruit => fruit: Food)
    // upcast
    val functor = new Functor[Read] {
      override def map[A, B](fa: Read[A])(op: A => B): Read[B] = new Read[B] {
        override def read(s: String): Option[B] = Some(op(fa.read("").get))
      }
    }
    val read = upcast(functor, fruitRead)
    // print
    println(foods + "\n" + foodRead + "\n" + read)
  }

  val listFunctor: Functor[List] =
    new Functor[List] {
      def map[A, B](fa: List[A])(f: A => B): List[B] = fa match {
        case Nil => Nil
        case a :: as => f(a) :: map(as)(f)
      }
    }

  val readFunctor: Functor[Read] =
    new Functor[Read] {
      def map[A, B](fa: Read[A])(f: A => B): Read[B] =
        new Read[B] {
          def read(s: String): Option[B] =
            fa.read(s) match {
              case None => None
              case Some(a) => Some(f(a))
            }
        }
    }

  def upcast[F[_], A, B <: A](functor: Functor[F], fb: F[B]): F[A] =
    functor.map(fb)(b => b: A)
}
