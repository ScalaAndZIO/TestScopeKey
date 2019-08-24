package mains

import shared.MyShared._

object CoreMain {
  val a = 4
  def main(args: Array[String]): Unit = {
    println("Hello from core ")
    println("From shared value of x ="+ sharedX )
  }
}
