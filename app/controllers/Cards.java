package controllers;

import java.util.ArrayList;
import models.Card;
import models.Item;
import models.Restaurant;
import models.TableRest;
import play.data.validation.Required;
import play.mvc.Controller;

public class Cards extends Controller{
    
    public static void index(Long restaurant_id, Long table_id, Long category_id) {
        Clients.items(restaurant_id, table_id, category_id);
    }
    
    public static void saveCreate(@Required Long table_id, @Required Long item_id) {
        TableRest table = TableRest.findById(table_id);
        Restaurant restaurant = table.restaurant;
        Item item = Item.findById(item_id);
        Card card = Card.find("byTable_id", table_id).first();
        if(card == null){
            card = new Card(restaurant, table);
            card.listItems = new ArrayList<Item>();   
        }
        
        card.listItems.add(item);
        
        card.save();
        index(restaurant.id, table.id, item.category.id);
    }

    public static void destroy(Long id) {
        
    }
}
