package by.simonow.VotingSystem.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Алексей on 08.01.2017.
 */
@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@NamedEntityGraph(name = Restaurant.GRAPH_WITH_MEALS, attributeNodes = {@NamedAttributeNode("meals")})
public class Restaurant extends NamedEntity{

    public static final String GRAPH_WITH_MEALS = "Restaurant.withMeals";

    @Column(name = "votes", columnDefinition = "int default 0")
    @Range(min = 0)
    private int votes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("description DESC")
//    @JsonIgnore
    protected List<Meal> meals;

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
