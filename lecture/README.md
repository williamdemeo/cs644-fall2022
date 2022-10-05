# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## LECTURE NOTES

The reading assignments below refer to the following:

+  [LS][] = [Learning Spark, 2nd edition](https://pages.databricks.com/rs/094-YMS-629/images/LearningSpark2.0.pdf)
+  [FPS][] = [Functional Programming in Scala, 2nd edition](https://livebook.manning.com/book/functional-programming-in-scala-second-edition)


### Week 1

Historical overview of Big Data, programming paradigms for Big Data.

+  **Lecture Slides**. [Week 1][]
+  **Required reading**. [LS][] Ch 1, [FPS Ch1][], [FPS Ch2][]
+  **Homework**. [HW 1][] due Mon 19 Sep 23:59.

+  **Recommended reading**.
   +  [Volume, velocity, and variety: Understanding the three V's of big data][], David Gewirtz, 2018.
   +  [Immutability Changes Everything][], Pat Helland, 2015.
+  **Related videos**. [ScalaDays 2019 Keynote lecture](https://youtu.be/_Rnrx2lo9cw) by Martin Odersky (creator of Scala).

### Week 2

Introduction to Functional Programming, immutability, referential transparency, higher-order functions.

+  **Lecture Slides**. [Week 2][]
+  **Required reading**. [FPS Ch3][], [FPS Ch4][]
+  **Homework**. (do the reading!)

+  **Recommended reading**. [Why should you care about referential transparency](https://blog.rockthejvm.com/referential-transparency/)
+  **Related videos**.
   +  **Installing an IDE**.
      +  **IntelliJ**. [Linux](https://youtu.be/cE4ZvjFkaHM), [Mac](https://youtu.be/5UDILzNBsRE), [Windows](https://youtu.be/AjpONViwMFU)
      +  **VSCode**. [Linux](https://youtu.be/Ayods37tM1s), [Mac](https://youtu.be/5UDILzNBsRE), [Windows](https://youtu.be/V3o57MU5eoE)


### Week 3

Functional data structures, recursion, the `Option[_]` functor, laziness. Hands-on programming in Scala. VSCode and the Metals plugin. IntelliJ and the Scala plugin.

+  **Lecture Slides**. [Week 3][] (coming soon)
+  **Required reading**. [FPS Ch5][], [FPS Ch6][]
+  **Homework**. (do the reading)

+  **Recommended reading**.
   +  [Scala Generics: a gentle introduction](https://blog.rockthejvm.com/scala-generics/)
   +  [Scala Option: a gentle introduction](https://blog.rockthejvm.com/scala-option/)
   +  [Scala 3 indentation](https://blog.rockthejvm.com/scala-3-indentation/)
+  **Related resources**.
   +  [Scala official website](https://www.scala-lang.org/)
   +  [Scala Installation](https://docs.scala-lang.org/getting-started/index.html)
   +  [Learning Resources](https://docs.scala-lang.org/)
+  **Related videos**.
   +  **Installing Scala**. [VSCode](https://youtu.be/iATmBDxlYAA), [IntelliJ](https://youtu.be/LWojiAi8PKQ)
   +  **Your first Scala program**. [VSCode](https://youtu.be/uVMGPrH5_Uc), [IntelliJ](https://youtu.be/7kfX1mg079g)
   +  [Immutable Data: your next superpower](https://youtu.be/0cjpBVNNSf0) (other [videos by 47degrees](https://www.youtube.com/c/47deg/videos))


### Week 4

Introduction to Spark. Transformations, actions, RDDs.  Datasets, Dataframes, Spark SQL.

+  **Lecture Slides**. [Week 4][] (coming soon)
+  **Required reading**. [FPS Ch7][], [LS][] Ch 2, 3.
+  **Homework**. TBD

+  **Recommended reading**.
   +  [Spark: a brief overview](../handouts/Slides_8-Steps-V2.pdf)
   +  [Tutorial: your first Spark program](https://www.47deg.com/blog/using-scala-3-with-spark/)
+  **Related resources**.
   +  [Spark official website](https://spark.apache.org/)
   +  [Spark Installation](https://spark.apache.org/downloads.html)
+  **Related videos**.
   +  **Installing Spark**. [Mac](https://youtu.be/6c1uP_UbuBg), [IntelliJ](https://youtu.be/UxeRRAsvDH4)
   +  **Your first Spark project**. [IntelliJ](https://youtu.be/faCaudZNb9w)


### Week 5

Introduction to Cloud Computing.

+  **Lecture Slides**. [Week 5][] (coming soon)
+  **Required reading**.  [LS][] Ch 4, 5.
+  **Homework**. TBD

+  **Recommended reading**.
   +  [Deploy a Scala App to AWS Lambda](https://blog.rockthejvm.com/lambda/)
+  **Related resources**.
+  **Related videos**.
   

### Miscellaneous Notes

For those of you having trouble with Windows, if you're not ready to remove Windows and replace it with Linux, and you're also reluctant to try using a dual-boot Windows/Linux machine, then there's a third option: [install Linux inside Windows!](https://youtu.be/v1JVqd8M3Yc).



[3V]: https://www.zdnet.com/article/volume-velocity-and-variety-understanding-the-three-vs-of-big-data/
[final exams]: https://www5.njit.edu/registrar/exams/finalexams.php

[FPS]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/
[FPS Ch1]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-1/v-6/
[FPS Ch2]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-2/v-6/
[FPS Ch3]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-3/v-6/
[FPS Ch4]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-4/v-6/
[FPS Ch5]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-5/v-6/

[FPS Ch6]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-6/v-6/
[FPS Ch7]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-7/v-6/
[FPS Ch8]: https://livebook.manning.com/book/functional-programming-in-scala-second-edition/chapter-8/v-6/


<!-- HW LINKS -->
[Gradescope]: https://www.gradescope.com/courses/441050

[HW 1]: https://www.gradescope.com/
[ICE]: https://www.cidrdb.org/cidr2015/Papers/CIDR15_Paper16.pdf
[Immutability Changes Everything]: https://www.cidrdb.org/cidr2015/Papers/CIDR15_Paper16.pdf

[KUPF]: https://goo.gl/maps/GjhP3cjrMAJSzVFt5
[KUPF 202]: https://goo.gl/maps/GjhP3cjrMAJSzVFt5

[LS]: https://pages.databricks.com/rs/094-YMS-629/images/LearningSpark2.0.pdf


<!-- Project LINKS -->
[Project 0]: https://github.com/williamdemeo/cs644-fall2022/tree/master/projects/Project0
[Project 1]: https://github.com/williamdemeo/cs644-fall2022/tree/master/projects/Project1
[Project 2]: https://github.com/williamdemeo/cs644-fall2022/tree/master/projects/Project2
[Project 3]: https://github.com/williamdemeo/cs644-fall2022/tree/master/projects/Project3
[Project 4]: https://github.com/williamdemeo/cs644-fall2022/tree/master/projects/Project4

[Volume, velocity, and variety: Understanding the three V's of big data]: https://www.zdnet.com/article/volume-velocity-and-variety-understanding-the-three-vs-of-big-data/

[Getting Started with Apache Spark]: https://docs.databricks.com/getting-started/spark/quick-start.html
[Getting Started with Azure (with Databricks)]: https://docs.microsoft.com/en-us/training/modules/get-started-azure-databricks/5-exercise
[Azure ML exercises]: https://microsoftlearning.github.io/dp-090-databricks-ml/


[Week 1]: https://github.com/williamdemeo/cs644-fall2022/blob/main/lecture/Lecture_Week01.pdf
[Week 2]: https://github.com/williamdemeo/cs644-fall2022/blob/main/lecture/Lecture_Week02.pdf
[Week 3]: https://github.com/williamdemeo/cs644-fall2022/blob/main/lecture/Lecture_Week03.pdf
[Week 4]: https://github.com/williamdemeo/cs644-fall2022/blob/main/lecture/Lecture_Week04.pdf
