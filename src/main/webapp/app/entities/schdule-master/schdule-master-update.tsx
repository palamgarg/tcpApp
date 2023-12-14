import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ISchduleMaster } from 'app/shared/model/schdule-master.model';
import { getEntity, updateEntity, createEntity, reset } from './schdule-master.reducer';

export const SchduleMasterUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const schduleMasterEntity = useAppSelector(state => state.cis.schduleMaster.entity);
  const loading = useAppSelector(state => state.cis.schduleMaster.loading);
  const updating = useAppSelector(state => state.cis.schduleMaster.updating);
  const updateSuccess = useAppSelector(state => state.cis.schduleMaster.updateSuccess);

  const handleClose = () => {
    navigate('/schdule-master');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.installmentNumber !== undefined && typeof values.installmentNumber !== 'number') {
      values.installmentNumber = Number(values.installmentNumber);
    }
    if (values.reducingBalance !== undefined && typeof values.reducingBalance !== 'number') {
      values.reducingBalance = Number(values.reducingBalance);
    }
    if (values.principleAmount !== undefined && typeof values.principleAmount !== 'number') {
      values.principleAmount = Number(values.principleAmount);
    }
    if (values.interest !== undefined && typeof values.interest !== 'number') {
      values.interest = Number(values.interest);
    }
    if (values.totalInstallment !== undefined && typeof values.totalInstallment !== 'number') {
      values.totalInstallment = Number(values.totalInstallment);
    }
    if (values.dueDate !== undefined && typeof values.dueDate !== 'number') {
      values.dueDate = Number(values.dueDate);
    }

    const entity = {
      ...schduleMasterEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...schduleMasterEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cisApp.schduleMaster.home.createOrEditLabel" data-cy="SchduleMasterCreateUpdateHeading">
            <Translate contentKey="cisApp.schduleMaster.home.createOrEditLabel">Create or edit a SchduleMaster</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="schdule-master-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cisApp.schduleMaster.installmentNumber')}
                id="schdule-master-installmentNumber"
                name="installmentNumber"
                data-cy="installmentNumber"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.schduleMaster.reducingBalance')}
                id="schdule-master-reducingBalance"
                name="reducingBalance"
                data-cy="reducingBalance"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.schduleMaster.principleAmount')}
                id="schdule-master-principleAmount"
                name="principleAmount"
                data-cy="principleAmount"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.schduleMaster.interest')}
                id="schdule-master-interest"
                name="interest"
                data-cy="interest"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.schduleMaster.totalInstallment')}
                id="schdule-master-totalInstallment"
                name="totalInstallment"
                data-cy="totalInstallment"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.schduleMaster.dueDate')}
                id="schdule-master-dueDate"
                name="dueDate"
                data-cy="dueDate"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.schduleMaster.remarks')}
                id="schdule-master-remarks"
                name="remarks"
                data-cy="remarks"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/schdule-master" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default SchduleMasterUpdate;
