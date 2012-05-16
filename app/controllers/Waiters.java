package controllers;

import java.util.List;
import models.Table;
import models.Waiter;
import play.data.validation.Required;
import play.mvc.Controller;

public class Waiters extends Controller {

    public static void index() {
        Waiter.findAll();
    }
    
    public static void find(Long id){
        Waiter waiter = Waiter.findById(id);
        render(waiter);
    }
    
    public static void create() {
        render();
    }
    
    public static void saveCreate(@Required String name, List<Table> tables) {
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
          flash.success("The waiter has been updated !");
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