package vehicalDemo.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "vehicle")
public class Vehicle {
	
	@Id
	@GeneratedValue
	private int id;
	private String brand;
	private String model;
	private int price;
	
	@OneToMany(mappedBy = "vehicle")
	private List<SoldVehicle> soldVehicles = new ArrayList<>();

	public Vehicle() {
		super();
	}

	public Vehicle(String brand, String model, int price) {
		super();
		this.brand = brand;
		this.model = model;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
