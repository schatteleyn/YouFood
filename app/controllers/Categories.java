package controllers;

import models.Category;
import play.data.validation.Required;
import play.mvc.Controller;

public class Categories extends Controller {

    public static void index() {
        render();
    }
    
    public static void create() {
        render();
    }

    public static void saveCreate(@Required String name) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        Category category = new Category(name);
        category.save();
        index();
    }
    
    public static void edit(Long id) {
        Category category = Category.findById(id);
        render(category);
    }

    public static void saveEdit(@Required Long id, @Required String name) {
        Category category = Category.findById(id);
        category.name = name;
        validation.valid(category);
        if(validation.hasErrors()) {
            flash.error("Please correct these errors !");
        } else {
            flash.success("The item has been updated !");
            category.save();
        }
        index();
    }
    
    public static void destroy(Long id) {
        Category category = Category.findById(id);
        category.delete();
        index();
    }
}