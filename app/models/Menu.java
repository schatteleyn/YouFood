package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Menu extends Model {
    
    public String name;
    public Date creationDate;
    
    @OneToMany
    public Item listItems;

    public Menu(String name, Item listItems, Date creationDate) {
        this.name = name;
        this.listItems = listItems;
        this.creationDate = new Date();
    }
}
