package vehicalDemo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sold_vehicle")
public class SoldVehicle {

	@Id
	private int regNo;

	@OneToMany(mappedBy = "soldVehicle")
	List<ServiceRecord> serviceRecords = new ArrayList<ServiceRecord>();

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public SoldVehicle(int regNo, Vehicle vehicle, Customer customer) {
		super();
		this.regNo = regNo;
		this.vehicle = vehicle;
		this.customer = customer;
	}

	public SoldVehicle() {
		super();
	}

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ServiceRecord> getServiceRecords() {
		return serviceRecords;
	}

	public void setServiceRecords(List<ServiceRecord> serviceRecords) {
		this.serviceRecords = serviceRecords;
	}

}

