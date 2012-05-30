package controllers;

import java.util.List;
import models.Item;
import models.Kitchen;
import models.OrderClient;
import models.Restaurant;
import play.mvc.Controller;

public class Kitchens extends Controller  {
    
    public static void index(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<OrderClient> listOrders = OrderClient.find("byRestaurant_id", restaurant_id).fetch();
        
        Kitchen kitchen = null;
        
        if(restaurant.kitchen == null){
            kitchen = new Kitchen(restaurant);
            
            restaurant.kitchen = kitchen;

            kitchen.save();
            restaurant.save();
        }else{
            kitchen = restaurant.kitchen;
        }
        
        kitchen.listItemsToDo.clear();
        
        
        for(int i=0; i<listOrders.size(); i++){
            
            if(listOrders.get(i).inProgress == true){
                
                for(int y=0; y<listOrders.get(i).listItems.size(); y++){
                    
                    if(!kitchen.listItemsDone.isEmpty()){
                        
                        System.out.println("listItemsDone not null");
                        
                        for(int x=0; x<kitchen.listItemsDone.size(); x++){
                            if(listOrders.get(i).listItems.get(y).id != kitchen.listItemsDone.get(x).id){
                                kitchen.listItemsToDo.add(listOrders.get(i).listItems.get(y));
                                System.out.println(listOrders.get(i).listItems.get(y).name);
                            }
                        }
                    }else{
                        kitchen.listItemsToDo.add(listOrders.get(i).listItems.get(y));
                        System.out.println(listOrders.get(i).listItems.get(y).name);
                    }
                }
            }
        }
        
        kitchen.save();
        
        render(restaurant, kitchen);    
    }
    
    public static void removeItem(Long restaurant_id, Long item_id){
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Kitchen kitchen = restaurant.kitchen;
        Item item = Item.findById(item_id);
        
        kitchen.listItemsDone.add(item);

        kitchen.save();
        index(restaurant.id);
    }
}
