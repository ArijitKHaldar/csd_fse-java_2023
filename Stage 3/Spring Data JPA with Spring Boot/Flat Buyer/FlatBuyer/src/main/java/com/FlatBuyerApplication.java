package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bean.Buyer;
import com.bean.Flat;
import com.dao.BuyerDAO;

@SpringBootApplication
public class FlatBuyerApplication implements CommandLineRunner {

	@Autowired
	private BuyerDAO buyerDAO;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(FlatBuyerApplication.class);

		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Invoke the methods");
		Buyer bobj = new Buyer();
		bobj.setBuyerId("B1");
		bobj.setBuyerName("Rohit");
		bobj.setBuyerContactNumber("9871234568");
		buyerDAO.addBuyer(bobj);

		Flat f = new Flat();
		f.setFlatId("F1");
		f.setFlatPrice(20000);
		f.setDoorNumber(12);
		f.setFlatArea(2000);
		f.setFlatType("Single");
		f.setNumberOfRooms(6);
		buyerDAO.addFlat("B1", f);
	}
}
