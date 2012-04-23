package controllers;

import models.Waiter;
import play.mvc.Controller;

public class WaiterController extends Controller {

    public static void index() {
        Waiter.findAll();
    }
    
    public static void find(Long id){
        Waiter waiter = Waiter.findById(id);
        render(waiter);
    }
    
    public static void create(Waiter waiter) {
        waiter.save();
        index();
    }
    
    public static void edit(Long id) {
        Waiter waiter = Waiter.findById(id);
        waiter.edit("waiter", params.all());
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