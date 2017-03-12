package by.simonow.VotingSystem.to;

import com.fasterxml.jackson.annotation.JsonProperty;


public class RestaurantWithVotes extends BaseTo {

    private final String name;

    private final int votes;

    public RestaurantWithVotes(@JsonProperty("id") Integer id,
                               @JsonProperty("name") String name,
                               @JsonProperty("votes") int votes) {
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
        return "RestaurantWithVotes{" +
                "name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }
}
