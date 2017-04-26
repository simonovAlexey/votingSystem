package com.simonow.VotingSystem.to;

import com.simonow.VotingSystem.model.Votes;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @SafeHtml
    private String name;

    @Email
    @NotBlank
    @SafeHtml
    private String email;

    @Size(min = 5, max = 64, message = " must between 5 and 64 characters")
    @SafeHtml
    private String password;

    private Votes todayVote;


    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        this(id,name,email,password,null);
    }

    public UserTo(Integer id, String name, String email, String password, Votes todayVote) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.todayVote = todayVote;
    }

    public Votes getTodayVote() {
        return todayVote;
    }

    public void setTodayVote(Votes todayVote) {
        this.todayVote = todayVote;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
