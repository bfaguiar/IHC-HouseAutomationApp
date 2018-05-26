package com.ihc.project.HouseAutomation;

import java.util.Objects;

public class Division {

    String name;
    String type;
    int image;

    public Division(String name, String type, int image)
    {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public int getImage()
    {
        return image;
    }

    public int getID() {
        return hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return image == division.image &&
                Objects.equals(name, division.name) &&
                Objects.equals(type, division.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, type, image);
    }
}
