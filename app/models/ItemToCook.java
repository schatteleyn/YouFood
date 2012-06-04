package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

@Entity
public class ItemToCook extends Model{
    
    public Date creationDate;
    public Date finishCookDate;

    @ManyToOne
    public Kitchen kitchen;
        
    @ManyToOne
    public OrderClient order;
    
    @ManyToOne
    public Item item;

    public ItemToCook(Kitchen kitchen, OrderClient order, Item item, Date creationDate){
        this.kitchen = kitchen;
        this.order = order;
        this.item = item;
        this.creationDate = creationDate;
    }
}
