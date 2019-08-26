package mytests

import org.scalatest.{FlatSpec, Matchers}

//Running test : native/testOnly *TestNative
class TestNative extends FlatSpec with Matchers {

  "n " should "be 6" in {
    assert(mains.MyNative.n == 6)
  }
}

//Output
//[info] Linking (8885 ms)
//[info] Discovered 2323 classes and 15308 methods
//[info] Optimizing (debug mode) (11143 ms)
//[info] Generating intermediate code (4122 ms)
//[info] Produced 68 files
//[info] Compiling to native code (7188 ms)
//[info] Linking native code (boehm gc) (1000 ms)
//[info] Starting process '/home/naseem/Documents/SFreely NEW/AggregationProjcet/native/target/scala-2.11/native-out' on port '30240'.
//[info] TestNative:
//[info] n
//[info] - should be 6
//[info] Run completed in 675 milliseconds.
//[info] Total number of tests run: 0
//[info] Suites: completed 0, aborted 0
//[info] Tests: succeeded 0, failed 0, canceled 0, ignored 0, pending 0
//[info] No tests were executed.