package com.di.cis.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SchduleMasterTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static SchduleMaster getSchduleMasterSample1() {
        return new SchduleMaster().id(1L).installmentNumber(1).dueDate(1L).remarks("remarks1");
    }

    public static SchduleMaster getSchduleMasterSample2() {
        return new SchduleMaster().id(2L).installmentNumber(2).dueDate(2L).remarks("remarks2");
    }

    public static SchduleMaster getSchduleMasterRandomSampleGenerator() {
        return new SchduleMaster()
            .id(longCount.incrementAndGet())
            .installmentNumber(intCount.incrementAndGet())
            .dueDate(longCount.incrementAndGet())
            .remarks(UUID.randomUUID().toString());
    }
}
