package controllers;

import models.Menu;
import play.mvc.Controller;

public class MenuController extends Controller {
    
    public static void index() {
        Menu.findAll();
    }
    
    public static void find(Long id){
        Menu menu = Menu.findById(id);
        render(menu);
    }
    
    public static void create(Menu menu) {
        menu.save();
        index();
    }
    
    public static void edit(Long id) {
        Menu menu = Menu.findById(id);
        //menu.edit(null,null)
        menu.save();
    }
    
    public static void destroy(Long id) {
        Menu menu = Menu.findById(id);
        menu.delete();
    }
}
