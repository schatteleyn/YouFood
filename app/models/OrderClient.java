package models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class OrderClient extends Model {
    
    public Date date;
    public Float totalPrice;
   
    @OneToMany
    public List<Item> listItems;
    
    @ManyToOne
    public TableRest table;
    
    @ManyToOne
    public Restaurant restaurant;
    
    public OrderClient(Restaurant restaurant, TableRest table, List<Item> listItems){
        this.restaurant = restaurant;
        this.table = table;   
        this.listItems = listItems;
    }
    
}
