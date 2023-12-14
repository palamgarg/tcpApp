import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PolicyMaster from './policy-master';
import PolicyMasterDetail from './policy-master-detail';
import PolicyMasterUpdate from './policy-master-update';
import PolicyMasterDeleteDialog from './policy-master-delete-dialog';

const PolicyMasterRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PolicyMaster />} />
    <Route path="new" element={<PolicyMasterUpdate />} />
    <Route path=":id">
      <Route index element={<PolicyMasterDetail />} />
      <Route path="edit" element={<PolicyMasterUpdate />} />
      <Route path="delete" element={<PolicyMasterDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PolicyMasterRoutes;
