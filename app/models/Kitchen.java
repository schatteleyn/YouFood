package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Kitchen extends Model{
    
    @ManyToOne
    public Restaurant restaurant;
    
    @OneToMany
    public List<ItemToCook> listItems;
    
    public Kitchen(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}
