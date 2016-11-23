package com.github.yu000hong.demo.perf4j

import org.perf4j.slf4j.Slf4JStopWatch

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Slf4JStopWatchDemo {


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
            def stopWatch = new Slf4JStopWatch()
            TimeUnit.SECONDS.sleep(3)
            stopWatch.stop('Slf4JStopWatchDemo')
        }
    }

}
