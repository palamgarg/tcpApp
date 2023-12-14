package com.di.cis.domain;

import static com.di.cis.domain.PolicyMasterTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.di.cis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PolicyMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PolicyMaster.class);
        PolicyMaster policyMaster1 = getPolicyMasterSample1();
        PolicyMaster policyMaster2 = new PolicyMaster();
        assertThat(policyMaster1).isNotEqualTo(policyMaster2);

        policyMaster2.setId(policyMaster1.getId());
        assertThat(policyMaster1).isEqualTo(policyMaster2);

        policyMaster2 = getPolicyMasterSample2();
        assertThat(policyMaster1).isNotEqualTo(policyMaster2);
    }
}
