package models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class OrderClient extends Model {
    
    public Date date;
    
    public Float totalPriceHT;
    
    public Boolean inProgress = true;
          
    @ManyToMany
    public List<Item> listItems;
    
    @ManyToOne
    public TableRest table;
    
    @ManyToOne
    public Restaurant restaurant;
    
    public OrderClient(Restaurant restaurant, TableRest table){
        this.restaurant = restaurant;
        this.table = table;   
    }
    
}
