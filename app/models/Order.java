package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Order extends Model {
    
    public Date date;
    public Float totalPrice;
    
    @ManyToOne
    public Table table;

    @OneToMany
    public Item listItems;
    
    public Order(Item listItems, Date date, Float totalPrice, Table table) {
        this.listItems = listItems;
        this.date = new Date();
        this.totalPrice = totalPrice;
        this.table = table;
    }
    
}
