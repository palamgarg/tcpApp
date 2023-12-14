import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './policy-master.reducer';

export const PolicyMasterDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const policyMasterEntity = useAppSelector(state => state.cis.policyMaster.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="policyMasterDetailsHeading">
          <Translate contentKey="cisApp.policyMaster.detail.title">PolicyMaster</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.id}</dd>
          <dt>
            <span id="purpuseName">
              <Translate contentKey="cisApp.policyMaster.purpuseName">Purpuse Name</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.purpuseName}</dd>
          <dt>
            <span id="policyName">
              <Translate contentKey="cisApp.policyMaster.policyName">Policy Name</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.policyName}</dd>
          <dt>
            <span id="chargesType">
              <Translate contentKey="cisApp.policyMaster.chargesType">Charges Type</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.chargesType}</dd>
          <dt>
            <span id="interestRate">
              <Translate contentKey="cisApp.policyMaster.interestRate">Interest Rate</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.interestRate}</dd>
          <dt>
            <span id="numberOfInstallments">
              <Translate contentKey="cisApp.policyMaster.numberOfInstallments">Number Of Installments</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.numberOfInstallments}</dd>
          <dt>
            <span id="penaltyRateOfInterest">
              <Translate contentKey="cisApp.policyMaster.penaltyRateOfInterest">Penalty Rate Of Interest</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.penaltyRateOfInterest}</dd>
          <dt>
            <span id="installmentDuration">
              <Translate contentKey="cisApp.policyMaster.installmentDuration">Installment Duration</Translate>
            </span>
          </dt>
          <dd>{policyMasterEntity.installmentDuration}</dd>
        </dl>
        <Button tag={Link} to="/policy-master" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/policy-master/${policyMasterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PolicyMasterDetail;
