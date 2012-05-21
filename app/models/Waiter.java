package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Waiter extends Model {
    
    public String firstName;
    public String lastName;
    
    //password
    //admin
    
    @ManyToOne
    public Restaurant restaurant;
        
    @OneToMany
    public List<Table> listTables;

    public Waiter(String firstName, String lastName, Restaurant restaurant, List<Table> listTables) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.restaurant = restaurant;
        this.listTables = listTables;
    }
    
}
