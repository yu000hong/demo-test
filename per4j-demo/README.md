# Perf4J Demo

### Log Format

The logs Perf4J printed are like this:

```
start[1479886494713] time[3040] tag[Demo]
start[1479886494713] time[3040] tag[Demo]
start[1479886494714] time[3040] tag[Demo]
start[1479886494714] time[3040] tag[Demo]
start[1479886494712] time[3042] tag[Demo]
start[1479886494714] time[3041] tag[Demo]
start[1479886494713] time[3039] tag[Demo]
start[1479886494713] time[3042] tag[Demo]
start[1479886494714] time[3042] tag[Demo]
start[1479886494713] time[3042] tag[Demo]
```

### Log Analysis

We can use `LogParser` built in Perf4J to analyze performance. 

Some help:

```bash
$ java -jar lib/perf4j-0.9.16.jar  --help
Usage: LogParser [-o|--out|--output outputFile] [-g|--graph graphingOutputFile] [-t|--timeslice timeslice] [-r] [-f|--format text|csv] [logInputFile]
Arguments:
  logInputFile - The log file to be parsed. If not specified, log data is read from stdin.
  -o|--out|--output outputFile - The file where generated statistics should be written. If not specified, statistics are written to stdout.
  -g|--graph graphingOutputFile - The file where generated perf graphs should be written. If not specified, no graphs are written.
  -t|--timeslice timeslice - The length of time (in ms) of each timeslice for which statistics should be generated. Defaults to 30000 ms.
  -r - Whether or not statistics rollups should be generated. If not specified, rollups are not generated.
  -f|--format text|csv - The format for the statistics output, either plain text or CSV. Defaults to text.
                         If format is csv, then the columns output are tag, start, stop, mean, min, max, stddev, and count.
```

Sample:

```bash
$ java -jar lib/perf4j-0.9.16.jar stats.log
Performance Statistics   2016-11-23 15:34:30 - 2016-11-23 15:35:00
Tag                                                  Avg(ms)         Min         Max     Std Dev       Count
Demo                                                  3040.8        3039        3042         1.1          10
```

### StopWatch

There are 4 built-in StopWatches: `LoggingStopWatch`, `Log4JStopWatch`, `JavaLogStopWatch`, `Slf4JStopWatch`. 

The hierarchy of these classes:

```
                        StopWatch
                            |
                    LoggingStopWatch
                            |
      ----------------------|----------------------
      |                     |                     |
Log4JStopWatch      JavaLogStopWatch        Slf4JStopWatch
```

**StopWatch**

StopWatch is the super class, it performs all the core functionality, such as time computation, log format, etc.

**LoggingStopWatch**

LoggingStopWatch will print all logs using System.out.println() function.

**Log4JStopWatch | JavaLogStopWatch | Slf4JStopWatch**

These 3 StopWatches will use log4j, java log, slf4j to print log individually.

### @Profiled Annotation

We can see that the stopwatch is littering around in the code. To keep our code clean we can remove the stopwatch 
from the method body. As an alternative we can take advantage of the @Profiled annotation, which uses AOP to record 
timing information. The sample code is shown below:

```java
@Profiled(tag="tagName")
public void method2(){
    ... method body ...
}
```

```java
@Profiled(tag = "dynamicTag_{$0}")
public void profiledExample(String tagSuffix) {
    ... business logic only here
}
```


### References

- [Performance Analysis and Monitoring with Perf4J](https://www.infoq.com/articles/perf4j)

- [Perf4J GitHub](https://github.com/perf4j/perf4j)