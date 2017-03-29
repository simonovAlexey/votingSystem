package by.simonow.VotingSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    public static final String M_DELETE = "Meal.delete";
    public static final String M_GET_MENU = "Meal.getMenuList";
    public static final String M_GET = "Meal.getWithVotes";
    public static final String M_ALL = "Meal.getAllMeals";

    @Column(name = "inmenu", columnDefinition = "boolean default false")
    private boolean inMenu;

    @Column(name = "description", nullable = false)
    @NotBlank
    @SafeHtml
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 100000)
    @NotNull
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(boolean inMenu, String description, int price) {
        this(null, inMenu, description, price);
    }

    public Meal(Integer id, boolean inMenu, String description, int price) {
        super(id);
        this.inMenu = inMenu;
        this.description = description;
        this.price = price;
    }

    public boolean isInMenu() {
        return inMenu;
    }

    public void setInMenu(boolean inMenu) {
        this.inMenu = inMenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + getId() +
                ", inMenu=" + inMenu +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
