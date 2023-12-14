import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './schdule-master.reducer';

export const SchduleMasterDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const schduleMasterEntity = useAppSelector(state => state.cis.schduleMaster.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="schduleMasterDetailsHeading">
          <Translate contentKey="cisApp.schduleMaster.detail.title">SchduleMaster</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.id}</dd>
          <dt>
            <span id="installmentNumber">
              <Translate contentKey="cisApp.schduleMaster.installmentNumber">Installment Number</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.installmentNumber}</dd>
          <dt>
            <span id="reducingBalance">
              <Translate contentKey="cisApp.schduleMaster.reducingBalance">Reducing Balance</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.reducingBalance}</dd>
          <dt>
            <span id="principleAmount">
              <Translate contentKey="cisApp.schduleMaster.principleAmount">Principle Amount</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.principleAmount}</dd>
          <dt>
            <span id="interest">
              <Translate contentKey="cisApp.schduleMaster.interest">Interest</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.interest}</dd>
          <dt>
            <span id="totalInstallment">
              <Translate contentKey="cisApp.schduleMaster.totalInstallment">Total Installment</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.totalInstallment}</dd>
          <dt>
            <span id="dueDate">
              <Translate contentKey="cisApp.schduleMaster.dueDate">Due Date</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.dueDate}</dd>
          <dt>
            <span id="remarks">
              <Translate contentKey="cisApp.schduleMaster.remarks">Remarks</Translate>
            </span>
          </dt>
          <dd>{schduleMasterEntity.remarks}</dd>
        </dl>
        <Button tag={Link} to="/schdule-master" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/schdule-master/${schduleMasterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SchduleMasterDetail;
