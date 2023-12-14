import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SchduleMaster from './schdule-master';
import SchduleMasterDetail from './schdule-master-detail';
import SchduleMasterUpdate from './schdule-master-update';
import SchduleMasterDeleteDialog from './schdule-master-delete-dialog';

const SchduleMasterRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SchduleMaster />} />
    <Route path="new" element={<SchduleMasterUpdate />} />
    <Route path=":id">
      <Route index element={<SchduleMasterDetail />} />
      <Route path="edit" element={<SchduleMasterUpdate />} />
      <Route path="delete" element={<SchduleMasterDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SchduleMasterRoutes;
