package controllers;

import java.util.Date;
import models.Item;
import models.Kitchen;
import models.Restaurant;
import play.mvc.Controller;

public class Kitchens extends Controller {

    public static void index(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Kitchen kitchen = restaurant.kitchen;        

        render(restaurant, kitchen);
    }

    public static void removeItem(Long restaurant_id, Long item_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Kitchen kitchen = restaurant.kitchen;
        Item item = Item.findById(item_id);

        for(int i=0; i<kitchen.listItems.size(); i++){
            if(kitchen.listItems.get(i).item.id == item.id){
                kitchen.listItems.get(i).finishCookDate = new Date();
                kitchen.listItems.get(i).save();
            }
        }

        index(restaurant.id);
    }
}
