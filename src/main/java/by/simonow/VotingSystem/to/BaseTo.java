package by.simonow.VotingSystem.to;


import by.simonow.VotingSystem.HasId;

import javax.persistence.Id;

abstract public class BaseTo implements HasId {
    @Id
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
