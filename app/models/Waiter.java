package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Waiter extends Model {
    
    public String firstName;
    public String lastName;
    
    @ManyToOne
    public Restaurant restaurant;

    public Waiter(String firstName, String lastName, Restaurant restaurant) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.restaurant = restaurant;
    }
    
}
