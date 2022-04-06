package vehicalDemo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import vehicalDemo.data.Customer;
import vehicalDemo.data.CustomerRepository;
import vehicalDemo.data.SoldVehicle;
import vehicalDemo.data.VehicleRepository;


@SpringBootApplication
@ComponentScan("prime.servo")
public class ServoApplication implements CommandLineRunner {

	@Autowired
	CustomerRepository cr;

	@Autowired
	VehicleRepository vr;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(ServoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Customer c1 = cr.findById(100).get();

		if (c1 == null) {
			throw new RuntimeException("BRUH--------");
		}

		List<SoldVehicle> soldVehicles = c1.getSoldVehicles();

		for (SoldVehicle sv : soldVehicles) {

			logger.info("reg_no: {}, vehicle_id: {}, customer_id: {}", sv.getRegNo(), sv.getVehicle().getId(),
					sv.getCustomer().getId());
			;
		}

	}

}
