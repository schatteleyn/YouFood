package controllers;

import java.util.List;
import models.Category;
import models.Item;
import models.Menu;
import models.Restaurant;
import play.mvc.Controller;

public class Clients extends Controller{
    
    public static void index(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        render(restaurant);
    }
    
    public static void menu(Long restaurant_id) {        
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<Category> categories = Category.findAll();
        Menu menu = restaurant.currentMenu;
        //Ne prendre que les categories présent dans le Current Menu
        render(restaurant, menu, categories);
    }
    
    public static void items(Long restaurant_id, Long category_id) {    
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<Item> items = Item.find("byCategory_id", category_id).fetch();
        //Ne prendre que les items présent dans la category du Current Menu
        render(restaurant, items);
    }
}
