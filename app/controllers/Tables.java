package controllers;

import java.util.List;
import models.Restaurant;
import models.TableRest;
import play.data.validation.Required;
import play.mvc.Controller;

public class Tables extends Controller {
    
    public static void index(Long restaurant_id) {
        Restaurants.show(restaurant_id);
    }
    
    public static void create(Long restaurant_id) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        List<TableRest> tables = restaurant.listTables;
        
        TableRest table = new TableRest(restaurant);
        
        table.numTable = tables.size()+1;
        restaurant.listTables.add(table);
        
        table.save();
        restaurant.save();
        index(restaurant_id);
    }
    
    public static void editIsAvailable(@Required Long restaurant_id, @Required Long table_id) {
        TableRest table = TableRest.findById(table_id);
        if(table.isAvailable){
            table.isAvailable = false;
        }else{
            table.isAvailable = true;
        }
        
        table.save();
        Waiters.showStatusTables(restaurant_id);
    }
    
    public static void editNeedHelp(Long restaurant_id, Long table_id) {
        TableRest table = TableRest.findById(table_id);
        if(table.needHelp){
            table.needHelp = false;
        }else{
            table.needHelp = true;
        }
        
        table.save();
        Waiters.showStatusTables(restaurant_id);
    }
    
    public static void editNeedHelpClient(Long restaurant_id, Long table_id) {
        TableRest table = TableRest.findById(table_id);
        if(table.needHelp){
            table.needHelp = false;
        }else{
            table.needHelp = true;
        }
        
        table.save();
        Clients.index(restaurant_id, table_id);
    }
    
    public static void destroy(Long restaurant_id) {
        Restaurant restaurant = Restaurant.findById(restaurant_id);
        
        for(int i=0; i<restaurant.listTables.size(); i++){
            if(i == restaurant.listTables.size()-1){
                TableRest table = restaurant.listTables.get(i);
                restaurant.listTables.remove(i);
                restaurant.save();
                table.delete();
            }
        }
        
        index(restaurant_id);
    }
    
}
