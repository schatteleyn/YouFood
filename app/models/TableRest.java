package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class TableRest extends Model {
    
    public int numTable;
    public boolean isAvailable = true;
    public boolean needHelp = false;
    
    @ManyToOne
    public Restaurant restaurant;
    
    @OneToMany
    public List<OrderClient> listOrders;

    public TableRest(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}
