package mains
import shared.MyShared._

object MyMain {
  val x = 9
  def main(args: Array[String]): Unit = {
    println("Hello from util ")
    println("From shared value of y = "+ sharedY )
  }
}
