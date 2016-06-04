package fr.masciulli.gsontest;

public class Bird implements Animal {
    public int wings;

    @Override
    public String toString() {
        return "Bird with " + wings + " wings";
    }
}
