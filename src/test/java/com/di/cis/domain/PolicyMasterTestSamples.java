package com.di.cis.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PolicyMasterTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static PolicyMaster getPolicyMasterSample1() {
        return new PolicyMaster()
            .id(1L)
            .purpuseName("purpuseName1")
            .policyName("policyName1")
            .chargesType("chargesType1")
            .numberOfInstallments(1)
            .installmentDuration(1);
    }

    public static PolicyMaster getPolicyMasterSample2() {
        return new PolicyMaster()
            .id(2L)
            .purpuseName("purpuseName2")
            .policyName("policyName2")
            .chargesType("chargesType2")
            .numberOfInstallments(2)
            .installmentDuration(2);
    }

    public static PolicyMaster getPolicyMasterRandomSampleGenerator() {
        return new PolicyMaster()
            .id(longCount.incrementAndGet())
            .purpuseName(UUID.randomUUID().toString())
            .policyName(UUID.randomUUID().toString())
            .chargesType(UUID.randomUUID().toString())
            .numberOfInstallments(intCount.incrementAndGet())
            .installmentDuration(intCount.incrementAndGet());
    }
}
