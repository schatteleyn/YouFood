package controllers;

import models.Table;
import play.mvc.Controller;

public class TableController extends Controller {
    
    public static void index() {
        Table.findAll();
    }
    
    public static void find(Long id){
        Table table = Table.findById(id);
        render(table);
    }
    
    public static void create(Table table) {
        table.save();
        index();
    }
    
    public static void edit(Long id) {
        Table table = Table.findById(id);
        table.edit("table", params.all());
        validation.valid(table);
        if(validation.hasErrors()) {
          // Message errors to test in views
        } else {
          table.save();
        }
        index();
    }
    
    public static void destroy(Long id) {
        Table table = Table.findById(id);
        table.delete();
        index();
    }
    
}
