package by.simonow.VotingSystem.to;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RestaurantTo extends BaseTo {

    private final String name;

    private final Integer votes;


    public RestaurantTo(@JsonProperty("id") Integer id,
                        @JsonProperty("name") String name,
                        @JsonProperty("votes") Integer votes) {
        super(id);
        this.name = name;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}
