package mytests

import org.scalatest.{FlatSpec, Matchers}

//Running test : util/testOnly *TestUtil
class TestUtil extends FlatSpec with Matchers {

  "x " should "be 9" in {
    assert(mains.MyMain.x == 9)
  }
}

//Output
//[info] TestUtil:
//[info] x
//[info] - should be 9
//[info] Run completed in 5 seconds, 404 milliseconds.
//[info] Total number of tests run: 1
//[info] Suites: completed 1, aborted 0
//[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
//[info] All tests passed.


//Running test :util/testOnly *TestUtil2
class TestUtil2 extends FlatSpec with Matchers {

  "x" should "be 9" in {
    assert(mains.MyMain.x == 8)
  }
}

//Output
//[info] TestUtil2:
//[info] x
//[info] - should be 9 *** FAILED ***
//[info]   9 did not equal 8 (TestUtil.scala:26)
//[info] Run completed in 1 second, 168 milliseconds.
//[info] Total number of tests run: 1
//[info] Suites: completed 1, aborted 0
//[info] Tests: succeeded 0, failed 1, canceled 0, ignored 0, pending 0
//[info] *** 1 TEST FAILED ***
//[error] Failed tests:
//[error] 	mytests.TestUtil2
//[error] (util / Test / testOnly) sbt.TestsFailedException: Tests unsuccessful
//[error] Total time: 45 s, completed Aug 26, 2019 11:32:59 AM