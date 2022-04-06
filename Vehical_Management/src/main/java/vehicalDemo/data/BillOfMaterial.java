package vehicalDemo.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BillOfMaterial {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int quantity;
	private int price;
	// (no need of mapping here, just unidirectional relationship form service
	// record is enough)
	private int serviceId;

	public BillOfMaterial() {
		super();
	}

	public BillOfMaterial(String name, int quantity, int price, int serviceId) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.serviceId = serviceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}



}

