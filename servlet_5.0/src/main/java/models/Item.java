package models;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;


    @Column(name = "DATECREATED")
    private Date dateCreated;

    @Column(name = "LASTUPDATEDDATE")
    private Date lastUpdatedDate;

    @Column(name = "DESCRIPTION")
    private String description;


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", description='" + description + '\'' +
                '}';
    }
}
