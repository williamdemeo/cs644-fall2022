# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## Project 1: Wikipedia

**Deadline** TBA

### Instructions

To start, download the assignment: [wikipedia.zip][].

For this assignment, you also need to download the data file [wikipedia.dat](https://drive.google.com/file/d/1HGpInNzqDw4Bdr_F1LjZ-BVDTeubfAdJ/view?usp=sharing) (~133 MB).

and place it in the `src/main/resources/data` folder of your project.

**Important**. The `wikipedia.dat` file must be located in the
`src/main/resources/data` folder of your project, otherwise your code will
not run without errors.

In this assignment, you will get to know Spark by exploring full-text Wikipedia articles.
Gauging how popular a programming language is important for companies judging
whether or not they should adopt an emerging programming language. For that
reason, industry analyst firm RedMonk has bi-annually computed a ranking of
programming language popularity using a variety of data sources, typically from
websites like GitHub and StackOverflow. See their [top-20 ranking for June 2016](http://redmonk.com/sogrady/2016/07/20/language-rankings-6-16/) as an example.

We'll use our full-text data from Wikipedia to produce a rudimentary metric of how popular a programming language is, in an effort to see if our Wikipedia-based rankings bear any relation to the popular Red Monk rankings.

You'll complete this exercise on just one node (your laptop), but you can also head
over to Databricks Community Edition to experiment with your code on a "micro-cluster" for free.


For the sake of simplified logistics, we'll run Spark in "local" mode. This means
that your full Spark application will be run on only one node, locally, on your laptop.
(Later in the course, we'll run our Spark programs on a cluster with multiple compute nodes, but the principles we learn here will still apply to that more general setup.)

- [Task 0: Create a SparkContext object](#task-0-create-a-sparkcontext-object)
- [Task 1: Read-in Wikipedia Data](#task-1-read-in-wikipedia-data)
- [Task 2: Compute a ranking of programming languages](#task-2-compute-a-ranking-of-programming-languages)
    - [Rank languages attempt #1](#rank-languages-attempt-1)
    - [Rank languages attempt #2](#rank-languages-attempt-2)
    - [Rank languages attempt #3](#rank-languages-attempt-3)
- [How to test your code.](#how-to-test-your-code)
- [How to submit your solution.](#how-to-submit-your-solution)

----------------------


### Task 0: Create a SparkContext object

To start, we need to create a **Spark context** (an object of type `SparkContext`), which gives us a "handle" on our cluster. Once we have a `SparkContext` object, we use it to create and populate `RDD`s with data.

To create a `SparkContext` object, we first need to a `SparkConfig` instance, which 
represents the configuration of our Spark application. It's here that we indicate our intention to run 
our application in "local" mode. 

We must also name our Spark application. (See the [Spark API Docs](https://spark.apache.org/docs/2.1.0/api/scala/index.html#org.apache.spark.package).)

We configure our cluster to run in "local" mode by implementing `val conf` and
`val sc` in the `WikipediaRanking` object in the `WikipediaRanking.scala` file.

Once you extract the [wikipedia.zip][] archive mentioned above and load it into
your IDE (either VSCode or IntelliJ IDEA), open the `WikipediaRanking.scala`
file and notice that the `SparkConf` and `SparkContext` objects have already
been created for you.

**Important**. Take note of how the `SparkConf` and `SparkContext` objects are
created since you will have to create these objects yourself in future projects.

-------------

### Task 1: Read-in Wikipedia Data

There are several ways to read data into Spark. The simplest way to read in data is to
convert an existing collection in memory to an `RDD` using the `textFile` method of
the Spark context.

In the program files that come in `wikipedia.zip`, we have already implemented a
method called `parse` (in the object `WikipediaData` object) which parses a line 
of the dataset and turns it into a `WikipediaArticle` object.

Your first task is to create an `RDD` (by implementing `val wikiRdd`) which
should contain the collection of `WikipediaArticle` article objects.

*Hints*. Use `sc.textFile`, `WikipediaData.filePath`, `map`, and `WikipediaData.parse`.

------------------------

### Task 2: Compute a ranking of programming languages

We will use a simple metric for determining the popularity of a programming
language: the number of Wikipedia articles that mention the language at least
once.

We will make three attempts at doing this, where each is an improvement on the
previous attempt.

#### Rank languages attempt #1

**Computing the number of occurrences of a lanuage** (`occurrencesOfLangStart`)

Start by implementing the helper method `occurrencesOfLang` which returns a
collection of type `RDD[WikipediaArticles]` containing those articles in the
given collection (`rdd`) that mention the given language (`lang`) at least once. 
For the sake of simplicity, we check that at least one word
(delimited by spaces) of the article text is equal to the given language.

*Hints*. Use `aggregate` on `rdd` and `mentionsLanguage` on each
`WikipediaArticle` object in the collection. Recall, the signature of aggregate
is:

```scala
def aggregate[B](z: B)(seqOp: (B, A) => B, combOp: (B, B) => B): B
```

**Computing the ranking** (`rankLangs`)

Implement a method called `rankLangs` which uses `occurrencesOfLang` and computes
a list of pairs where the second component of the pair is the number of articles
that mention the language (the first component of the pair is the name of the
language).

*Hints*. Use `map`, `occurencesOfLang`, and `sortBy`.

Here's an example of what `rankLangs` might return:

```scala
List(("Scala", 999999), ("JavaScript", 1278), ("LOLCODE", 982), ("Java", 42))
```

The list should be sorted in descending order. That is, according to this ranking, the
pair with the highest second component (the count) should be the first element of the
list.

Pay attention to roughly how long it takes to run this part.
(It should take tens of seconds... maybe less on a fast machine.)

-----------------------------------------------------


#### Rank languages attempt #2

**Computing an inverted index** (`makeIndex`)

An *inverted index* is an index data structure storing a mapping from keys, such as
words or numbers, to a set of documents. In particular, the purpose of an inverted
index is to allow fast full text searches. In our use-case, an inverted index would be
useful for mapping from the names of programming languages to the collection of
Wikipedia articles that mention the name at least once.

To make working with the dataset more e cient and more convenient, implement a
method that computes an inverted index which maps programming language names
to the Wikipedia articles on which they occur at least once.

Implement the method `makeIndex` which returns an RDD of the following type:

```scala
RDD[(String, Iterable[WikipediaArticle])]
```

This RDD contains pairs, such that for each language in the given `langs` list
there is at most one pair. Furthermore, the second component of each pair (the
`Iterable`) contains the `WikipediaArticles` that mention the language at least
once. 

This could be accomplished in one line, and you may go ahead and do it
that way if you wish.  However, we recommend starting by breaking the task down
in steps.

1.  Compute a collection (`pairs`) of all pairs `(l, wa)`, where `l` is a
    language and `wa` is a Wikipedia article. (*Hints*. Use `flatMap` and `map`.)

2.  Compute the collection (`mentionedPairs`) of all pairs `(l, wa)` such that
    `wa` is an article that mentions language `l`. (*Hints*. Use `filter` and
    `mentionsLanguage`.)

3.  Finally, write the expression that you want the `makeIndex` function to
    return. (*Hints*. Apply `groupByKey` to `mentionedPairs`.)


**Computing the ranking** `rankLangsUsingIndex`

Use the `makeIndex` method to implement a faster method for computing the language ranking.

Like in Part 1, `rankLangsUsingIndex` should compute a list of pairs where 
the first component of the pair is the name of the language and the second
component is the number of articles that mention the language. 
(*Hints*. Use `mapValues`, `sortBy`, `collect`, and `toList`.)

Again, the list should be sorted in descending order. That is, according to this ranking,
the pair with the highest second component (the count) should be the first element of
the list.

Can you notice a performance improvement over attempt #2? If so, why do you
think that is?


-----------------------------------------------------


#### Rank languages attempt #3

In the case where the inverted index from above is only used for computing the
ranking and for no other task (full-text search, say), it is more efficient to use the
`reduceByKey` method to compute the ranking directly, without first computing an
inverted index. Note that the `reduceByKey` method is only defined for RDDs
containing pairs (each pair is interpreted as a *key-value pair*).

Implement the `rankLangsReduceByKey` method, this time computing the ranking
without the inverted index, using `reduceByKey`.

To begin, first implement the function `zipLangWithPoint` which 
creates a list of `(lang, integer)` pairs which contains one pair `(l, 1)` for each Wikipedia
article in which the language `l` occurs.  For instance, if "Scala" appears in only two
999999 articles, then the RDD should contain 999,999 occurrences of the pair
("Scala", 1). (*Hints*. Use `flatMap`, `withFilter`, and `map` for this.)

Next, implement `rankLangsReduceByKey`. Like in part 1 and 2,
`rankLangsReduceByKey` should compute a list of pairs where the first component
of the pair is the name of the language and the second component is the number
of articles that mention the language. Again, the list should be sorted in
descending order. That is, according to this ranking, the pair with the highest
second component (the count) should be the first element of the list.
(*Hints.* Use `zipLangWithPoint(langs, rdd)`, `reduceByKey`, `sortBy`, `collect`, and `toList`.)

Can you notice an improvement in performance compared to measuring *both* the
computation of the index *and* the computation of the ranking as we did in attempt #2?
If so, can you think of a reason?

--------------------------------------------

### How to test your code.

You can check that your code passes the five tests specified in
`WikipediaSuite.scala` either by using the IDE or by invoking the `test` command
at the sbt command prompt.

#### In VSCode

With your project open in VSCode, open the explorer and select the
`WikipediaSuite.scala` file (located in `src/test/scala`). If your project was
already built correctly, with no errors, then you should see a green "play" icon
next to the declaration of the `WikipediaSuite` class. You can run the tests by
clicking that icon.


#### In IntelliJ

With your project open in IntelliJ, open the explorer and select the
`WikipediaSuite.scala` file (located in `src/test/scala`). You might have to
manually invoke the "build project" command, either from the "Build" menu or by
clicking on the hammer icon.  Once the build is complete, if it's successful,
you should see green "play" icons next to the declaration of the
`WikipediaSuite` class.  You can run the tests by clicking that icon.


#### Using the sbt REPL

If you prefer to use a terminal/command line interface (cli) to test your code, you can use the sbt REPL.

1.  Open a terminal window.
2.  Change to the `wikipedia` main project directory (the one with the
    `build.sbt` file in it) (e.g., `cd ~/Project1/wikipedia`).


3.  Run `sbt` by typing `sbt` on the command line and hitting enter.

    After sbt reads your `build.sbt` file and downloads and compiles all the
    required dependencies, you should see something like the following in the
    terminal window:

    ```shell
    williamdemeo@lampe:~/wikipedia$ sbt
    [info] welcome to sbt 1.7.1 (Oracle Corporation Java 11.0.4)
    [info] loading global plugins from /home/williamdemeo/.sbt/1.0/plugins
    [info] loading settings for project wikipedia-build-build from metals.sbt ...
    [info] loading project definition from ~/wikipedia/project
    [info] loading settings for project wikipedia-build from metals.sbt ...
    [info] loading project definition from ~/wikipedia/project
    [success] Generated .bloop/wikipedia-build.json
    [success] Total time: 0 s, completed Oct 22, 2022, 10:57:22 PM
    [info] loading settings for project root from build.sbt ...
    [info] set current project to wikipedia (in build file:~/wikipedia/)
    [info] sbt server started at local:///home/williamdemeo/.sbt/1.0/server/d02c03774b971d6bf250/sock
    [info] started sbt server
    sbt:wikipedia> 
    ```

4.  At the `sbt:wikipedia>` prompt, type `test` and hit enter.

    The tests should run and show you any errors and give some indication of which tests you've passed/failed.

    If all the tests pass, then, after lots of other output, you will see lines like the following:

    ```shell
    22/10/22 23:01:43 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
    22/10/22 23:01:43 INFO SparkContext: Successfully stopped SparkContext
    wikipedia.WikipediaSuite:
      + 'occurrencesOfLang' should work for (specific) RDD with one element 4.034s
      + 'rankLangs' should work for RDD with two elements 0.075s
      + 'makeIndex' creates a simple index with two entries 0.329s
      + 'rankLangsUsingIndex' should work for a simple RDD with three elements 0.258s
      + 'rankLangsReduceByKey' should work for a simple RDD with four elements 0.461s
    [info] Passed: Total 5, Failed 0, Errors 0, Passed 5
    [success] Total time: 15 s, completed Oct 22, 2022, 11:01:43 PM
    sbt:wikipedia> 
    ```

------------------------------------

### How to submit your solution.

Upload your modified version of the `WikipediaRankings.scala` file to
Gradescope.

[wikipedia.zip]: https://github.com/williamdemeo/cs644-fall2022/raw/main/projects/Project1/wikipedia.zip
