package models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.db.jpa.Model;

@Entity
public class Waiter extends Model {
    
    @OneToMany
    public Table listTables;

    public Waiter(Table listTables) {
        this.listTables = listTables;
    }
    
}
