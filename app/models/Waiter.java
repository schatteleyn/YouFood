package models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Waiter extends Model {
    
    public String name;
    @OneToMany
    public List<Table> listTables;

    public Waiter(String name, List<Table> listTables) {
        this.name = name;
        this.listTables = listTables;
    }
    
}
