# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## Project 1: Wikipedia

**Deadline** TBA

### Instructions

To start, download the assignment: [wikipedia.zip](wikipedia.zip). 

For this assignment, you also need to download [the data](http://alaska.epfl.ch/~dockermoocs/bigdata/wikipedia.dat) (~133 MB):

http://alaska.epfl.ch/~dockermoocs/bigdata/wikipedia.dat

and place it in the data folder of your project.

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

### Task 1: Create a SparkContext object

To start, we need to create a **Spark context** (an object of type `SparkContext`), which gives us a "handle" on our cluster. Once we have a `SparkContext` object, we use it to create and populate `RDD`s with data.

To create a `SparkContext` object, we first need to a `SparkConfig` instance, which 
represents the configuration of our Spark application. It's here that we indicate our intention to run 
our application in "local" mode. 

We must also name our Spark application. For help, see the [Spark API Docs](https://spark.apache.org/docs/2.1.0/api/scala/index.html#org.apache.spark.package).

Configure your cluster to run in "local" mode by implementing `val conf` and `val sc` in the `main` method of the project you loaded into VSCode or IntelliJ (after extracting the `wikipedia.zip` archive).


### Task 2: Read-in Wikipedia Data

There are several ways to read data into Spark. The simplest way to read in data is to
convert an existing collection in memory to an `RDD` using the parallelize method of
the Spark context.

In the program files that come in the `wikipedia.zip` file for this assignment, we already have a fully implemented method called `parse` (in the object `WikipediaData` object) which parses a line of the dataset and turns it into a `WikipediaArticle` object.

Your first task is to create an `RDD` (by implementing `val wikiRdd`) which should contain the collection of `WikipediaArticle` article objects.

### Task 3: Compute a ranking of programming languages

We will use a simple metric for determining the popularity of a programming
language: the number of Wikipedia articles that mention the language at least
once.

We will make three attempts at doing this, where each is an improvement on the
previous attempt.

-----------------------------------------------------

**Rank languages attempt #1**. `rankLangs`

**Computing** `occurrencesOfLangStart`

Start by implementing a helper method `occurrencesOfLang` which computes the
number of articles in an RDD of type `RDD[WikipediaArticles]` that mention the given
language at least once. For the sake of simplicity we check that it least one word
(delimited by spaces) of the article text is equal to the given language.

**Computing the ranking** `rankLangs`

Using `occurrencesOfLang`, implement a method `rankLangs` which computes a list of
pairs where the second component of the pair is the number of articles that mention
the language (the first component of the pair is the name of the language).

Here's an example of what `rankLangs` might return:

```scala
List(("Scala", 999999), ("JavaScript", 1278), ("LOLCODE", 982), ("Java", 42))
```

The list should be sorted in descending order. That is, according to this ranking, the
pair with the highest second component (the count) should be the first element of the
list.

Pay attention to roughly how long it takes to run this part! (It should take tens of
seconds... maybe less on a fast machine.)

-----------------------------------------------------



**Rank languages attempt #2**. `rankLangsUsingIndex`

**Compute an inverted index**

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

*Hint*. You might want to use methods `flatMap` and `groupByKey` on the RDD for this part.

**Computing the ranking** `rankLangsUsingIndex`

Use the `makeIndex` method implemented in the previous part to implement a faster
method for computing the language ranking.

Like in Part 1, `rankLangsUsingIndex` should compute a list of pairs where the second
component of the pair is the number of articles that mention the language (the first
component of the pair is the name of the language).

Again, the list should be sorted in descending order. That is, according to this ranking,
the pair with the highest second component (the count) should be the first element of
the list.

*Hint*. the method `mapValues` on `PairRDD` could be useful for this part.

Can you notice a performance improvement over attempt #2? Why?


-----------------------------------------------------



**Rank languages attempt #3**. `rankLangsReduceByKey`

In the case where the inverted index from above is only used for computing the
ranking and for no other task (full-text search, say), it is more efficient to use the
`reduceByKey` method to compute the ranking directly, without first computing an
inverted index. Note that the `reduceByKey` method is only defined for RDDs
containing pairs (each pair is interpreted as a *key-value pair*).

Implement the `rankLangsReduceByKey` method, this time computing the ranking
without the inverted index, using `reduceByKey`.

Like in part 1 and 2, `rankLangsReduceByKey` should compute a list of pairs where the
second component of the pair is the number of articles that mention the language (the
first component of the pair is the name of the language).

Again, the list should be sorted in descending order. That is, according to this ranking,
the pair with the highest second component (the count) should be the first element of
the list.

Can you notice an improvement in performance compared to measuring both the
computation of the index and the computation of the ranking as we did in attempt #2?

If so, can you think of a reason?

### How to test your code.

In the IDE, open the `WikipediaSuite.scala` file.  If you project builds
automatically (VSCode) and correctly, then you should be able to run the
`WikipediaSuite` class which contains tests that your code is getting the
correct results.  In IntelliJ, you might have to invoke the "build project"
command, either from the "Build" menu or by clicking on the hammer icon.

### How to submit your solution.

Upload your modified version of the `WikipediaRankings.scala` file to
Gradescope.

