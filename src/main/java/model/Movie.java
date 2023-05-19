package model;

import model.Enums.Type;

import java.util.Observable;

public class Movie extends Observable {
    private int ID;
    private String name;
    private Type type;
    private String category;
    private int year;
    private int idCreator;

    public Movie() {
        super();
    }

    public Movie(int ID, String name, Type type, String category, int year, int idCreator) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.category = category;
        this.year = year;
        this.idCreator = idCreator;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }

    public void setUpdate() {
        setChanged();
        notifyObservers(this);
        System.out.println("Set update for Movie " + ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie film = (Movie) o;
        return ID == film.ID && year == film.year && name.equals(film.name) && type == film.type && category.equals(film.category) && idCreator == film.idCreator;
    }

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", type=" + type +
                ", category=" + category +
                ", year=" + year +
                ", id_creator=" + idCreator +
                '}';
    }
}
