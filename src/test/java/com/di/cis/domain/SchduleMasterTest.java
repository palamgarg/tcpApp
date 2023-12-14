package com.di.cis.domain;

import static com.di.cis.domain.SchduleMasterTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.di.cis.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SchduleMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SchduleMaster.class);
        SchduleMaster schduleMaster1 = getSchduleMasterSample1();
        SchduleMaster schduleMaster2 = new SchduleMaster();
        assertThat(schduleMaster1).isNotEqualTo(schduleMaster2);

        schduleMaster2.setId(schduleMaster1.getId());
        assertThat(schduleMaster1).isEqualTo(schduleMaster2);

        schduleMaster2 = getSchduleMasterSample2();
        assertThat(schduleMaster1).isNotEqualTo(schduleMaster2);
    }
}
