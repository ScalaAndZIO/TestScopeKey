package mains
import org.scalajs.dom
import org.scalajs.dom.document
import org.querki.jquery._
import scala.scalajs.js.annotation.JSExportTopLevel

object JsMain {

  def main(args: Array[String]): Unit = {
    appendPar(document.body, "Hello World")
  }

  def setupUI(): Unit = {
    $("body").append("<p>Hello World</p>")
    $("#click-me-button").click(() => addClickedMessage())
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
}

//object JsMain extends js.JSApp {
//
//  def main(): Unit = {}
//
//  @JSExportTopLevel("append")
//  def append() {
//    val name = $("#name").value()
//    $("#v").html("Hi " + name)
//  }
//}