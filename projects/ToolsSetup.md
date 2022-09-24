# CS 644: Introduction to Big Data (§§ 101, 103, 105)

## Tools Setup

**Note**. The following sections contain information about all the tools you
will need to complete the course. 

In order to work on the programming projects, you need to have the following tools installed on your machine:

<!-- Oracle  JDK, the Java Development Kit, version 1.8. Note some assignments may  work with Java 1.7, but we grade the assignments with 1.8, so we  strongly recommend to use Java 1.8. Check you have the right version by  typing in the console: -->

### Compiler and Build Tool

You need to install the Java Development Kit (JDK), the Scala compiler, and a build tool to work on Scala projects on your computer.

Please follow the official installation instructions here: https://docs.scala-lang.org/getting-started

M​ake sure you have at least JDK 11 (you can check by running the command java -version in a terminal).

If you're using Windows and you see a message about a missing `VCRUNTIME140.dll` file, please find out how to fix it on [this page](https://answers.microsoft.com/en-us/windows/forum/all/vcruntime140dll-missing-how-can-i-fix/c5c1461a-3b75-428c-9c5d-18d56a11f2bc).


Make sure you have the command-line tool "sbt" (the Scala Build Tool). Run
the command `sbt --version` on the command-line. You should see something
like the following (`$` is the shell prompt):

```shell
$ sbt --version
downloading sbt launcher 1.7.1
[info] [launcher] getting org.scala-sbt sbt 1.7.1  (this may take some time)...
[info] [launcher] getting Scala 2.12.16 (for sbt)...
sbt version in this project: 1.7.1
sbt script version: 1.7.1
$
```
   
If you have an even more recent version of `sbt`, it's likely that will also work fine.
   
### Code Editor

You are free to use your preferred code editor, or IDE, to work with Scala and
Spark. If you like working with an IDE, two of the best options for Scala are
Metals, which works with many editors (e.g., vim, emacs, or VSCode) and Intellij with the Scala plugin.

#### Metals

Metals is a technology that brings IDE features to text editors (such as vim, emacs, VS Code, etc.).

Follow the installation instructions here: https://scalameta.org/metals/docs/editors/overview.html


#### IntelliJ with the Scala Plugin

IntelliJ Idea is an IDE developed by Jetbrains. The community version of Idea and the Scala plugin are free.

Follow the installation instructions here: https://www.jetbrains.com/help/idea/installation-guide.html

Then, install the Scala plugin: https://www.jetbrains.com/help/idea/discover-intellij-idea-for-scala.html




<!-- 1 -->
<!-- java -version -->
<!-- Scala Build Tool (sbt), a build tool for Scala, version 0.13.x, or newer. -->

<!-- The Scala IDE for Eclipse, Intellij IDEA or another IDE of your choice. -->

<!-- Please follow the instructions on this page carefully. -->

<!-- Installing the JDK -->
<!-- Linux -->
<!-- Ubuntu, Debian: To install the JDK using apt-get, execute the following command in a terminal sudo apt-get install openjdk-8-jdk -->

<!-- Fedora, Oracle, Red Had: To install the JDK using yum, execute the following command in a terminal su -c "yum install java-1.8.0-openjdk-devel" -->

<!-- Manual Installation: To install the JDK manually on a Linux system, follow these steps: -->

<!-- Download the .tar.gz archive from the Oracle website -->

<!-- 2. Unpack the downloaded archive to a directory of your choice -->

<!-- 3. Add the bin/ directory of the extracted JDK to the PATH environment variable. Open the file ~/.bashrc in an editor (create it if it doesn't exist) and add the following line: -->

<!-- 1 -->
<!-- export PATH="/PATH/TO/YOUR/jdk1.8.0-VERSION/bin:$PATH" -->
<!-- If you are using another shell, add that line in the corresponding configuration file (e.g. ~/.zshrc for zsh). -->

<!-- Verify your setup: Open a new terminal (to apply the changed ~/.bashrc  in case you did the manual installation) and type java -version. If you  have problems installing the JDK, ask for help on the forums. -->

<!-- Mac OS X -->
<!-- Mac OS X either comes with a pre-installed JDK, or installs it automatically. -->

<!-- To  verify your JDK installation, open the Terminal application in  /Applications/Utilities/ and type java -version. If the JDK is not yet  installed, the system will ask you if you would like to download and  install it. Make sure you install Java 1.8. -->

<!-- Windows -->
<!-- Download the JDK installer for Windows from the Oracle website. -->

<!-- Run the installer. -->

<!-- Add the bin  directory of the installed JDK to the PATH environment variable, as  described here. -->

<!-- To  verify the JDK installation, open the Command Prompt and type `java  -version`. If you run into any problem, go to the official Oracle documentation. -->

<!-- Installing sbt -->
<!-- Follow the instructions for your platform to get it running. -->

<!-- This course requires sbt version >0.13.x.  If you have previously installed sbt 0.12.x, you need to uninstall it  and install a newer version. sbt 0.13.x can be used for projects and  other courses requiring sbt 0.12.x, but not the other way around. If in  doubt, you can check your currently installed sbt like this: in an  arbitrary directory that is not a programming assignment or otherwise an  sbt project, run: -->

<!-- 1 -->
<!-- $ sbt about -->
<!-- You should see something like this: -->

<!-- 1 -->
<!-- This is sbt 1.1.0 -->
<!-- If the sbt command is not found, you need to install sbt. In this case, go to the official instructions for your platform. -->

