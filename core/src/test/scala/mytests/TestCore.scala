package mytests

import org.scalatest.{FlatSpec, Matchers}

//This test assumed to test the value of a in core main
//Running test : core/test
class TestCore extends FlatSpec with Matchers {

  "a " should "be 4" in {
    assert(mains.CoreMain.a == 4)
  }
}
//Test output
//[info] TestCore:
//[info] a
//[info] - should be 4
//[info] Run completed in 2 seconds, 408 milliseconds.
//[info] Total number of tests run: 1
//[info] Suites: completed 1, aborted 0
//[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
//[info] All tests passed.