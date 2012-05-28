package controllers;

import models.Card;
import models.Item;
import models.Restaurant;
import play.data.validation.Required;
import play.mvc.Controller;

public class Cards extends Controller{
    
    public static void index(Card card, Long category_id) {
        Clients.items(card.restaurant.id, category_id);
    }
    
    public static void saveCreate(@Required Long restaurant_id, @Required Long item_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Card card = new Card(restaurant);
        Item item = Item.findById(item_id);
        card.listItems.add(item);
        
        card.save();
        index(card , item.category.id);
    }
 
    public static void saveEdit(@Required Long id, @Required Long item_id) {
        Card card = Card.findById(id);
        Item item = Item.findById(item_id);
        card.listItems.add(item);
        
        card.save();
        index(card, item.category.id);
    }
    
    public static void destroy(Long id) {
        
    }
}
