package controllers;

import java.util.ArrayList;
import java.util.List;
import models.*;
import play.data.validation.Required;
import play.mvc.Controller;

public class Clients extends Controller{
    
    public static void index(Long restaurant_id, Long table_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        
        render(restaurant, table);
    }
    
    public static void menu(@Required Long restaurant_id, @Required Long table_id) {        
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        List<Category> categories = Category.findAll();
        List<Card> listCard = Card.find("byRestaurant_id", restaurant_id).fetch();
        Card card = null;
        
        for(int i=0; i<listCard.size(); i++){
            if(listCard.get(i).table.id == table.id){
                card = listCard.get(i);
            }
        }
        
        if(card != null){
            render(restaurant, table, categories, card);
        }else{
            render(restaurant, table, categories);
        }
        
    }
    
    public static void items(@Required Long restaurant_id, @Required Long table_id, @Required Long category_id) {    
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        Category category = Category.findById(category_id);
        
        List<Item> listItemsMenu = restaurant.currentMenu.listItems;
        List<Item> listItemsMenuCategory = new ArrayList<Item>();
        
        for(int i=0; i<listItemsMenu.size(); i++){
            if(listItemsMenu.get(i).category == category){
                listItemsMenuCategory.add(listItemsMenu.get(i));
            }
        }
         
        List<Card> listCard = Card.find("byRestaurant_id", restaurant_id).fetch();
        Card card = null;
        
        for(int i=0; i<listCard.size(); i++){
            if(listCard.get(i).table.id == table.id){
                card = listCard.get(i);
            }
        }

        if(card != null){
            render(restaurant, table, category, card, listItemsMenuCategory);
        }else{
            render(restaurant, table, category, listItemsMenuCategory);
        }  
    }
    
    public static void card(@Required Long restaurant_id, @Required Long table_id) {    
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        List<Card> listCard = Card.find("byRestaurant_id", restaurant_id).fetch();
        Card card = null;
        
        for(int i=0; i<listCard.size(); i++){
            if(listCard.get(i).table.id == table_id){
                card = listCard.get(i);
            }
        }
        
        if(card != null){
           render(restaurant, table, card);
        }else{
           menu(restaurant_id, table_id);
        }
    }
      
    public static void config(@Required Long restaurant_id) {   
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<TableRest> listTables = TableRest.findAll();
        
        render(restaurant, listTables);
    }
    
    public static void saveConfig(@Required Long restaurant_id, @Required Long table_id) {        
        index(restaurant_id, table_id);
    }
        
    public static void confirmation(Long restaurant_id, Long table_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        TableRest table = TableRest.findById(table_id);
        render(restaurant, table);
    }
}
