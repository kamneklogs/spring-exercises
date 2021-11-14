package co.edu.icesi.tintegracion.services.interfaces;

import co.edu.icesi.tintegracion.model.hr.Employeepayhistory;

public interface EmployeePayHistoryService {

        public Employeepayhistory save(Employeepayhistory employeepayhistory, Integer businessentityid);

        public Employeepayhistory edit(Integer employeeId, Employeepayhistory employeepayhistory,
                        Integer employeepayhistoryPk);

        public Employeepayhistory findById(Integer employeepayhistoryPk);

        public Iterable<Employeepayhistory> findAll();
}
