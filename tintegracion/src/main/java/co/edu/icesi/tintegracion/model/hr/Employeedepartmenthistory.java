package co.edu.icesi.tintegracion.model.hr;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the employeedepartmenthistory database table.
 *
 */

@Entity
@NamedQuery(name = "Employeedepartmenthistory.findAll", query = "SELECT e FROM Employeedepartmenthistory e")
public class Employeedepartmenthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer businessentityid;

	@Column(insertable = false, updatable = false)
	private Integer departmentid;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startdate;

	@NotNull(message = "Debe ser despues de la fecha de inicio")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enddate;

	private Timestamp modifieddate;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public Integer getBusinessentityid() {
		return businessentityid;
	}

	public void setBusinessentityid(Integer businessentityid) {
		this.businessentityid = businessentityid;
	}

	public Integer getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Integer departmentid) {
		this.departmentid = departmentid;
	}

	public java.util.Date getStartdate() {
		return startdate;
	}

	public void setStartdate(java.util.Date startdate) {
		this.startdate = startdate;
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