package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;

/**
 * Created by Алексей on 08.01.2017.
 */
@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Restaurant extends NamedEntity{

    @Column(name = "votes", columnDefinition = "int default 100")
    @Digits(fraction = 0, integer = 5)
    private int votes;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(null, name);
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                ", votes=" + votes +
                '}';
    }
}
