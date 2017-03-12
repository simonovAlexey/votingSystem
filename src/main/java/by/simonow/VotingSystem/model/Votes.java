package by.simonow.VotingSystem.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Votes extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    // votes assign with restaurant, to make statistic of user votes assign with user ?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "voted_date", columnDefinition = "timestamp default null")
    private LocalDateTime votedDate;

    public Votes() {
    }

    public Votes(Integer id, LocalDateTime votedDate) {
        super(id);
        this.votedDate = votedDate;
    }
}