# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## PROJECT 0

**Attention**. You are allowed to submit an unlimited number of times. Once you
have submitted your solution, you should see your grade and feedback about your
code under the "My Submission" tab above, within 10 minutes. If you want to
improve your grade, just submit an improved solution. The best of all of your
submissions will count as the final grade. (This assignment does not count
towards your grade anyways, its just to teach you the workflow for submitting
assignments, but this is the policy for all other assignments. However, you'll
need to implement some trivial methods in order to pass the tests.)

### Getting Started! (During Class)

The goal of this assignment is to familiarize yourself with the infrastructure
and the tools required during this class. Even though the grade in this
assignment will be excluded from your final grade for the course, it is
important that you work through this assignment carefully.

1.  **Install Required Tools**

    Before anything else, you must make sure that all the tools we will use for the
    programming projects are correctly installed.  Carefully read and following the
    instructions on the [Tools Setup][] page and verify
    that each of the tools mentioned works on your machine.


2.  **Evaluate Scala Expressions in a REPL**

    The simplest way to get started is to open a Scala REPL
    (read-eval-print-loop). This is an interactive prompt where you can create
    Scala definition and evaluate Scala expressions.

    Assuming you installed Scala according to the instructions given in the
    [Tools Setup][] document, you can start a REPL by invoking the
    command `scala`. You should see something like the following.

    ```shell
    $ scala

    Welcome to Scala 3.2.0 (11.0.4, Java Java HotSpot(TM) 64-Bit Server VM).
    Type in expressions for evaluation. Or try :help.

    scala> 
    ```

    This loads the Scala REPL and shows the `scala>` prompt, where you can type in Scala code and evaluate Scala expressions:

    ```scala
    scala> 1 + 1
    val res0: Int = 2

    scala> val x = 42
    val x: Int = 42
    ```

    To exit the Scala REPL, type `Ctrl-C`.

3.  **Write some Scala code in a Worksheet**

    A **Scala worksheet** is a file that contains Scala definitions and expressions
    that are evaluated from top to bottom. You can think of it as a persistent REPL
    session that you can edit and run again.

    Worksheet files are named with the `.sc` extension.

    You can evaluate a Scala worksheet with an IDE like VSCode or IntelliJ.
    Download the [scala-project.zip][] archive, which contains an empty Scala
    project.  Extract the contents of the archive somewhere on your computer.
    (e.g., on Linux, do `unzip scala-project.zip` at the command line in the
    directory where you downloaded the `scala-project.zip` file.) 
    Open the resulting `scala-project` directory in VSCode or IntelliJ.


    IntelliJ will automatically import the project.

    In VSCode (with Metals extension), a pop-up will appear asking you if you want
    to import the project. 


    ![img](../.img/import-build.png)


    Click "import build."

    In the "Explorer" pane on the left of your IDE, select (usually double-click) the file `src/main/scala/example.worksheet.sc` and play with it.


    In VSCode, the worksheet should be automatically evaluated every time you save
    any changes to it.

    In IntelliJ, you may have to click the green arrow (the "play" button) on the
    top left corner to evaluate the worksheet. The result of the expressions is
    shown on the right. 


### The Assignment (to do on your own or with ONE partner)

1.  **Obtain the Project Files**

    Download the [example.zip](../example.zip) archive file and extract it somewhere on your machine.

2.  **Opening the Project in VSCode or Intellij**

    To work on the source code of the project, you have to import it into VSCode
    or Intellij. Follow the description on the Tools Setup page and make sure to
    import the project as a sbt project. Then, have a first look at the source
    code. In the folder src/main/scala, open the package example and
    double-click the file Lists.scala.

    If you run the tests right away, they will fail. Implement two methods (sum and max).

    When working on an assignment, it is important that you don't change any
    already implemented method, class or object names or types. Doing so will
    prevent our automated grading tools from working and you have a high risk of
    not obtaining any points for your solution.

3.  **Running your Code**

    Once you start writing some code, you might want to run your code on a few
    examples to see if it works correctly. We present two possibilities to run
    the methods you implemented.

    +  **Using the Scala REPL**

       In the sbt console, start the Scala REPL by typing console.

       ```scala
       > console
       [info] Starting scala interpreter...

       scala>
       ```

       The classes of the assignment are available inside the REPL, so you can
       for instance import all the methods from object Lists: 


       ```scala
       scala> import example.Lists._
       import example.Lists._

       scala> max(List(1,3,2))
       res1: Int = 3
       ```

    +  **Using a Main Object**

       Another way to run your code is to create a new Main object that can be executed by the Java Virtual Machine.

       In your IDE, right-click on the package `example` in `src/main/scala` and select "New" > "Scala Object".

       Use `Main` as the object name (any other name would also work).

       Confirm by clicking "Finish"

       In order to make the object executable it has to extend the type App. Change the object definition to the following:

       ```
       object Main extends App {
         println(Lists.max(List(1,3,2)))
       }
       ```

       Now the `Main` object can be executed. In order to do so in your IDE

       Right-click on the file `Main.scala`

       Select "Run As" > "Scala Application"

       You can also run the `Main` object in the sbt console by simply using the command run.

4.  **Writing Tests**

    Throughout the assignments of this course we will require you to write unit
    tests for the code that you write. Unit tests are the preferred way to test
    your code because unlike REPL commands, unit tests are saved and can be
    re-executed as often as required. This is a great way to make sure that
    nothing breaks when you have go back later to change some code that you
    wrote earlier on. 

    We will be using the ScalaTest testing framework to write our unit tests. In
    your IDE, navigate to the folder `src/test/scala` and open the file
    `ListsSuite.scala` in package `example`. This file contains a step-by-step
    tutorial to learn how to write and execute ScalaTest unit tests. 

5.  **Submitting your Solution**

    New Gradescope instructions coming soon!


<!-- Once you implemented all the required methods and tested you code thoroughly, you can submit it to Coursera. The only way to submit your solution is through sbt, so you need to start sbt in your project directory. -->

<!-- Warning: Make sure that you run sbt in the root folder of the project (where the build.sbt file is). -->

<!-- Warning: Make sure that the console line is `>` and not `scala>`. Otherwise, you're inside the Scala console and not SBT (check the SBT tutorial). -->

<!-- In order to submit, you need to have your coursera username and your submission password. Note that the submission password is NOT your login password, instead it is a special password generated by Coursera.  -->

<!-- Submitting in sbt is simply done by invoking the submit task: -->

<!-- 1234567 -->
<!-- > submit your.email@domain.com submissionPassword -->
<!-- [info] Connecting to coursera. Obtaining challenge... -->
<!-- [info] Computing challenge response... -->
<!-- [info] Submitting solution... -->
<!-- [success] Your code was successfully submitted: Your submission has been accepted and will be graded shortly. -->
<!-- [success] Total time: 2 s, completed Aug 30, 2012 4:30:10 PM -->
<!-- >  -->
<!-- You are allowed to resubmit an unlimited number of times! Once you submit your solution, you should see your grade and a feedback about your code on the Coursera website within 10 minutes. If you want to improve your grade, just submit an improved solution. The best of all your submissions will count as the final grade. -->

<!-- More information about SBT can be found here. -->

<!-- How to submit -->
<!-- Copy the token below and run the submission script included in the assignment download. When prompted, use your email address williamdemeo@gmail.com. -->

<!-- xoqfJwxrnL0OPqwB -->
<!-- Your submission token is unique to you and should not be shared with anyone. You may submit as many times as you like. -->

[Tools Setup]: ../ToolsSetup.md
<!-- [example.zip]: example.zip -->
[scala-project.zip]: scala-project.zip
