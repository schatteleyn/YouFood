package controllers;

import java.util.ArrayList;
import java.util.Date;
import models.*;
import play.mvc.Controller;

public class Orders extends Controller {
    
    public static void saveCreate(Long restaurant_id, Long table_id, Long card_id) {      
        Card card = Card.findById(card_id);
        TableRest table = TableRest.findById(table_id);
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        
        OrderClient order = new OrderClient(restaurant, table);
        order.date = new Date();
        order.totalPrice = card.totalPrice;
        order.listItems = new ArrayList<Item>();
        
        
        for(int i=0; i<card.listItems.size(); i++){
            order.listItems.add(card.listItems.get(i));
        }
        
        order.save();
        Cards.destroy(restaurant_id, table_id);
    }
    
    public static void orderComplete(Long restaurant_id, Long order_id) {
        OrderClient order = OrderClient.findById(order_id);
        order.inProgress = false;
        order.save();
        
        Waiters.showCurrentOrders(restaurant_id);
    }
    
    public static void destroy(Long order_id) {
        OrderClient order = OrderClient.findById(order_id);
        order.delete();
    }
    
}
