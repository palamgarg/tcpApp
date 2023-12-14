import region from 'app/entities/region/region.reducer';
import policyMaster from 'app/entities/policy-master/policy-master.reducer';
import schduleMaster from 'app/entities/schdule-master/schdule-master.reducer';
import country from 'app/entities/country/country.reducer';
import location from 'app/entities/location/location.reducer';
import department from 'app/entities/department/department.reducer';
import task from 'app/entities/task/task.reducer';
import employee from 'app/entities/employee/employee.reducer';
import job from 'app/entities/job/job.reducer';
import jobHistory from 'app/entities/job-history/job-history.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  region,
  policyMaster,
  schduleMaster,
  country,
  location,
  department,
  task,
  employee,
  job,
  jobHistory,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
