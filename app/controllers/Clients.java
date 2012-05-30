package controllers;

import java.util.ArrayList;
import java.util.List;
import models.*;
import play.mvc.Controller;

public class Clients extends Controller{
    
    public static void index(Long restaurant_id, Long table_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        render(restaurant, table);
    }
    
    public static void menu(Long restaurant_id, Long table_id) {        
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        List<Category> categories = Category.findAll();

        Card card = Card.find("byTable_id", table_id).first();
        
        render(restaurant, table, categories, card);
    }
    
    public static void items(Long restaurant_id, Long table_id, Long category_id) {    
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        Category category = Category.findById(category_id);
        List<Item> listItemsMenu = restaurant.currentMenu.listItems;
        List<Item> listItemsMenuCategory = new ArrayList<Item>();
        
        for(int i=0; i<listItemsMenu.size(); i++){
            if(listItemsMenu.get(i).category == category){
                listItemsMenuCategory.add(listItemsMenu.get(i));
            }
        }
        
        Card card = Card.find("byTable_id", table_id).first();
        
        render(table_id, category, listItemsMenuCategory, card);
    }
      
    public static void config(Long restaurant_id) {   
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<TableRest> listTables = TableRest.findAll();
        
        render(restaurant, listTables);
    }
    
    public static void saveConfig(Long restaurant_id, Long table_id) {        
        index(restaurant_id, table_id);
    }
}
