package mytests

import org.scalatest.{FlatSpec, Matchers}

class MyTest extends FlatSpec with Matchers {

  "x " should "be 9" in {
    assert(mains.MyMain.x == 9)
  }
}


class MyTest2 extends FlatSpec with Matchers {

  "x" should "be 9" in {
    assert(mains.MyMain.x == 8)
  }
}
