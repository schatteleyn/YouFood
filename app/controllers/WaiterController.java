package controllers;

import java.util.List;
import models.Item;
import models.Table;
import models.Waiter;
import play.data.validation.Required;
import play.mvc.Controller;

public class WaiterController extends Controller {

    public static void index() {
        Waiter.findAll();
    }
    
    public static void find(Long id){
        Waiter waiter = Waiter.findById(id);
        render(waiter);
    }
    
    public static void create(@Required String name, List<Table> tables) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        Waiter waiter = new Waiter(name, tables);
        waiter.save();
        index();
    }
    
    public static void edit(Long id) {
        Waiter waiter = Waiter.findById(id);
        render(waiter);
    }
    public static void saveEdit(@Required Long id, @Required String name) {
        Waiter waiter = Waiter.findById(id);
        waiter.name = name;
        validation.valid(waiter);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          waiter.save();
        }
        index();
    }
    
    public static void destroy(Long id) {
        Waiter waiter = Waiter.findById(id);
        waiter.delete();
        index();
    }
}   