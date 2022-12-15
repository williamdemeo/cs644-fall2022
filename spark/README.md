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

-----------------

## Getting Data

The Movie Lens datasets `ml-100k.zip` and `ml-1m.zip` (and many others) can be downloaded from the following url:

https://files.grouplens.org/datasets/movielens/

Another excellent source of datasets is [Kaggle](https://www.kaggle.com/datasets/).

For example, the [American time use survey data](https://www.kaggle.com/datasets/bls/american-time-use-survey) used in Project 2 is available on Kaggle.

------------------------

## SparkContext vs SparkSession

https://sparkbyexamples.com/spark/sparksession-vs-sparkcontext/


## Packaging up a Spark project to run on a cluster

So far we have only run Spark jobs in the IDE.  We need to learn how to package up and deploy a Spark program so that it can run independently of the IDE, say, on an [AWS EMR][] cluster.

We will use the [Scala Build Tool][] ([sbt][]) to create a jar file containing our Scala program.

In the `project` folder, we need a new file called `assembly.sbt` that contains one line

```scala
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")
```

### Example: building a complete stand-alone Spark program

This example describes the steps required to build a stand-alone Spark program on a Linux machine.
If you're not using Linux, don't worry.  It shouldn't be too hard to read through the
example and figure out what analogous steps to follow on your architecture, and if you get stuck, ple
you can post a question on Slack.

#### Prelimiary setup: building the jar file

Let's set up a Spark project in the usual way.

This consists of 

*  Creating a new project directory.
*  Setting up the standard Spark project structure, including at least a `build.sbt` file
   and a `src/main/scala` directory where our Scala source code resides.
   (We'll call our project `movie-similarity`.)
*  We will also need a special sbt file called `assembly.sbt` which will enable us to 
   build our program into a jar file, called `MovieSimilarity-assembly-1.0.jar`.

The `MovieSimilarity-assembly-1.0.jar` file contains everything we need in order to launch our Spark 
program from the command line on a remote server that has Spark installed (e.g., an AWS EMR cluster)
by simply invoking the command `spark-submit MovieSimilarity-assembly-1.0.jar`.

Fortunately, I have already put all the files mentioned above into a zip file to make the setup easy for you. 

1.  Download the [movie-similarity.zip](https://github.com/williamdemeo/cs644-fall2022/raw/main/spark/movie-similarity.zip) file and extract it on your machine.

2.  To build the `MovieSimilarity-assembly-1.0.jar` file, 
    +  download and install the [Scala build tool (sbt)](https://www.scala-sbt.org/download.html),
    +  open a terminal, change to the movie-similarity project directory, and invoke the command `sbt assembly`.

       ```shell
       cd /home/your-user-name/movie-similarity
       sbt assembly
       ```

    This should create a file called `MovieSimilarity-assembly-1.0.jar` inside the `target/scala-2.12` subdirectory 
    of the main `movie-similarity` project directory. Move this newly created file up to the main project directory.

    ```shell
    mv /home/your-user-name/movie-similarity/{target/scala-2.12/,}MovieSimilarity-assembly-1.0.jar
    ```

3.  If you look in the `build.sbt` file, you will see that the version of Spark on which we want to run
    our program is **version 2.4.8**. The reason for this is that we happen to know 2.4.8 is the version 
    of Spark that's available on AWS. So, to test our `MovieSimilarity-assembly-1.0.jar` file, we need to download 
    [Spark version 2.4.8](https://archive.apache.org/dist/spark/spark-2.4.8/spark-2.4.8-bin-hadoop2.7.tgz) 
    from [here](https://archive.apache.org/dist/spark/spark-2.4.8/spark-2.4.8-bin-hadoop2.7.tgz).
    
    (If the Spark 2.4.8 download link above doesn't work for you, go to 
    https://spark.apache.org/downloads.html and find the link for the 
    [Spark release archives](https://archive.apache.org/dist/spark/).)

4.  Finally, we need to download the movielens 1m dataset ([ml-1m.zip](https://files.grouplens.org/datasets/movielens/ml-1m.zip))
    which our Spark program will analyze.  You can download [ml-1m.zip](https://files.grouplens.org/datasets/movielens/ml-1m.zip) from 
    [here](https://files.grouplens.org/datasets/movielens/ml-1m.zip).

    (If the movielens dataset link doesn't work for you, go to https://grouplens.org/datasets/movielens/ and find the ml-1m.zip 
    file on that page.)

    Unzip the ml-1m.zip file and put the `movies.dat` and `ratings.dat` files in the same directory as you put 
    the `MovieSimilarity-assembly-1.0.jar` file in step 1 above (e.g., the main project directory). (It doesn't really matter where you put
    the files, as long as all three of them are in the same directory.)

5.  Test the Spark program by invoking the following command (in a terminal window from the directory containing 
    the three files `MovieSimilarity-assembly-1.0.jar`, `movies.dat`, `ratings.dat`):
    
    ```shell
    ~/spark-2.4.8-bin-hadoop2.7/bin/spark-submit --executor-memory 1g MovieSimilarity-assembly-1.0.jar 260
    ```
    
If the job is successful, you should see something like the following in the terminal window:

```
22/12/13 22:26:53 WARN Utils: Your hostname, lampe resolves to a loopback address: 127.0.1.1; using 192.168.1.76 instead (on interface wlp2s0)
22/12/13 22:26:53 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by org.apache.spark.unsafe.Platform (file:/opt/spark-3.2.0-bin-hadoop3.2/jars/spark-unsafe_2.12-3.2.0.jar) to constructor java.nio.DirectByteBuffer(long,int)
WARNING: Please consider reporting this to the maintainers of org.apache.spark.unsafe.Platform
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
22/12/13 22:26:53 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties

Loading movie names...
```

Then Spark will compute for about 5 or 10 minutes, after which time you should see the following output.

```
Top 50 similar movies for Star Wars: Episode IV - A New Hope (1977)
Star Wars: Episode V - The Empire Strikes Back (1980)	score: 0.9897917106566659	strength: 2355
Raiders of the Lost Ark (1981)	score: 0.9855548278565054	strength: 1972
Star Wars: Episode VI - Return of the Jedi (1983)	score: 0.9841248359926177	strength: 2113
Indiana Jones and the Last Crusade (1989)	score: 0.9774440028650038	strength: 1397
Shawshank Redemption, The (1994)	score: 0.9768332708746131	strength: 1412
Usual Suspects, The (1995)	score: 0.9766875136831684	strength: 1194
Godfather, The (1972)	score: 0.9759284503618028	strength: 1583
Sixth Sense, The (1999)	score: 0.974688767430798	strength: 1480
Schindler's List (1993)	score: 0.9746820121947888	strength: 1422
Terminator, The (1984)	score: 0.9745821991816754	strength: 1746
Back to the Future (1985)	score: 0.9743476892310179	strength: 1845
Fugitive, The (1993)	score: 0.9740503810950097	strength: 1429
Princess Bride, The (1987)	score: 0.9737384179609926	strength: 1657
Matrix, The (1999)	score: 0.9732130645719457	strength: 1908
Butch Cassidy and the Sundance Kid (1969)	score: 0.9731825975678353	strength: 1048
Hunt for Red October, The (1990)	score: 0.9731286559518592	strength: 1229
Casablanca (1942)	score: 0.9730078799612648	strength: 1113
Saving Private Ryan (1998)	score: 0.9729484985516464	strength: 1709
Ghostbusters (1984)	score: 0.9726721862046535	strength: 1447
Die Hard (1988)	score: 0.9724843514829112	strength: 1369
L.A. Confidential (1997)	score: 0.9722077641949141	strength: 1416
Toy Story (1995)	score: 0.9721270419610062	strength: 1382
Stand by Me (1986)	score: 0.9718025936506943	strength: 1212
Close Encounters of the Third Kind (1977)	score: 0.9717491756795117	strength: 1242
Monty Python and the Holy Grail (1974)	score: 0.9717238750026624	strength: 1248
Silence of the Lambs, The (1991)	score: 0.9714472073187363	strength: 1587
Wizard of Oz, The (1939)	score: 0.9713633100564869	strength: 1346
Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb (1963)	score: 0.9713269232938938	strength: 1149
One Flew Over the Cuckoo's Nest (1975)	score: 0.9708527915400245	strength: 1125
Ferris Bueller's Day Off (1986)	score: 0.9705811698208009	strength: 1073
Godfather: Part II, The (1974)	score: 0.9704073574007531	strength: 1246
Terminator 2: Judgment Day (1991)	score: 0.9703674024729073	strength: 1889
E.T. the Extra-Terrestrial (1982)	score: 0.9702456868065551	strength: 1714
```

## Running your standalone Spark program in the cloud

### Sign up for an AWS account


https://docs.aws.amazon.com/emr/latest/ManagementGuide/emr-connect-ssh-prereqs.html


By default, Spark on an EMR cluster is configured to load data files from the
`/user/hadoop/` directory on the master cluster, so we need to create that directory
and place `movies.dat` and `ratings.dat` in there.

```
mkdir /user/hadoop
cp movies.dat ratings.dat
the master cluster (e.g., `hdfs://ip-172-31-27-212.us-west-1.compute.internal:8020/user/hadoop/`



ssh -i ~/aws_id_rsa.pem hadoop@ec2-54-219-61-238.us-west-1.compute.amazonaws.com


```
$ ssh -i ~/.ssh/aws_id_rsa.pem hadoop@ec2-54-219-61-238.us-west-1.compute.amazonaws.com
Warning: Identity file /home/williamdemeo/aws_id_rsa.pem not accessible: No such file or directory.
The authenticity of host 'ec2-54-219-61-238.us-west-1.compute.amazonaws.com (54.219.61.238)' can't be established.
ED25519 key fingerprint is SHA256:crH3DmHJuyqTCh9DvCpMPiTCod6bv+V4CbFogOSKgs0.
This key is not known by any other names
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added 'ec2-54-219-61-238.us-west-1.compute.amazonaws.com' (ED25519) to the list of known hosts.
Last login: Wed Dec 14 04:48:35 2022

       __|  __|_  )
       _|  (     /   Amazon Linux 2 AMI
      ___|\___|___|

https://aws.amazon.com/amazon-linux-2/
24 package(s) needed for security, out of 46 available
Run "sudo yum update" to apply all updates.
                                                                    
EEEEEEEEEEEEEEEEEEEE MMMMMMMM           MMMMMMMM RRRRRRRRRRRRRRR    
E::::::::::::::::::E M:::::::M         M:::::::M R::::::::::::::R   
EE:::::EEEEEEEEE:::E M::::::::M       M::::::::M R:::::RRRRRR:::::R 
  E::::E       EEEEE M:::::::::M     M:::::::::M RR::::R      R::::R
  E::::E             M::::::M:::M   M:::M::::::M   R:::R      R::::R
  E:::::EEEEEEEEEE   M:::::M M:::M M:::M M:::::M   R:::RRRRRR:::::R 
  E::::::::::::::E   M:::::M  M:::M:::M  M:::::M   R:::::::::::RR   
  E:::::EEEEEEEEEE   M:::::M   M:::::M   M:::::M   R:::RRRRRR::::R  
  E::::E             M:::::M    M:::M    M:::::M   R:::R      R::::R
  E::::E       EEEEE M:::::M     MMM     M:::::M   R:::R      R::::R
EE:::::EEEEEEEE::::E M:::::M             M:::::M   R:::R      R::::R
E::::::::::::::::::E M:::::M             M:::::M RR::::R      R::::R
EEEEEEEEEEEEEEEEEEEE MMMMMMM             MMMMMMM RRRRRRR      RRRRRR
                                                                    
[hadoop@ip-172-31-27-212 ~]$ 
```



[AWS EMR]: https://aws.amazon.com/emr/



[Scala Build Tool]: https://www.scala-sbt.org/

[sbt]: https://www.scala-sbt.org/
