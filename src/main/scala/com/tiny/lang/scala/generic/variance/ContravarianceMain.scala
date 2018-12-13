package com.tiny.lang.scala.generic.variance

import com.tiny.lang.scala.generic.{Apple, Fruit}

import scala.language.higherKinds

/**
  * @author tiny.wang
  */
object ContravarianceMain {

  def main(args: Array[String]): Unit = {
    val show = new Show[Fruit] {
      override def show(a: Fruit): String = "fruit"
    }
    val rtn = contraUpcast[Show, Apple, Fruit](showContravariant, show)
    println(rtn)
  }

  /**
    * 逆变：
    * 1. 将消费者Show[A]转换成消费者Show[B]
    * 2. Show[B]消费B时内部将B转换为A，
    * 3. 调用Show[A]并返回A消费结果,并作为Show[B]的结果
    */
  val showContravariant: Contravariant[Show] = new Contravariant[Show] {
    override def contraMap[A, B](fa: Show[A])(op: B => A): Show[B] = {
      new Show[B] {
        override def show(b: B): String = fa.show(op(b))
      }
    }
  }

  /**
    *逆变：可以把父类转变成子类，也可接收一个父类对象，返回一个逆变子类对象
    */
  def contraUpcast[F[_], A, B >: A](contra: Contravariant[F], fb: F[B]): F[A] =
    contra.contraMap(fb)((a: A) => a: B)
}
