package model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
       /* @NamedQuery(name = Meal.M_DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.M_GET, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.M_ALL_BETWEEN, query = "SELECT m FROM Meal m WHERE m.user.id=?1 and m.dateTime BETWEEN ?2 AND ?3 ORDER BY m.dateTime DESC "),
        @NamedQuery(name = Meal.M_ALL, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),*/
})
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    public static final String M_DELETE = "Meal.delete";
    public static final String M_ALL_BETWEEN = "Meal.getMenu";
    public static final String M_GET = "Meal.get";
    public static final String M_ALL = "Meal.getAll";

    @Column(name = "in_menu", columnDefinition = "boolean default false")
    private boolean isInMenu;

    @NotEmpty
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", columnDefinition = "int default 100")
    @Digits(fraction = 0, integer = 5)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(boolean isInMenu, String description, int price) {
        this(null, isInMenu, description, price);
    }

    public Meal(Integer id, boolean isInMenu, String description, int price) {
        super(id);
        this.isInMenu = isInMenu;
        this.description = description;
        this.price = price;
    }

    public boolean isInMenu() {
        return isInMenu;
    }

    public void setInMenu(boolean inMenu) {
        isInMenu = inMenu;
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
                "id=" + id +
                ", isInMenu=" + isInMenu +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant +
                '}';
    }
}
