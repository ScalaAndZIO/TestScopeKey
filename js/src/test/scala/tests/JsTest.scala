package tests

import mains.JsMain
import org.querki.jquery._
import org.scalatest.FunSuite


class JsTest extends FunSuite {
  JsMain.setupUI()

  test("it should be true ") {
    assert($("p:contains('Hello World')").length == 1)
  }

}