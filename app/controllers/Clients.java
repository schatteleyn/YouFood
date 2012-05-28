package controllers;

import java.util.List;
import models.Card;
import models.Category;
import models.Item;
import models.Restaurant;
import play.data.validation.Required;
import play.mvc.Controller;

public class Clients extends Controller{
    
    public static void index(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        render(restaurant);
    }
    
    public static void menu(Long restaurant_id) {        
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<Category> categories = Category.findAll();
        //Menu menu = restaurant.currentMenu;
        //Ne prendre que les categories présent dans le Current Menu
        render(restaurant, categories);
    }
    
    public static void items(Long restaurant_id, Long category_id) {    
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Category category = Category.findById(category_id);
        List<Item> listItemsMenu = restaurant.currentMenu.listItems;
        List<Item> listItemsMenuCategory = category.listItems;
        listItemsMenuCategory.clear();
        
        for(int i=0; i<listItemsMenu.size(); i++){
            if(listItemsMenu.get(i).category == category){
                listItemsMenuCategory.add(listItemsMenu.get(i));
            }
        }
        
        render(restaurant, category, listItemsMenuCategory);
    }
}
