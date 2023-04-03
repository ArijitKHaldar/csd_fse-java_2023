package com.pizza.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pizza.exception.InvalidPizzaOrderException;
import com.pizza.model.PizzaOrder;

public class PizzaShop {
	private List<PizzaOrder> pizzaOrderList=new ArrayList<>();

	public List<PizzaOrder> getPizzaOrderList() {
		return pizzaOrderList;
	}

	public void setPizzaOrderList(List<PizzaOrder> pizzaOrderList) {
		this.pizzaOrderList = pizzaOrderList;
	}

	public boolean validateSize(String size) throws InvalidPizzaOrderException{
		boolean flag=false;
		if(size.equalsIgnoreCase("small") || size.equalsIgnoreCase("medium") || size.equalsIgnoreCase("large")){
			flag=true;
		}
		else {
			throw new InvalidPizzaOrderException("size is invalid");
		}
		return flag;
	}
	public PizzaOrder viewPizzaOrderById(int orderId) throws InvalidPizzaOrderException{
		if(pizzaOrderList.isEmpty()){
			throw new InvalidPizzaOrderException("List is empty");
		}
		else{
			for(PizzaOrder num:pizzaOrderList){
				if(num.getOrderId()==orderId)
					return num;
			}
			throw new InvalidPizzaOrderException("Order Id is Invalid");
		}

	}
	public List<PizzaOrder> viewPizzaOrdersByType(String pizzaType) throws InvalidPizzaOrderException{
		if(pizzaOrderList.isEmpty()){
			throw new InvalidPizzaOrderException("List is empty");
		}
		else{
			List<PizzaOrder> result=new ArrayList<>();
			for(PizzaOrder num:pizzaOrderList){
				if(num.getPizzaType().equalsIgnoreCase(pizzaType))
					result.add(num);
			}
			return result;
		}
	}
	public Map<String,List<PizzaOrder>> viewPizzaOrdersTypeWise() throws InvalidPizzaOrderException{
		if(pizzaOrderList.isEmpty()){
			throw new InvalidPizzaOrderException("List is empty");
		}
		else{
			Map<String,List<PizzaOrder>> result=new HashMap<>();
			for(PizzaOrder num:pizzaOrderList){
				if(!result.containsKey(num.getPizzaType())){
					result.put(num.getPizzaType(),new ArrayList<PizzaOrder>());
				}
				List<PizzaOrder> temp=result.get(num.getPizzaType());
				temp.add(num);
				result.put(num.getPizzaType(), temp);
			}
			return result;
		}
	}
	public Map<String,Integer> countTotalQuantityForEachSize() throws InvalidPizzaOrderException{
		if(pizzaOrderList.isEmpty()){
			throw new InvalidPizzaOrderException("List is empty");
		}
		else{
			Map<String,Integer> result=new HashMap<>();
			int smallCount=0;
			int medCount=0;
			int largeCount=0;
			for(PizzaOrder num:pizzaOrderList){
				if(num.getSize().equalsIgnoreCase("small")){
					smallCount=smallCount+num.getQuantity();
				}
				else if(num.getSize().equalsIgnoreCase("medium")){
					medCount=medCount+num.getQuantity();
				}
				else if(num.getSize().equalsIgnoreCase("large")){
					largeCount=largeCount+num.getQuantity();
				}
			}
			result.put("Small", smallCount);
			result.put("Medium", medCount);
			result.put("Large", largeCount);
			return result;
		}
	}
}
