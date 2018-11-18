package com.tiny.lang.scala.generic.variance

import scala.language.higherKinds

trait Read[+A] {
  def read(s: String): Option[A]
}

trait Show[-A] {
  def show(a: A): String
}

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(op: A => B): F[B]
}