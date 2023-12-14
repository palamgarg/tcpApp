import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './schdule-master.reducer';

export const SchduleMaster = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const schduleMasterList = useAppSelector(state => state.cis.schduleMaster.entities);
  const loading = useAppSelector(state => state.cis.schduleMaster.loading);

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
      <h2 id="schdule-master-heading" data-cy="SchduleMasterHeading">
        <Translate contentKey="cisApp.schduleMaster.home.title">Schdule Masters</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="cisApp.schduleMaster.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/schdule-master/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="cisApp.schduleMaster.home.createLabel">Create new Schdule Master</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {schduleMasterList && schduleMasterList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="cisApp.schduleMaster.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('installmentNumber')}>
                  <Translate contentKey="cisApp.schduleMaster.installmentNumber">Installment Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('installmentNumber')} />
                </th>
                <th className="hand" onClick={sort('reducingBalance')}>
                  <Translate contentKey="cisApp.schduleMaster.reducingBalance">Reducing Balance</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('reducingBalance')} />
                </th>
                <th className="hand" onClick={sort('principleAmount')}>
                  <Translate contentKey="cisApp.schduleMaster.principleAmount">Principle Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('principleAmount')} />
                </th>
                <th className="hand" onClick={sort('interest')}>
                  <Translate contentKey="cisApp.schduleMaster.interest">Interest</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('interest')} />
                </th>
                <th className="hand" onClick={sort('totalInstallment')}>
                  <Translate contentKey="cisApp.schduleMaster.totalInstallment">Total Installment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalInstallment')} />
                </th>
                <th className="hand" onClick={sort('dueDate')}>
                  <Translate contentKey="cisApp.schduleMaster.dueDate">Due Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dueDate')} />
                </th>
                <th className="hand" onClick={sort('remarks')}>
                  <Translate contentKey="cisApp.schduleMaster.remarks">Remarks</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('remarks')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {schduleMasterList.map((schduleMaster, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/schdule-master/${schduleMaster.id}`} color="link" size="sm">
                      {schduleMaster.id}
                    </Button>
                  </td>
                  <td>{schduleMaster.installmentNumber}</td>
                  <td>{schduleMaster.reducingBalance}</td>
                  <td>{schduleMaster.principleAmount}</td>
                  <td>{schduleMaster.interest}</td>
                  <td>{schduleMaster.totalInstallment}</td>
                  <td>{schduleMaster.dueDate}</td>
                  <td>{schduleMaster.remarks}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/schdule-master/${schduleMaster.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/schdule-master/${schduleMaster.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (location.href = `/schdule-master/${schduleMaster.id}/delete`)}
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
              <Translate contentKey="cisApp.schduleMaster.home.notFound">No Schdule Masters found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default SchduleMaster;
