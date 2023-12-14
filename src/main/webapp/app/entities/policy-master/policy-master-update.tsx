import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPolicyMaster } from 'app/shared/model/policy-master.model';
import { getEntity, updateEntity, createEntity, reset } from './policy-master.reducer';

export const PolicyMasterUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const policyMasterEntity = useAppSelector(state => state.cis.policyMaster.entity);
  const loading = useAppSelector(state => state.cis.policyMaster.loading);
  const updating = useAppSelector(state => state.cis.policyMaster.updating);
  const updateSuccess = useAppSelector(state => state.cis.policyMaster.updateSuccess);

  const handleClose = () => {
    navigate('/policy-master');
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
    if (values.interestRate !== undefined && typeof values.interestRate !== 'number') {
      values.interestRate = Number(values.interestRate);
    }
    if (values.numberOfInstallments !== undefined && typeof values.numberOfInstallments !== 'number') {
      values.numberOfInstallments = Number(values.numberOfInstallments);
    }
    if (values.penaltyRateOfInterest !== undefined && typeof values.penaltyRateOfInterest !== 'number') {
      values.penaltyRateOfInterest = Number(values.penaltyRateOfInterest);
    }
    if (values.installmentDuration !== undefined && typeof values.installmentDuration !== 'number') {
      values.installmentDuration = Number(values.installmentDuration);
    }

    const entity = {
      ...policyMasterEntity,
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
          ...policyMasterEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="cisApp.policyMaster.home.createOrEditLabel" data-cy="PolicyMasterCreateUpdateHeading">
            <Translate contentKey="cisApp.policyMaster.home.createOrEditLabel">Create or edit a PolicyMaster</Translate>
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
                  id="policy-master-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('cisApp.policyMaster.purpuseName')}
                id="policy-master-purpuseName"
                name="purpuseName"
                data-cy="purpuseName"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.policyMaster.policyName')}
                id="policy-master-policyName"
                name="policyName"
                data-cy="policyName"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.policyMaster.chargesType')}
                id="policy-master-chargesType"
                name="chargesType"
                data-cy="chargesType"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.policyMaster.interestRate')}
                id="policy-master-interestRate"
                name="interestRate"
                data-cy="interestRate"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.policyMaster.numberOfInstallments')}
                id="policy-master-numberOfInstallments"
                name="numberOfInstallments"
                data-cy="numberOfInstallments"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.policyMaster.penaltyRateOfInterest')}
                id="policy-master-penaltyRateOfInterest"
                name="penaltyRateOfInterest"
                data-cy="penaltyRateOfInterest"
                type="text"
              />
              <ValidatedField
                label={translate('cisApp.policyMaster.installmentDuration')}
                id="policy-master-installmentDuration"
                name="installmentDuration"
                data-cy="installmentDuration"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/policy-master" replace color="info">
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

export default PolicyMasterUpdate;
