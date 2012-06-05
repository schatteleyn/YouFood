package controllers;

import java.util.List;
import models.Category;
import models.Item;
import play.data.validation.Required;
import play.mvc.Controller;

public class Categories extends Controller {

    public static void index() {
        List<Category> categories = Category.findAll();
        render(categories);
    }
    
    public static void show(Long category_id) {
        Category category = Category.findById(category_id);
        List<Item> items = Item.find("byCategory_id", category_id).fetch();
        render(category, items);
    }

    public static void saveCreate(@Required String name, @Required String description) {
        if (validation.hasErrors()) {
            validation.keep();
            params.flash();
            flash.error("Please correct these errors !");
        }
        Category category = new Category(name, description);
        category.save();
        index();
    }
    
    public static void edit(Long category_id) {
        Category category = Category.findById(category_id);
        render(category);
    }

    public static void saveEdit(@Required Long category_id, @Required String name, @Required String description) {
        Category category = Category.findById(category_id);
        category.name = name;
        category.description = description;
        validation.valid(category);
        if(validation.hasErrors()) {
            flash.error("Please correct these errors !");
        } else {
            flash.success("The item has been updated !");
            category.save();
        }
        index();
    }
    
    public static void destroy(Long category_id) {
        Category category = Category.findById(category_id);
        category.delete();
        index();
    }
}
