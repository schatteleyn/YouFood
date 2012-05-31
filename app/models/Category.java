package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Category extends Model{
    
    public String name;
    
    public String description;
    
    @OneToMany
    public List<Item> listItems;
    
    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }
}
