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
    
    public static void createItem(@Required Long restaurant_id, @Required Long table_id, @Required Long item_id) {
        TableRest table = TableRest.findById(table_id);
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Item item = Item.findById(item_id);

        Card card = Card.find("byTable_id", table_id).first();
        
        if(card == null){
            card = new Card(restaurant, table);
            card.listItems = new ArrayList<Item>();   
        }

        card.listItems.add(item);

        card.totalPrice = card.totalPrice+item.price;
        
        item.save();
        card.save();
        index(restaurant_id, table_id, item.category.id);
    }
    
    public static void deleteItem(@Required Long restaurant_id, @Required Long table_id, @Required Long item_id) {
        TableRest table = TableRest.findById(table_id);
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Item item = Item.findById(item_id);
        
        Card card = Card.find("byTable_id", table_id).first();
        
        card.listItems.remove(item);
        
        card.totalPrice = card.totalPrice-item.price;
        
        item.save();
        
        if(!card.listItems.isEmpty()){
            card.save();
        }else{
            card.delete();
        }

        Clients.menu(restaurant_id, table.id);
    }
    
    public static void clear(@Required Long restaurant_id, @Required Long table_id, @Required Long card_id) {
        Card card = Card.findById(card_id);
        
        card.listItems.clear();
        
        card.delete();
        
        Clients.index(restaurant_id, table_id);
    }

    public static void destroy(@Required Long restaurant_id, @Required Long table_id) {
        Card card = Card.find("byTable_id", table_id).first();
        
        card.listItems.clear();
        
        card.delete();
        
        Clients.confirmation(restaurant_id, table_id);
    }
}
