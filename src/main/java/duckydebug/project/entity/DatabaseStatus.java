package duckydebug.project.entity;

import javax.persistence.*;

/**
 * This entity is used to check if databas is new. If the table has no entities, the database is new
 */
@Entity
@Table(name="database_status")
public class DatabaseStatus {
    private int id;

    public DatabaseStatus() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
