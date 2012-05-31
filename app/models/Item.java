package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Item extends Model {
 
    public String name;
    public Float price;

    public String description;

    public int quantity;

    @ManyToOne
    public Category category;

    public Item(String name, Float price, String description, Category category){
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = 1;
    }
}
