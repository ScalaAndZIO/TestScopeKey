package mains
import org.scalajs.dom
import org.scalajs.dom.document
import org.querki.jquery._
import scala.scalajs.js.annotation.JSExportTopLevel


//This is js main that append a paragraph to the html
//Running main :
//1- js/fastOptJS
//2- open MyHtml file
object JsMain {

  def main(args: Array[String]): Unit = {
    appendPar(document.body, "Hello World")
    addClickedMessage()
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  //For test purposes
  def setupUI(): Unit = {
    $("body").append("<p>Hello World</p>")
    $("#click-me-button").click(() => addClickedMessage()).appendTo($("body"))
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }
}
