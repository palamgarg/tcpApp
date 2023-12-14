import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './policy-master.reducer';

export const PolicyMaster = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const policyMasterList = useAppSelector(state => state.cis.policyMaster.entities);
  const loading = useAppSelector(state => state.cis.policyMaster.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="policy-master-heading" data-cy="PolicyMasterHeading">
        <Translate contentKey="cisApp.policyMaster.home.title">Policy Masters</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="cisApp.policyMaster.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/policy-master/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="cisApp.policyMaster.home.createLabel">Create new Policy Master</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {policyMasterList && policyMasterList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="cisApp.policyMaster.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('purpuseName')}>
                  <Translate contentKey="cisApp.policyMaster.purpuseName">Purpuse Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('purpuseName')} />
                </th>
                <th className="hand" onClick={sort('policyName')}>
                  <Translate contentKey="cisApp.policyMaster.policyName">Policy Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('policyName')} />
                </th>
                <th className="hand" onClick={sort('chargesType')}>
                  <Translate contentKey="cisApp.policyMaster.chargesType">Charges Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('chargesType')} />
                </th>
                <th className="hand" onClick={sort('interestRate')}>
                  <Translate contentKey="cisApp.policyMaster.interestRate">Interest Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('interestRate')} />
                </th>
                <th className="hand" onClick={sort('numberOfInstallments')}>
                  <Translate contentKey="cisApp.policyMaster.numberOfInstallments">Number Of Installments</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('numberOfInstallments')} />
                </th>
                <th className="hand" onClick={sort('penaltyRateOfInterest')}>
                  <Translate contentKey="cisApp.policyMaster.penaltyRateOfInterest">Penalty Rate Of Interest</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('penaltyRateOfInterest')} />
                </th>
                <th className="hand" onClick={sort('installmentDuration')}>
                  <Translate contentKey="cisApp.policyMaster.installmentDuration">Installment Duration</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('installmentDuration')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {policyMasterList.map((policyMaster, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/policy-master/${policyMaster.id}`} color="link" size="sm">
                      {policyMaster.id}
                    </Button>
                  </td>
                  <td>{policyMaster.purpuseName}</td>
                  <td>{policyMaster.policyName}</td>
                  <td>{policyMaster.chargesType}</td>
                  <td>{policyMaster.interestRate}</td>
                  <td>{policyMaster.numberOfInstallments}</td>
                  <td>{policyMaster.penaltyRateOfInterest}</td>
                  <td>{policyMaster.installmentDuration}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/policy-master/${policyMaster.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/policy-master/${policyMaster.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (location.href = `/policy-master/${policyMaster.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="cisApp.policyMaster.home.notFound">No Policy Masters found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default PolicyMaster;
