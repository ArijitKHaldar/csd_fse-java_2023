package com.pizza.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.pizza.exception.InvalidPizzaOrderException;
import com.pizza.model.PizzaOrder;
import com.pizza.util.PizzaShop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PizzaShopTest {

	private static PizzaShop psObj;
	static PizzaOrder p1;
	static PizzaOrder p2;
	static PizzaOrder p3;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setUp() throws Exception {

		psObj = new PizzaShop();
		// Create few objects for PizzaOrder class and add to a list.
		// Set that list to the pizzaOrderList using the setPizzaOrderList method in
		// PizzaShop class
		p1 = new PizzaOrder(1,"pizza1", "sravani", "Onion", "Small", 5);
		p2 = new PizzaOrder(2,"pizza2", "sandhya", "Mushroom", "Medium", 3);
		p3 = new PizzaOrder(3,"pizza2", "keerthi", "Onion", "Large", 1);

		List<PizzaOrder> pizza = new ArrayList<>();
		pizza.add(p1);
		pizza.add(p2);
		pizza.add(p3);
		psObj.setPizzaOrderList(pizza);
	}

	// Test the validateSize method when size is small
	@Test
	public void test11ValidateSizeWhenSmall() throws InvalidPizzaOrderException {
		assertTrue(psObj.validateSize("Small"));
	}

	// Test the validateSize method when size is medium
	@Test
	public void test12ValidateSizeWhenMedium() throws InvalidPizzaOrderException {
		assertTrue(psObj.validateSize("Medium"));
	}

	// Test the validateSize method when size is large
	@Test
	public void test13ValidateSizeWhenLarge() throws InvalidPizzaOrderException {
		assertTrue(psObj.validateSize("Large"));
	}

	// Test the validateSize method when size is invalid
	@Test
	public void test14ValidateSizeWhenInvalid() throws InvalidPizzaOrderException {
		exceptionRule.expect(InvalidPizzaOrderException.class);
		assertTrue(psObj.validateSize("Big"));
	}

	// Test viewPizzaOrderById method for an existing order id.
	@Test
	public void test15ViewPizzaOrderByIdWhenValid() throws InvalidPizzaOrderException {
		assertEquals(p1, psObj.viewPizzaOrderById(1));
	}

	// Test viewPizzaOrderById method for a non existing order id.
	@Test
	public void test16ViewPizzaOrderByIdWhenInvalid() throws InvalidPizzaOrderException {
		exceptionRule.expect(InvalidPizzaOrderException.class);
		assertEquals(p1, psObj.viewPizzaOrderById(5));
	}

	// Test the correctness of viewPizzaOrdersByType method
	@Test
	public void test17ViewPizzaOrdersByType() throws InvalidPizzaOrderException {
		List<PizzaOrder> tmp = new ArrayList<>();
		tmp.add(p1);
		tmp.add(p3);

		assertEquals(tmp, psObj.viewPizzaOrdersByType("Onion"));
	}

	// Test the correctness of viewPizzaOrdersTypeWise method
	@Test
	public void test18ViewPizzaOrdersTypeWise() throws InvalidPizzaOrderException {
		Map<String, List<PizzaOrder>> p = psObj.viewPizzaOrdersTypeWise();
		int l = p.size();
		assertEquals(2, l);
	}

	// Test the correctness of countTotalQuantityForEachSize method
	@Test
	public void test19CountTotalQuantityForEachSize() throws InvalidPizzaOrderException {
		Map<String, Integer> expectedResult = new HashMap<>();
		expectedResult.put("Small", 5);
		expectedResult.put("Large", 1);
		expectedResult.put("Medium", 3);

		assertEquals(expectedResult, psObj.countTotalQuantityForEachSize());
	}

	// Test the correctness of viewPizzaOrdersByType method when the list is empty
	@Test
	public void test20ViewPizzaOrdersByTypeForEmptyList() throws InvalidPizzaOrderException {
		List<PizzaOrder> tmp = new ArrayList<>();
		assertEquals(tmp, psObj.viewPizzaOrdersByType("Oni"));
	}

	// Test the correctness of viewPizzaOrdersTypeWise method when the list is empty
	@Test
	public void test21ViewPizzaOrdersTypeWiseForEmptyList() throws InvalidPizzaOrderException {
		List<PizzaOrder> tmp = new ArrayList<>();
		psObj.viewPizzaOrdersTypeWise();
	}

	// Test the correctness of countTotalQuantityForEachSize method when the list is
	// empty
	@Test
	public void test22CountTotalQuantityForEachSizeForEmptyList() throws InvalidPizzaOrderException {
		List<PizzaOrder> tmp = new ArrayList<>();
		psObj.countTotalQuantityForEachSize();
	}
}
