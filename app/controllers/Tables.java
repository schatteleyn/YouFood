package controllers;

import models.Table;
import play.mvc.Controller;

public class Tables extends Controller {
    
    public static void index() {
        Table.findAll();
    }
    
    public static void find(Long id){
        Table table = Table.findById(id);
        render(table);
    }
    
    public static void create() {
        render();
    }
    
    public static void saveCreate() {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
            index();
        }
        Table table = new Table();
        table.save();
        index();
    }

    
    public static void destroy(Long id) {
        Table table = Table.findById(id);
        table.delete();
        index();
    }
    
}
