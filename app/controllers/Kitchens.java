package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Item;
import models.OrderClient;
import models.Restaurant;
import play.mvc.Controller;

public class Kitchens extends Controller  {
    
        public static void index(Long restaurant_id) {
            Restaurant restaurant = Restaurant.findById(restaurant_id);
            List<OrderClient> listOrders = OrderClient.find("byRestaurant_id", restaurant_id).fetch();
            List<Item> listItems = new ArrayList<Item>();
            
            for(int i=0; i<listOrders.size(); i++){
                for(int y=0; y<listOrders.get(i).listItems.size(); y++){
                    listItems.add(listOrders.get(i).listItems.get(y));
                }
            }

            render(restaurant, listItems);    
    }
}
