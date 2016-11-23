package com.github.yu000hong.demo.perf4j

import org.perf4j.javalog.JavaLogStopWatch

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class JavaLogStopWatchDemo {

    public static void main(String[] args) {
        def executorService = Executors.newFixedThreadPool(10)
        (1..10).each {
            executorService.execute(new Task())
        }
        executorService.shutdown()
    }

    static class Task implements Runnable {
        @Override
        void run() {
            def stopWatch = new JavaLogStopWatch()
            TimeUnit.SECONDS.sleep(3)
            stopWatch.stop('JavaLogStopWatchDemo')
        }
    }

}
