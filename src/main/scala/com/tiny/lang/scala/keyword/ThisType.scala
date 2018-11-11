package com.tiny.lang.scala.keyword

/**
  * @author tiny.wang
  */
object ThisType {

  def main(args: Array[String]): Unit = {
    new Book().setTitle("title").setName("name")
  }

}

class Document {
  private var title = ""

  def setTitle(title: String): this.type = {
    this.title = title
    this
  }
}

class Book extends Document {

  private var name = ""

  def setName(name: String): this.type = {
    this.name = name
    this
  }
}