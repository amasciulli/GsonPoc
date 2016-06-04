package fr.masciulli.gsontest;

public class Dog implements Animal {
    public int paws;

    @Override
    public String toString() {
        return "Dog with " + paws + " paws";
    }
}
