package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Restaurant extends Model {
    
    public String address;
    public String city;
    public String country;
    
    @ManyToOne
    public Menu currentMenu;
    
    public Restaurant(String address, String city, String country, Menu currentMenu) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.currentMenu = currentMenu;
    }
}
