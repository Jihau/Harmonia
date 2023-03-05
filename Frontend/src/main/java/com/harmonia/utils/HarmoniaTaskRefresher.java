package com.harmonia.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HarmoniaTaskRefresher implements Runnable {
    private volatile Thread clockThread = null;
    public void start() {
        if (clockThread == null) {
            clockThread = new Thread(this, "Clock");
            clockThread.start();
        }
    }

    public HarmoniaTaskRefresher() {
        start();
    }

    public void run() {
        Thread myThread = Thread.currentThread();
        while (clockThread == myThread) {
            HarmoniaDataLoader.loadDirectMessagesByUserId(true);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){ }
        }
    }
}
