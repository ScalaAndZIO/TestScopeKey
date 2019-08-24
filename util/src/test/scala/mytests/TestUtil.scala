package mytests

import org.scalatest.{FlatSpec, Matchers}

class TestUtil extends FlatSpec with Matchers {

  "x " should "be 9" in {
    assert(mains.MyMain.x == 9)
  }
}


class TestUtil2 extends FlatSpec with Matchers {

  "x" should "be 9" in {
    assert(mains.MyMain.x == 8)
  }
}
