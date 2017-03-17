package by.simonow.VotingSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Алексей on 08.01.2017.
 */
@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@NamedEntityGraph(name = Restaurant.GRAPH_WITH_MEALS, attributeNodes = {@NamedAttributeNode("meals")})
public class Restaurant extends NamedEntity {

    public static final String GRAPH_WITH_MEALS = "Restaurant.withMeals";
//    public static final String GRAPH_WITH_VOTES = "Restaurant.withVotes";


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("inMenu DESC")
//    @JsonIgnore
//    @JsonBackReference
    @JsonManagedReference
    protected List<Meal> meals;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @JsonIgnore
    protected Set<Votes> votes;

    public Restaurant() {
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, List<Meal> meals) {
        super(id, name);
        this.meals = meals;
    }

    public Restaurant(String name) {
        super(null, name);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Set<Votes> getVotes() {
        return votes;
    }

    public void setVotes(Set<Votes> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
