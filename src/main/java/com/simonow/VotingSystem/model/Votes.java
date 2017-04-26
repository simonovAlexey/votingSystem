package com.simonow.VotingSystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "votes")
public class Votes extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    // vote assign with restaurant, to make statistic of user vote assign with user ?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(name = "voted_date", columnDefinition = "timestamp default null")
    private LocalDateTime votedDate;

    public Votes(Integer id, LocalDateTime votedDate) {
        this(id, null, null, votedDate);
    }

    public Votes(LocalDateTime votedDate, Restaurant restaurant, User user) {
        this(null, restaurant, user, votedDate);
    }

    public Votes(Integer id, Restaurant restaurant, User user, LocalDateTime votedDate) {
        super(id);
        this.restaurant = restaurant;
        this.user = user;
        this.votedDate = votedDate;
    }

    @Override
    public String toString() {
        return "Votes{" +
                "id=" + getId() +
                ", votedDate=" + votedDate.toLocalDate() +
                '}';
    }
}
