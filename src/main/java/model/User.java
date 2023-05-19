package model;

import model.Enums.Role;

import javax.swing.*;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer {
    private int ID;
    private String name;
    private String username;
    private String password;
    private Role role;
    private Movie movie;

    public User() {
        super();
    }

    public User(int ID, String name, String username, String password, Role role) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.ID && Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        setMovie((Movie) o);
        System.out.println("Got update from Movie" + o.toString());
        JOptionPane.showMessageDialog(null, "I am the user with the username: " + this.username + " and i got the update from movie " + movie.getID() + ": " + o.toString());
    }
}
