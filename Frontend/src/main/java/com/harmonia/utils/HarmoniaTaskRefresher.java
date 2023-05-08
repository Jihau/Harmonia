package com.harmonia.utils;

public class HarmoniaTaskRefresher implements Runnable {
    private volatile Thread clockThread = null;

    /**
     * Starts the refresher
     */
    public HarmoniaTaskRefresher() {
        start();
    }
    /**
     * start the thread
     */
    public void start() {
        if (clockThread == null) {
            clockThread = new Thread(this, "Clock");
            clockThread.start();
        }
    }

    /**
     * attempt to load direct messages every 3500 milliseconds
     */
    public void run() {
        Thread myThread = Thread.currentThread();
        while (clockThread == myThread) {
            HarmoniaDataLoader.loadDirectMessagesByUserId(true);
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
