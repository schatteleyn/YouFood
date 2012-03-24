package controllers;

import play.mvc.Controller;

public class Restaurant extends Controller {
    
    public static void show(Long id) {
        Restaurant restaurant = Restaurant.findById(id);
        render(restaurant);
    }
    
    public static void edit() {
    }
    
    public static void destroy() {
    }
    
}
