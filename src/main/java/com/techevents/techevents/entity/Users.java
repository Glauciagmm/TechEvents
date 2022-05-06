package com.techevents.techevents.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.net.PasswordAuthentication;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    public String surname;
    @NotEmpty
    private String phone;
    @NotEmpty
    @Email
    private String email;

    private String passwordAuthentication;
    private Boolean admin;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "class",
            joinColumns = @JoinColumn(name= "id_users"),
            inverseJoinColumns = @JoinColumn (name="id_events"))

    private Set<Events> events = new HashSet<>() ;

    public Users() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                ", events=" + events +
                '}';
    }

    public void setPassword(Object o) {
    }

    public String getPassword() {
    }
}
