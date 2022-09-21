# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## Scala 3 REPL and Worksheets

### Evaluate Scala Expressions in a REPL

The simplest way to get started is to open a Scala REPL (read-eval-print-loop). This is an interactive prompt where you can create Scala definition and evaluate Scala expressions.

Assuming you installed Scala according to the instructions given in the [Tools Setup](ToolsSetup.md) document, you can start a REPL by invoking the command `scala`.
You should see something like the following.

```shell
$ scala

Welcome to Scala 3.1.3 (11.0.4, Java Java HotSpot(TM) 64-Bit Server VM).
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

### Programming in a Scala Worksheet

A Scala **worksheet** is a file that contains Scala definitions and expressions that are evaluated from top to bottom. You can think of it as a persistent REPL session that you can edit and run again.

You can evaluate a Scala worksheet with an IDE like VSCode or IntelliJ.  Download this archive, which contains an empty Scala project, extract its content somewhere on your computer, and open the directory "scala-project" in IntelliJ or Metals.


InteliJ will automatically import the project.

In Metals, a pop-up will show up, asking you if you want to import the project. Click “import build”.


Open the file src/main/scala/example.worksheet.sc and play with it!

In IntelliJ, click the green arrow on the top left corner to evaluate your worksheet. The result of the expressions is shown on the right:


In Metals, the worksheet is automatically evaluated every time you save it.


Play with the Code Snippets Given in the Lectures
You can download and play with all the code examples given in the lectures. Below every video, you will find an archive containing the code examples as a standalone Scala project. You can decompress the archive and import the project by opening the root directory from within an IDE.


