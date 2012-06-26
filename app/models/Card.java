package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class Card extends Model{
    
    public Float totalPriceHT = Float.parseFloat("0");
    
    @ManyToOne
    public Restaurant restaurant;
    
    @ManyToOne
    public TableRest table;
    
    @ManyToMany
    public List<Item> listItems;

    public Card(Restaurant restaurant, TableRest table){
        this.restaurant = restaurant;
        this.table = table;
    }
}
