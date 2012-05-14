package models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Menu extends Model {
    
    public String name;
    public Date creationDate = new Date();
    
    @OneToMany
    public List<Item> listItems;

    public Menu(String name, List<Item> listItems) {
        this.name = name;
        this.listItems = listItems;
    }
}
