package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Table extends Model {
    
    public boolean isAvailable; //default => false
    public boolean needHelp; //default => false
    
    @OneToMany
    public List<Order> order;

    public Table() {
    }
    
    
}
