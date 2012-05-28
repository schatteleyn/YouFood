package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Card extends Model{
    
    public Restaurant restaurant;
    
    @OneToMany
    public List<Item> listItems;

    public Card(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}
