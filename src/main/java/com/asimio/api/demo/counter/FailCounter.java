package com.asimio.api.demo.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class FailCounter {
    public static final AtomicInteger TOTAL_REQUEST = new AtomicInteger(0);
    public static AtomicInteger FAIL_REQUEST = new AtomicInteger(0);;
    public static AtomicInteger SUCCESS_REQUEST = new AtomicInteger(0);;
    public static AtomicInteger TOTAL_SAMPLE = new AtomicInteger(0);;
    public static AtomicInteger FAIL_SAMPLE_COUNT = new AtomicInteger(0);;
    public static AtomicInteger SUCCESS_SAMPLE_COUNT = new AtomicInteger(0);;

    public static void incrementTotalRequest(){
        TOTAL_REQUEST.incrementAndGet();
    }

    public static void incrementFailCount(){
        FAIL_SAMPLE_COUNT.incrementAndGet();
    }

    public static void incrementSuccessCount(){
        SUCCESS_SAMPLE_COUNT.incrementAndGet();
    }

    public static void incrementSampleCount(){
        TOTAL_SAMPLE.incrementAndGet();
    }

    public static void incrementFailRequest(){
        FAIL_REQUEST.incrementAndGet();
    }

    public static void incrementSuccessRequest(){
        SUCCESS_REQUEST.incrementAndGet();
    }

    public static void displayResult(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println("TOTAL_REQUEST                = " + TOTAL_REQUEST);
        System.out.println("FAIL_REQUEST                 = " + FAIL_REQUEST);
        System.out.println("SUCCESS_REQUEST              = " + SUCCESS_REQUEST);
        System.out.println("FAIL REQUEST PERCENTAGE,(%)  = " + (double)FAIL_REQUEST.get() * 100.00 / (double)TOTAL_REQUEST.get());
        System.out.println("*******************************************************************");
        System.out.println("TOTAL_SAMPLE                 = " + TOTAL_SAMPLE);
        System.out.println("FAIL_SAMPLE_COUNT            = " + FAIL_SAMPLE_COUNT);
        System.out.println("SUCCESS_SAMPLE_COUNT         = " + SUCCESS_SAMPLE_COUNT);
        if(TOTAL_SAMPLE.get() > 0)
          System.out.println("FAIL SAMPLE PERCENTAGE(%)  = " + (double)FAIL_SAMPLE_COUNT.get() * 100.00/(double)TOTAL_SAMPLE.get());
        System.out.println("----------------------------------------------------------------------");

    }
}
