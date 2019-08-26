package mytests

import org.scalatest.{FlatSpec, Matchers}

class TestNative extends FlatSpec with Matchers {

  "n " should "be 6" in {
    assert(mains.MyNative.n == 6)
  }
}
