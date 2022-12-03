# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## Spark Setup 

### Windows

This section only applies to users of Windows.  You do not need to follow these directions to install Spark on Linux or Mac.


1.  **Install the Java Development Kit (JDK)** (if you haven't done so already)

    Any version between Java 8 through 14 should be fine.  (More recent version might not be compatible with Spark.)

    I recommend you install JDK 11, which can be downloaded from the Oracle website at the following URL:
    
    [https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    
    **Tip** Do NOT install the JDK in a directory with a space in the name (e.g., `Program Files`). Instead, when Java asks you where you would like to install, choose something like `C:\jdk11`.
    
2.  **Install an IDE** e.g. VS Code with the Metals extension or IntelliJ IDEA with the Scala plugin.
    
    **Tip** If you chose IntelliJ IDEA as your IDE, make sure the Scala plugin is installed by clicking on the `Configure` button on the bottom right of the main IDEA splash screen window.  If you don't see the splash screen window when you start IntelliJ IDEA, it's probably because you have some project windows open.  Close these windows and you should see the IntelliJ IDEA splash screen window.
    
    **Tip** If you chose IntelliJ IDEA as your IDE, setup the default JDK to use for your Scala projects.  Click on the `Configure` button on the bottom right of the IntelliJ IDEA splash screen and select "Structure for New Projects."  Select the JDK you installed in step 1.

3.  **Convince Windows that Hadoop is running**

    a.  Create a `C:\Hadoop\bin` directory.
    
    b.  Copy the file [winutils.exe](winutils.exe) into the `C:\Hadoop\bin` directory you just created.
    
    c.  Create a new environment variable (enter "environment variables" in the Windows search bar, click on "Add Environment Variables," and add a new system variable) named `HADOOP_HOME` with a value of `C:\Hadoop`.  Select the `PATH` environment variable, and `APPEND` a new entry, separated by a semi-colon, of `%HADOOP_HOME%\bin`. Restart IntelliJ to make sure the new environment variables are loaded.


## Getting Data

Please download the Movie Lens dataset `ml-100k.zip` from the following url:

https://files.grouplens.org/datasets/movielens/ml-100k.zip

Unzip the file and put it in a folder called `data` inside your spark project.

## SparkContext vs SparkSession

https://sparkbyexamples.com/spark/sparksession-vs-sparkcontext/


## Packaging up a Spark project to run on a cluster

So far we have only run Spark jobs in the IDE.  We need to learn how to package up and deploy a Spark program so that it can run independently of the IDE, say, on an [AWS EMR][] cluster.

We will use the [Scala Build Tool][] ([sbt][]) to create a jar file containing our Scala program.

In the `project` folder, we need a new file called `assembly.sbt` that contains one line

```scala
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")
```

### A small example

Download [sbt.zip](https://github.com/williamdemeo/cs644-fall2022/find/main).

[AWS EMR]: https://aws.amazon.com/emr/



[Scala Build Tool]: https://www.scala-sbt.org/

[sbt]: https://www.scala-sbt.org/
