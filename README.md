## Implemented in scala
here are output from the application:
```sampled merged iterator after 1000000 in 610 millis:
 1000005
 1617492
 666671
 1000006
 1617496
 666673
 1000009
 1617505
 666674
 1000010
 1617506
 approximate sparsities in 2216 millis by 1000000 elems with 4 cores:
 (2,Success(0.499999750000125))
 (3,Success(0.33333300000033333))
 (4,Success(0.2500263777828561))
 (5,Success(0.20185533310873563))
 (6,Success(0.17515797497763233))
 (7,Success(0.12411635362039956))
 sampled merged iterator after 10000000 in 2266 millis:
 10000008
 16833859
 6666670
 10000015
 16833861
 6666672
 10000016
 16833862
 6666675
 10000023
 16833865
 approximate sparsities in 262214 millis by 10000000 elems with 4 cores:
 (0,Failure(java.lang.ArithmeticException: / by zero))
 (1,Failure(java.lang.IllegalArgumentException: requirement failed: sparsity of 1 is not supported))
 (2,Success(0.5))
 (3,Success(0.33333338888889813))
 (4,Success(0.2500922089974574))
 (5,Success(0.1980380254000799))
 (6,Success(0.17317002061208123))
 (7,Success(0.15491799570815185))
 (8,Success(0.1032671591525678))
 (9,Success(0.06998620718827354))
 (10,Success(0.07143621459327423))
 (11,Success(0.11628740324728154))
 (12,Success(0.14467952407554813))
 (13,Success(0.15757439059404427))
 (14,Success(0.14204988351554426))
 (15,Success(0.11501208328448195))
 (16,Success(0.08757484354119284))
 (17,Success(0.060963774977414904))
 (18,Success(0.04185682273556462))
 (19,Success(0.026130355543100784))
 (20,Success(0.018629405986784656))
 (21,Success(0.010291265569608084))
 (22,Success(0.0062133264811940455))
 (23,Success(0.003446775528223261))
 
 Process finished with exit code 0
```
This simple project requires sbt, JDK 8 and scala 2.10.

There are two alternative and mostly identical tasks, one of which is in Java and other in Scala.

In both versions there is one file to be edited, either
    src/main/java/captify/test/java/TestAssignment.java 
or 
    src/main/scala/captify/test/scala/TestAssignment.scala



The methods to fill are documented and throw placeholder exceptions. 

Also, looking at the test harness could clarify the assignments a bit more, so see
    src/main/java/captify/test/java/SparseIteratorsApp.java
or 
    src/main/scala/captify/test/scala/TestAssignment.scala

To run this you just issue `sbt run` in the project root and then choose accordingly to run respective harness.
This of course presumes you've installed all the requirements mentioned above.

General requirements for this are:
 * initialize a local git repo with initial commit containing original code
 * doing separate commit(s) for initial version and follow-up polishings is recommended
 * including one representative output of your local run(s) is required
 * all tests are expected to run successfully - so most of exceptions thrown should be reported, not propagated
 * try to show reasonably functional style - avoid side-effects and mutable state, as long as it does not impair performance
 * adding some unit-tests or several other test harnesses is recommended, but not required
 * harness takes 5 minutes on my 4-core machine (Intel Core i7-3537U CPU @ 2.00GHz) - comparable delay is perfectly fine, so please do not over-optimize
 * algorithm sketches and general ideas to optimize are welcome but not required

Time required to complete this should generally be under 4 hours, with some simple test cases added.
In case you spend more time (to add some recommended or otherwise interesting stuff) please commit a bit more often - so that your actual track record is visible.
