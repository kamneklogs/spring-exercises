package co.edu.icesi.tintegracion.model.hr;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the employeedepartmenthistory database table.
 *
 */
@Entity
@NamedQuery(name = "Employeedepartmenthistory.findAll", query = "SELECT e FROM Employeedepartmenthistory e")
public class Employeedepartmenthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "Employeedepartmenthistory_BUSINESSENTITYID_GENERATOR", allocationSize = 1, sequenceName = "Employeedepartmenthistory_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employeedepartmenthistory_BUSINESSENTITYID_GENERATOR")
	private Integer businessentityid;

	@Column(insertable = false, updatable = false)
	private Integer departmentid;

	@Temporal(TemporalType.DATE)
	private java.util.Date startdate;
	
	@Temporal(TemporalType.DATE)
	private Date enddate;

	private Timestamp modifieddate;

	// bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name = "departmentid", insertable = false, updatable = false)
	private Department department;

	// bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Employee employee;

	// bi-directional many-to-one association to Shift
	@ManyToOne
	@JoinColumn(name = "shiftid", insertable = false, updatable = false)
	private Shift shift;

	public Employeedepartmenthistory() {
	}

	public Department getDepartment() {
		return this.department;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Shift getShift() {
		return this.shift;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

}