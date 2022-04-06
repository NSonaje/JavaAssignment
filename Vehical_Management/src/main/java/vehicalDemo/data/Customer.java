package vehicalDemo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String location;

	@OneToMany(mappedBy = "customer")
	private List<SoldVehicle> soldVehicles = new ArrayList<>();

	public Customer() {
		super();
	}

	public Customer(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<SoldVehicle> getSoldVehicles() {
		return soldVehicles;
	}

	public void addSoldVehicle(SoldVehicle soldVehicle) {
		this.soldVehicles.add(soldVehicle);
	}

	public void removeSoldVehicle(SoldVehicle soldVehicle) {
		this.soldVehicles.remove(soldVehicle);
	}

}

