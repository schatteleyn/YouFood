package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Table extends Model {
    
    public boolean isAvailable;
    public boolean needHelp;
    
    @OneToMany
    public List<Order> order;

    public Table(boolean isAvailable, boolean needHelp, List<Order> order) {
        this.isAvailable = isAvailable;
        this.needHelp = needHelp;
        this.order = order;
    }
    
    
}
