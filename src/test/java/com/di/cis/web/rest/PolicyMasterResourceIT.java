package com.di.cis.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.di.cis.IntegrationTest;
import com.di.cis.domain.PolicyMaster;
import com.di.cis.repository.EntityManager;
import com.di.cis.repository.PolicyMasterRepository;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link PolicyMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PolicyMasterResourceIT {

    private static final String DEFAULT_PURPUSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PURPUSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POLICY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_POLICY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHARGES_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CHARGES_TYPE = "BBBBBBBBBB";

    private static final Double DEFAULT_INTEREST_RATE = 1D;
    private static final Double UPDATED_INTEREST_RATE = 2D;

    private static final Integer DEFAULT_NUMBER_OF_INSTALLMENTS = 1;
    private static final Integer UPDATED_NUMBER_OF_INSTALLMENTS = 2;

    private static final Double DEFAULT_PENALTY_RATE_OF_INTEREST = 1D;
    private static final Double UPDATED_PENALTY_RATE_OF_INTEREST = 2D;

    private static final Integer DEFAULT_INSTALLMENT_DURATION = 1;
    private static final Integer UPDATED_INSTALLMENT_DURATION = 2;

    private static final String ENTITY_API_URL = "/api/policy-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PolicyMasterRepository policyMasterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PolicyMaster policyMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PolicyMaster createEntity(EntityManager em) {
        PolicyMaster policyMaster = new PolicyMaster()
            .purpuseName(DEFAULT_PURPUSE_NAME)
            .policyName(DEFAULT_POLICY_NAME)
            .chargesType(DEFAULT_CHARGES_TYPE)
            .interestRate(DEFAULT_INTEREST_RATE)
            .numberOfInstallments(DEFAULT_NUMBER_OF_INSTALLMENTS)
            .penaltyRateOfInterest(DEFAULT_PENALTY_RATE_OF_INTEREST)
            .installmentDuration(DEFAULT_INSTALLMENT_DURATION);
        return policyMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PolicyMaster createUpdatedEntity(EntityManager em) {
        PolicyMaster policyMaster = new PolicyMaster()
            .purpuseName(UPDATED_PURPUSE_NAME)
            .policyName(UPDATED_POLICY_NAME)
            .chargesType(UPDATED_CHARGES_TYPE)
            .interestRate(UPDATED_INTEREST_RATE)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .penaltyRateOfInterest(UPDATED_PENALTY_RATE_OF_INTEREST)
            .installmentDuration(UPDATED_INSTALLMENT_DURATION);
        return policyMaster;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PolicyMaster.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        policyMaster = createEntity(em);
    }

    @Test
    void createPolicyMaster() throws Exception {
        int databaseSizeBeforeCreate = policyMasterRepository.findAll().collectList().block().size();
        // Create the PolicyMaster
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeCreate + 1);
        PolicyMaster testPolicyMaster = policyMasterList.get(policyMasterList.size() - 1);
        assertThat(testPolicyMaster.getPurpuseName()).isEqualTo(DEFAULT_PURPUSE_NAME);
        assertThat(testPolicyMaster.getPolicyName()).isEqualTo(DEFAULT_POLICY_NAME);
        assertThat(testPolicyMaster.getChargesType()).isEqualTo(DEFAULT_CHARGES_TYPE);
        assertThat(testPolicyMaster.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testPolicyMaster.getNumberOfInstallments()).isEqualTo(DEFAULT_NUMBER_OF_INSTALLMENTS);
        assertThat(testPolicyMaster.getPenaltyRateOfInterest()).isEqualTo(DEFAULT_PENALTY_RATE_OF_INTEREST);
        assertThat(testPolicyMaster.getInstallmentDuration()).isEqualTo(DEFAULT_INSTALLMENT_DURATION);
    }

    @Test
    void createPolicyMasterWithExistingId() throws Exception {
        // Create the PolicyMaster with an existing ID
        policyMaster.setId(1L);

        int databaseSizeBeforeCreate = policyMasterRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllPolicyMastersAsStream() {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        List<PolicyMaster> policyMasterList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(PolicyMaster.class)
            .getResponseBody()
            .filter(policyMaster::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(policyMasterList).isNotNull();
        assertThat(policyMasterList).hasSize(1);
        PolicyMaster testPolicyMaster = policyMasterList.get(0);
        assertThat(testPolicyMaster.getPurpuseName()).isEqualTo(DEFAULT_PURPUSE_NAME);
        assertThat(testPolicyMaster.getPolicyName()).isEqualTo(DEFAULT_POLICY_NAME);
        assertThat(testPolicyMaster.getChargesType()).isEqualTo(DEFAULT_CHARGES_TYPE);
        assertThat(testPolicyMaster.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testPolicyMaster.getNumberOfInstallments()).isEqualTo(DEFAULT_NUMBER_OF_INSTALLMENTS);
        assertThat(testPolicyMaster.getPenaltyRateOfInterest()).isEqualTo(DEFAULT_PENALTY_RATE_OF_INTEREST);
        assertThat(testPolicyMaster.getInstallmentDuration()).isEqualTo(DEFAULT_INSTALLMENT_DURATION);
    }

    @Test
    void getAllPolicyMasters() {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        // Get all the policyMasterList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(policyMaster.getId().intValue()))
            .jsonPath("$.[*].purpuseName")
            .value(hasItem(DEFAULT_PURPUSE_NAME))
            .jsonPath("$.[*].policyName")
            .value(hasItem(DEFAULT_POLICY_NAME))
            .jsonPath("$.[*].chargesType")
            .value(hasItem(DEFAULT_CHARGES_TYPE))
            .jsonPath("$.[*].interestRate")
            .value(hasItem(DEFAULT_INTEREST_RATE.doubleValue()))
            .jsonPath("$.[*].numberOfInstallments")
            .value(hasItem(DEFAULT_NUMBER_OF_INSTALLMENTS))
            .jsonPath("$.[*].penaltyRateOfInterest")
            .value(hasItem(DEFAULT_PENALTY_RATE_OF_INTEREST.doubleValue()))
            .jsonPath("$.[*].installmentDuration")
            .value(hasItem(DEFAULT_INSTALLMENT_DURATION));
    }

    @Test
    void getPolicyMaster() {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        // Get the policyMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, policyMaster.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(policyMaster.getId().intValue()))
            .jsonPath("$.purpuseName")
            .value(is(DEFAULT_PURPUSE_NAME))
            .jsonPath("$.policyName")
            .value(is(DEFAULT_POLICY_NAME))
            .jsonPath("$.chargesType")
            .value(is(DEFAULT_CHARGES_TYPE))
            .jsonPath("$.interestRate")
            .value(is(DEFAULT_INTEREST_RATE.doubleValue()))
            .jsonPath("$.numberOfInstallments")
            .value(is(DEFAULT_NUMBER_OF_INSTALLMENTS))
            .jsonPath("$.penaltyRateOfInterest")
            .value(is(DEFAULT_PENALTY_RATE_OF_INTEREST.doubleValue()))
            .jsonPath("$.installmentDuration")
            .value(is(DEFAULT_INSTALLMENT_DURATION));
    }

    @Test
    void getNonExistingPolicyMaster() {
        // Get the policyMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPolicyMaster() throws Exception {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();

        // Update the policyMaster
        PolicyMaster updatedPolicyMaster = policyMasterRepository.findById(policyMaster.getId()).block();
        updatedPolicyMaster
            .purpuseName(UPDATED_PURPUSE_NAME)
            .policyName(UPDATED_POLICY_NAME)
            .chargesType(UPDATED_CHARGES_TYPE)
            .interestRate(UPDATED_INTEREST_RATE)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .penaltyRateOfInterest(UPDATED_PENALTY_RATE_OF_INTEREST)
            .installmentDuration(UPDATED_INSTALLMENT_DURATION);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedPolicyMaster.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedPolicyMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
        PolicyMaster testPolicyMaster = policyMasterList.get(policyMasterList.size() - 1);
        assertThat(testPolicyMaster.getPurpuseName()).isEqualTo(UPDATED_PURPUSE_NAME);
        assertThat(testPolicyMaster.getPolicyName()).isEqualTo(UPDATED_POLICY_NAME);
        assertThat(testPolicyMaster.getChargesType()).isEqualTo(UPDATED_CHARGES_TYPE);
        assertThat(testPolicyMaster.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testPolicyMaster.getNumberOfInstallments()).isEqualTo(UPDATED_NUMBER_OF_INSTALLMENTS);
        assertThat(testPolicyMaster.getPenaltyRateOfInterest()).isEqualTo(UPDATED_PENALTY_RATE_OF_INTEREST);
        assertThat(testPolicyMaster.getInstallmentDuration()).isEqualTo(UPDATED_INSTALLMENT_DURATION);
    }

    @Test
    void putNonExistingPolicyMaster() throws Exception {
        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();
        policyMaster.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, policyMaster.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPolicyMaster() throws Exception {
        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();
        policyMaster.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPolicyMaster() throws Exception {
        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();
        policyMaster.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePolicyMasterWithPatch() throws Exception {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();

        // Update the policyMaster using partial update
        PolicyMaster partialUpdatedPolicyMaster = new PolicyMaster();
        partialUpdatedPolicyMaster.setId(policyMaster.getId());

        partialUpdatedPolicyMaster
            .purpuseName(UPDATED_PURPUSE_NAME)
            .policyName(UPDATED_POLICY_NAME)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .installmentDuration(UPDATED_INSTALLMENT_DURATION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPolicyMaster.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPolicyMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
        PolicyMaster testPolicyMaster = policyMasterList.get(policyMasterList.size() - 1);
        assertThat(testPolicyMaster.getPurpuseName()).isEqualTo(UPDATED_PURPUSE_NAME);
        assertThat(testPolicyMaster.getPolicyName()).isEqualTo(UPDATED_POLICY_NAME);
        assertThat(testPolicyMaster.getChargesType()).isEqualTo(DEFAULT_CHARGES_TYPE);
        assertThat(testPolicyMaster.getInterestRate()).isEqualTo(DEFAULT_INTEREST_RATE);
        assertThat(testPolicyMaster.getNumberOfInstallments()).isEqualTo(UPDATED_NUMBER_OF_INSTALLMENTS);
        assertThat(testPolicyMaster.getPenaltyRateOfInterest()).isEqualTo(DEFAULT_PENALTY_RATE_OF_INTEREST);
        assertThat(testPolicyMaster.getInstallmentDuration()).isEqualTo(UPDATED_INSTALLMENT_DURATION);
    }

    @Test
    void fullUpdatePolicyMasterWithPatch() throws Exception {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();

        // Update the policyMaster using partial update
        PolicyMaster partialUpdatedPolicyMaster = new PolicyMaster();
        partialUpdatedPolicyMaster.setId(policyMaster.getId());

        partialUpdatedPolicyMaster
            .purpuseName(UPDATED_PURPUSE_NAME)
            .policyName(UPDATED_POLICY_NAME)
            .chargesType(UPDATED_CHARGES_TYPE)
            .interestRate(UPDATED_INTEREST_RATE)
            .numberOfInstallments(UPDATED_NUMBER_OF_INSTALLMENTS)
            .penaltyRateOfInterest(UPDATED_PENALTY_RATE_OF_INTEREST)
            .installmentDuration(UPDATED_INSTALLMENT_DURATION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPolicyMaster.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPolicyMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
        PolicyMaster testPolicyMaster = policyMasterList.get(policyMasterList.size() - 1);
        assertThat(testPolicyMaster.getPurpuseName()).isEqualTo(UPDATED_PURPUSE_NAME);
        assertThat(testPolicyMaster.getPolicyName()).isEqualTo(UPDATED_POLICY_NAME);
        assertThat(testPolicyMaster.getChargesType()).isEqualTo(UPDATED_CHARGES_TYPE);
        assertThat(testPolicyMaster.getInterestRate()).isEqualTo(UPDATED_INTEREST_RATE);
        assertThat(testPolicyMaster.getNumberOfInstallments()).isEqualTo(UPDATED_NUMBER_OF_INSTALLMENTS);
        assertThat(testPolicyMaster.getPenaltyRateOfInterest()).isEqualTo(UPDATED_PENALTY_RATE_OF_INTEREST);
        assertThat(testPolicyMaster.getInstallmentDuration()).isEqualTo(UPDATED_INSTALLMENT_DURATION);
    }

    @Test
    void patchNonExistingPolicyMaster() throws Exception {
        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();
        policyMaster.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, policyMaster.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPolicyMaster() throws Exception {
        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();
        policyMaster.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPolicyMaster() throws Exception {
        int databaseSizeBeforeUpdate = policyMasterRepository.findAll().collectList().block().size();
        policyMaster.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(policyMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PolicyMaster in the database
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePolicyMaster() {
        // Initialize the database
        policyMasterRepository.save(policyMaster).block();

        int databaseSizeBeforeDelete = policyMasterRepository.findAll().collectList().block().size();

        // Delete the policyMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, policyMaster.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PolicyMaster> policyMasterList = policyMasterRepository.findAll().collectList().block();
        assertThat(policyMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
