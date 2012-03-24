package models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Table extends Model {
    
    public boolean isAvailable;
    public boolean needHelp;
    //public Area area;
    @OneToMany
    public Order order;

    public Table(boolean isAvailable, boolean needHelp, Order order) {
        this.isAvailable = isAvailable;
        this.needHelp = needHelp;
        this.order = order;
    }
    
    
}
