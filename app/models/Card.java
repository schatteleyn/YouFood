package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Card extends Model{
    
    public Float totalPrice = Float.parseFloat("0");
    
    @ManyToOne
    public Restaurant restaurant;
    
    @ManyToOne
    public TableRest table;
    
    @OneToMany
    public List<Item> listItems;

    public Card(Restaurant restaurant, TableRest table){
        this.restaurant = restaurant;
        this.table = table;
    }
}
