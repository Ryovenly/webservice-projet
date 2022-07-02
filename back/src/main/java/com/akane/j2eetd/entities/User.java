package com.akane.j2eetd.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Column(name = "username") @Id @NotEmpty
    String username;
    @Column
   // @JsonIgnore
    private String password;
    @Column
    private String role;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "charactergame_pseudo")
    @JsonBackReference
    private Charactergame charactergame;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Charactergame getCharactergame() {
        return charactergame;
    }

    public void setCharacter(Charactergame charactergame) {
        this.charactergame = charactergame;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
