package by.simonow.VotingSystem.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantTo extends BaseTo {

    private String name;

    private Integer votes;


    public RestaurantTo(@JsonProperty("id") Integer id,
                        @JsonProperty("name") String name,
                        @JsonProperty("votes") Integer votes) {
        super(id);
        this.name = name;
        this.votes = votes;
    }

    public RestaurantTo(Integer id, String name, Long votes) {
        super(id);
        this.name = name;
        this.votes = votes.intValue();
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}
