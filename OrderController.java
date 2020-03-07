package com.flip.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flip.entity.Order;
import com.flip.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/list")
	public String listOrders(Model theModel) {
		
		List<Order> theOrders = orderService.getOrders();
		
		theModel.addAttribute("orders", theOrders);
		//there will be a jsp page where all the orders will be displayed
		return "list-orders";
	}
	
	@GetMapping("/shorFormToOrder")
	public String shorFormToOrder(Order theOrder,Model theModel) {
		
		
		theModel.addAttribute("order", theOrder);
		theModel.addAttribute("item", theOrder.getItem());  
		theModel.addAttribute("item", theOrder.getEmail());  
		theModel.addAttribute("item", theOrder.getQuantity());  
		 
		//there will be a jsp page where  item, no. of items & email id will be enter to palce a order
		return "order-form";
	}
	
	@PostMapping("/saveOrder")
	public String saveOrder(@ModelAttribute("order") Order theOrder){
		
		orderService.saveOrder(theOrder);
		
		return "redirect:/order/list";
		
	}	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("orderId") int theId,
									Model theModel)
	{
		Order theOrder = orderService.getOrder(theId);	
		
		theModel.addAttribute("order", theOrder);
		// Here the same order form will be opened will pre-populated details of the order
		return "order-form";
	}
	
	@GetMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("orderId") int theId)
	{
		orderService.deleteOrder(theId);		
		
		return "redirect:/order/list";
	}
	
	
	
	// {itemList} will be used in the jsp page to show as a dropdown menu
	@ModelAttribute("countryList")
	   public List<String> getCountryList() {
           List<String> itemList = new ArrayList<String>();
           itemList.add("Phone");
           itemList.add("Laptop");
           itemList.add("Gloves");
           itemList.add("Bottle");
           itemList.add("Notebook");
           
           
	      return itemList;
	   }
	
	
}
