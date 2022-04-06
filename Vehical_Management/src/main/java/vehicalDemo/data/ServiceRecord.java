package vehicalDemo.data;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ServiceRecord {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "reg_no")
	private SoldVehicle soldVehicle;

	private String serviceStatus;

	private Date dueDate;

	public ServiceRecord() {
		super();
	}

	public ServiceRecord(SoldVehicle soldVehicle, String serviceStatus, Date dueDate) {
		super();
		this.soldVehicle = soldVehicle;
		this.serviceStatus = serviceStatus;
		this.dueDate = dueDate;
	}

	public int getId() {
		return id;
	}


	public SoldVehicle getSoldVehicle() {
		return soldVehicle;
	}

	public void setSoldVehicle(SoldVehicle soldVehicle) {
		this.soldVehicle = soldVehicle;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

}
