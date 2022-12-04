# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## Project 2: Time Usage

**Deadline** TBA

### Instructions

#### Getting Started

1.  Download the source code [timeusage.zip][].

2.  Download the data file [atussum.csv.bz2][].

3.  Extract [timeusage.zip][] in a directory of your choice and open the
    resulting `timeusage` project directory in either VSCode or IntelliJ IDEA.

    **Important!** Windows users, make sure your project doesn't end up inside
    an extra subdirectory. Specifically, after you unzip `timeusage.zip` you 
    should have just `timeusage/build.sbt` and `timeusage/src`; you should NOT
    have `timeusage/timeusage/build.sbt` and `timeusage/timeusage/src`.

4.  Extract the [atussum.csv.bz2][] data file and place it in the
    `timeusage/src/main/resources/timeusage/` directory.

------------------------
    
#### The Problem

The dataset in the `atussum.csv` file is provided by Kaggle and documented [here](https://www.kaggle.com/bls/american-time-use-survey).

The file uses the [comma-separated values][] (CSV) format: the first line is a
header defining the field names of each column, and every following line
contains an information record, which is itself made of several columns. It
contains information about how people spend their time (e.g., sleeping, eating,
working, etc.).

Here are the first four lines of the dataset:

```
1  tucaseid,gemetsta,gtmetsta,peeduca,pehspnon,ptdtrace,teage,telfs,temjot,teschenr,teschlvl,tesex,
   tespempnot,trchildnum,trdpftpt,trernwa,trholiday,trspftpt,trsppres,tryhhchild,tudiaryday,tufnwgtp,
   tehruslt,tuyear, t010101,t010102,t010199,t010201,t010299,t010301,t010399,t010401,t010499,t010501,
   t010599,t019999,t020101,t020102,t020103,t020104,t020199,t020201,t020202,t020203,t020299,t020301,
   t020302,t020303,t020399,t020401,t020402,t020499,t020501,t020502,t020599,t020681,t020699,t020701,
   t020799,t020801,t020899,t020901,t020902,t020903,t020904,t020905,t020999,t029999,t030101,t030102,
   t030103,t030104,t030105,t030108,t030109,t030110,t030111,t030112,t030186,t030199,t030201,t030202,
   t030203,t030204,t030299,t030301,t030302,t030303,t030399,t030401,t030402,t030403,t030404,t030405,
   t030499,t030501,t030502,t030503,t030504,t030599,t039999,t040101,t040102,t040103,t040104,t040105,
   t040108,t040109,t040110,t040111,t040112,t040186,t040199,t040201,t040202,t040203,t040204,t040299,
   t040301,t040302,t040303,t040399,t040401,t040402,t040403,t040404,t040405,t040499,t040501,t040502,
   t040503,t040504,t040505,t040506,t040507,t040508,t040599,t049999,t050101,t050102,t050103,t050189,
   t050201,t050202,t050203,t050204,t050289,t050301,t050302,t050303,t050304,t050389,t050403,t050404,
   t050405,t050481,t050499,t059999,t060101,t060102,t060103,t060104,t060199,t060201,t060202,t060203,
   t060289,t060301,t060302,t060303,t060399,t060401,t060402,t060403,t060499,t069999,t070101,t070102,
   t070103,t070104,t070105,t070199,t070201,t070299,t070301,t070399,t079999,t080101,t080102,t080199,
   t080201,t080202,t080203,t080299,t080301,t080302,t080399,t080401,t080402,t080403,t080499,t080501,
   t080502,t080599,t080601,t080602,t080699,t080701,t080702,t080799,t080801,t080899,t089999,t090101,
   t090102,t090103,t090104,t090199,t090201,t090202,t090299,t090301,t090302,t090399,t090401,t090402,
   t090499,t090501,t090502,t090599,t099999,t100101,t100102,t100103,t100199,t100201,t100299,t100381,
   t100383,t100399,t100401,t100499,t109999,t110101,t110199,t110281,t110289,t119999,t120101,t120199,
   t120201,t120202,t120299,t120301,t120302,t120303,t120304,t120305,t120306,t120307,t120308,t120309,
   t120310,t120311,t120312,t120313,t120399,t120401,t120402,t120403,t120404,t120405,t120499,t120501,
   t120502,t120503,t120504,t120599,t129999,t130101,t130102,t130103,t130104,t130105,t130106,t130107,
   t130108,t130109,t130110,t130111,t130112,t130113,t130114,t130115,t130116,t130117,t130118,t130119,
   t130120,t130121,t130122,t130123,t130124,t130125,t130126,t130127,t130128,t130129,t130130,t130131,
   t130132,t130133,t130134,t130135,t130136,t130199,t130201,t130202,t130203,t130204,t130205,t130206,
   t130207,t130208,t130209,t130210,t130211,t130212,t130213,t130214,t130215,t130216,t130217,t130218,
   t130219,t130220,t130221,t130222,t130223,t130224,t130225,t130226,t130227,t130228,t130229,t130230,
   t130231,t130232,t130299,t130301,t130302,t130399,t130401,t130402,t130499,t139999,t140101,t140102,
   t140103,t140104,t140105,t149999,t150101,t150102,t150103,t150104,t150105,t150106,t150199,t150201,
   t150202,t150203,t150204,t150299,t150301,t150302,t150399,t150401,t150402,t150499,t150501,t150599,
   t150601,t150602,t150699,t159989,t160101,t160102,t160103,t160104,t160105,t160106,t160107,t160108,
   t169989,t180101,t180199,t180280,t180381,t180382,t180399,t180481,t180482,t180499,t180501,t180502,
   t180589,t180601,t180682,t180699,t180701,t180782,t180801,t180802,t180803,t180804,t180805,t180806,
   t180807,t180899,t180901,t180902,t180903,t180904,t180905,t180999,t181002,t181081,t181099,t181101,
   t181199,t181201,t181202,t181204,t181283,t181299,t181301,t181302,t181399,t181401,t181499,t181501,
   t181599,t181601,t181699,t181801,t181899,t189999,t500101,t500103,t500104,t500105,t500106,t500107,
   t509989
2  "20030100013280",1,-1,44,2,2,60,2,2,-1,-1,1,2,0,2,66000,0,-1,1,-1,6,8155463,30,2003,870,0,0,40,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,325,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,200,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
3  "20030100013344",2,-1,40,2,1,41,1,2,2,-1,2,1,2,2,20000,0,1,1,0,7,1735323,30,2003,620,0,0,60,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,60,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,90,0,0,0,0,530,0,0,0,0,0,0,60,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,20,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
4  "20030100013352",1,-1,41,2,1,26,2,2,2,-1,2,2,0,2,20000,0,-1,1,-1,7,3830528,12,2003,560,0,0,80,0,
   0,0,0,0,0,0,0,0,0,0,15,0,180,0,60,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,60,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,75,0,0,0,0,220,0,0,0,0,0,0,120,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,60,0,0,0,0,0,0,0,0,0,0,0,
   0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
   0,0
```

Our goal is to identify three groups of activities,

*  **primary needs** (sleeping and eating), 
*  **work**,
*  **other** (leisure),

and to observe how people allocate their time between these three kinds of activities, and
whether we can see differences among different groups. Specifically, we will compare and
contrast men versus women, employed versus unemployed people, or young (less than 22 years
old) versus active (between 22 and 55 years old) versus elder people.

At the end of the assignment you should be able to answer the following questions based on
the dataset.

*  How much time do we spend on primary needs compared to other activities?
*  Do women and men spend the same amount of time in working?
*  Does the time spent on primary needs change when people get older?
*  How much time do employed people spend on leisure compared to unemployed people?

To achieve this, we will read in the data file with Spark, transform it into an
intermediate dataset which will be easier to work with for our use case, and finally
compute information that will answer the above questions.

-------------

#### Read-in Data

The simplest way to create a [DataFrame][] is to read a file and let SparkSQL infer the
underlying schema. Unfortunately, this approach doesn't work well with CSV files because
the inferred column types are always `String`.

In the present case, the first column contains a `String` value identifying the
respondent, but all the other columns contain numeric values. Since this schema will not
be correctly inferred by SparkSQL, we must define the schema ourselves. But the number of
columns is huge, so we definitely do not want to manually enumerate each column.
Fortunately, the names of all columns in the dataset appear in the first line of the CSV
file, so we can parse that line to assign names to the columns programmatically.

1.  The first task is to turn this first line into a SparkSQL [StructType][]. This is the
    purpose of the `dfSchema` method. This method returns a [StructType][] describing the
    schema of the CSV file, where the first column has type [StringType][] and all the
    others have type [DoubleType][]. None of these columns are nullable.

    ```scala
    def dfSchema(columnNames: List[String]): StructType
    ```

    *Hints*.
    +  Handle the `head` field and `tail` fields separately because the type of the `head`
       field is special ([StringType][]).
    +  Define a `val` called, say, `head_field`, using the [StructField][] constructor
       with arguments `columnNames.head`, [StringType][], and `nullable = false` as arguments.
    +  Define `val tail_field` as `columnNames.tail.map(???)`; replace the `???` with an
       anonymous function (e.g., `fld => StructField(fld, ???, ???)`) (Of course, you
       should replace the `???` with the appropriate arguments.)
    +  Finally, `dfSchema` should return `StructType(head_field :: tail_fields)`.

2.  The second step to effectively read in the CSV file is to turn each line into a
    SparkSQL [Row][] containing columns that match the schema returned by `dfSchema`. 
    That's the job of the `row` method.

    ```scala
    def row(line: List[String]): Row
    ```

    *Hints*. Use [Row.fromSeq][] with argument `line.head :: line.tail.map(???)`. (Replace
    `???` with the appropriate argument.)

-------------

#### Transform the Data

As you probably noticed, the initial dataset contains lots of information that we don't
need in order to answer our questions, and even the columns that contain useful information
are too detailed. For instance, we are not interested in the exact age of each
respondent, but just whether she was "young", "active" or "elder".

Also, the time spent on each activity is very detailed (there are more than 50 reported
activities). Again, we don't need this level of detail; we are only interested in three
activities: primary needs, work and other. So, with this initial dataset it would a bit
hard to express the queries that would give us the answers we are looking for.

The second part of this assignment consists in transforming the initial dataset into a
format that will be easier to work with. A first step in this direction is to identify
which columns are related to the same activity. Based on the description of the activity
corresponding to each column (given in [this document](https://www.bls.gov/tus/home.htm),
we deduce the following rules:

*  **primary needs** activities (sleeping, eating, etc.) are reported in columns starting with
   "t01", "t03", "t11", "t1801" and "t1803" ;
*  **work** activities are reported in columns starting with "t05" and "t1805" ;
*  **other** activities (leisure) are reported in columns starting with "t02", "t04", "t06", "t07",
   "t08", "t09", "t10", "t12", "t13", "t14", "t15", "t16" and "t18" (only those which are not part
   of the previous groups).

Then our work consists in implementing the `classifiedColumns` method, which classifies
the given list of column names into three [Column][] groups (primary needs, work or other).
This method should return a triple containing the "primary needs" columns list, the
"work" columns list and the "other" columns list.

```scala
def classifiedColumns(columnNames: List[String]): (List[Column], List[Column], List[Column])
```

*Hints*.
1.  Create three lists of strings, called, say, `p`, `w`, `o`, containing the column name
    prefixes associated with each activity described above. For instance, `p` would be
    the list `List("t01", "t03", "t11", "t1801", "t1803")`.
2.  Use `p`, `w`, `o` to filter the given list (`columnNames`) of column names to obtain
    three lists of strings, called, say, `primary`, `work`, `other`.  For example,

    ```scala
    val primary = columnNames.filter(s1 => p.exists(s2 => s1.startsWith(s2)))
    ```

    Similarly for `work`, but be carefull! ...you cannot simply use the above filter to 
    get the `other` column names since, for example, `t1801` starts with `t18` but it's
    already included among the "primary needs" column names.
3.  Use `map` and `col` to convert each of these lists of strings (from step 2) into a list of
    columns and return the results as a triple.


-------------

#### Data Transformation

The second step is to implement the `timeUsageSummary` method, which projects the
detailed dataset into a summarized dataset. This summary will contain only 6 columns:
the working status of the respondent, his sex, his age, the amount of dailyhours spent
on primary needs activities, the amount of daily hours spent on working and the amount
of daily hours spent on other activities.

```scala
def timeUsageSummary( primaryNeedsColumns : List[Column]
                    , workColumns         : List[Column]
                    , otherColumns        : List[Column]
                    , df                  : DataFrame
                    ) : DataFrame
```

Each "activity column" will contain the sum of the columns related to the same activity
of the initial dataset. Note that time amounts are given in minutes in the initial dataset,
whereas in our resulting dataset we want them to be in hours.

The columns describing the work status, the sex and the age, will contain simplified
information compared to the initial dataset.

Last, people that are not employable will be filtered out of the resulting dataset.

The comment on top of the `timeUsageSummary` method will give you more specific
information about what is expected in each column.

-------------------

#### Data Aggregation

Finally, we want to compare the **average time** spent on each activity, for all the
combinations of working status, sex and age.

Implement the `timeUsageGrouped` method which computes the average
number of hours spent on each activity, grouped by working status (employed or
unemployed), sex and age (young, active or elder), and also ordered by working status,
sex and age. The values will be rounded to the nearest tenth.

```scala
def timeUsageGrouped(summed: DataFrame): DataFrame
```

Now you can run the project and see what the final `DataFrame` contains. What do you
see when you compare elderly men versus elderly women's time usage? Compared to active people, how much
time do elderly people allocate to leisure activities? How much time do
active employed people spend on work activities?

-----------------

#### Alternative to Data Transformation Approach (SQL)

We can also implement the `timeUsageGrouped` method by using a plain SQL query
instead of the `DataFrame` API. Note that sometimes using the programmatic API to
build queries is a lot easier than writing a plain SQL query. If you do not have
experience with SQL, you might find [these examples](https://en.wikipedia.org/wiki/SQL_syntax#Queries) useful.

```scala
def timeUsageGroupedSqlQuery(viewName: String): String
```

*Hints*.  
*  Keep in mind you simply want to return string describing an SQL query.
*  Use "SELECT", "ROUND", "AVG", "FROM", and "GROUP BY".
*  Be sure the resulting string conforms exactly to correct SQL syntax (including newline characters, "\n").

Before moving on, take a moment to think about the queries above.  Try to think of a query we have seen 
that would have been very challenging to write in plain SQL. 


--------------------------

#### Alternative Data Transformation Approach (Datasets)

In the last part of this project we explore yet another way to express queries: 
using (typed) [Dataset][]s instead of (untyped) [DataFrame][]s.

The `TimeUsageRow` type models the content of a row of a summarized dataset. 

Implement the `timeUsageSummaryTyped` method to convert a [DataFrame][] returned
by `timeUsageSummary` into a [Dataset][][TimeUsageRow]. 

```scala
def timeUsageSummaryTyped(timeUsageSummaryDf: DataFrame): Dataset[TimeUsageRow]
```

*Hints*.  
1. As usual, you convert a collection using the `map` method.
2. To achieve the conversion consider using [Row][]'s [getAs][] method which 
   retrieves a named column of the row and attempts to cast its value to a given type.
   
The `timeUsageSummaryTyped` function can be implemented in one line with the following form:

```scala
timeUsageSummaryDF.map { row => TimeUsageRow(???, ???, ???, ???, ???, ???) }
```



Finally, implement the `timeUsageGroupedTyped` method which performs the same query as `timeUsageGrouped` but uses typed APIs as much as possible.
    
```scala
def timeUsageGroupedTyped(summed: Dataset[TimeUsageRow]): Dataset[TimeUsageRow]
```

*Hints*.

*  Not all operations have a typed equivalent. For example, [round][] will return a [Column][]
   that you will have to turn into a [TypedColumn][] by calling .[as][]`[Double]`. Another example 
   is [orderBy][], which also has no typed equivalent. 

*  Make sure your [Dataset][] has a schema because this operation requires one. (Column names are generally
   lost when using typed transformations.)

--------------------

### How to submit

To submit your solution, post your edited `TimeUsage.scala` file to the Gradescope
assignment called "Project 2."


[timeusage.zip]: https://github.com/williamdemeo/cs644-fall2022/raw/main/projects/Project2/timeusage.zip
[atussum.csv.bz2]: https://github.com/williamdemeo/cs644-fall2022/raw/main/projects/Project2/atussum.csv.bz2
[StructType]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/types/StructType.html
[StringType]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/types/StringType.html
[DoubleType]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/types/DoubleType.html
[Row]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Row.html
[Dataset]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Dataset.html
[Column]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Column.html

[DataFrame]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/index.html#DataFrame=org.apache.spark.sql.Dataset[org.apache.spark.sql.Row]
[StructField]: https://spark.apache.org/docs/3.1.3/api/scala/org/apache/spark/sql/types/StructField.html
[comma-separated values]: https://en.wikipedia.org/wiki/Comma-separated_values
[getAs]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Row.html#getAs[T](i:Int):T
[Row.fromSeq]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Row$.html#fromSeq(values:Seq[Any]):org.apache.spark.sql.Row
[TypedColumn]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/TypedColumn.html
[as]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Column.html#as[U](implicitevidence$1:org.apache.spark.sql.Encoder[U]):org.apache.spark.sql.TypedColumn[Any,U]
[orderBy]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/Dataset.html#orderBy(sortExprs:org.apache.spark.sql.Column*):org.apache.spark.sql.Dataset[T]
[round]: https://spark.apache.org/docs/3.3.1/api/scala/org/apache/spark/sql/functions$.html#round(e:org.apache.spark.sql.Column):org.apache.spark.sql.Column
