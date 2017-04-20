package by.simonow.VotingSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@NamedEntityGraphs({@NamedEntityGraph(name = Restaurant.GRAPH_WITH_DISHES, attributeNodes = {@NamedAttributeNode("dishes")}),
        @NamedEntityGraph(name = Restaurant.GRAPH_WITH_VOTES, attributeNodes = {@NamedAttributeNode("votes")})})
public class Restaurant extends NamedEntity {

    public static final String GRAPH_WITH_DISHES = "Restaurant.withDishes";
    public static final String GRAPH_WITH_VOTES = "Restaurant.withVotes";


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("inMenu DESC")
//    @JsonIgnore
    @JsonManagedReference
    protected List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
//    @JsonIgnore
    protected Set<Votes> votes;

    public Restaurant() {
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, List<Dish> dishes) {
        super(id, name);
        this.dishes = dishes;
    }

    public Restaurant(String name) {
        super(null, name);
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
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
                "id=" + getId() +
                ", name=" + name +
                '}';
    }
}
