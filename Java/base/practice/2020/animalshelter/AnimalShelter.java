package animalshelter;

import java.util.LinkedList;

/**
 * Cracking the Coding Interview: Question 3.6
 * An animal shelter, which holds only dogs and cats, operates on a strictly "first in, first out" basis.  People must
 * adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they
 * would prefer a dog or a cat (and will receive the oldest animal of that type).  They cannot select which specific
 * animal they would like.  Create a data structure to maintain this system and implement operations such as enqueue,
 * dequeueAny, dequeueDog, and dequeueCat.  You may use the built-in LinkedList data structure.
 * @author Andrew Jarombek
 * @since 1/6/2020
 */

class AnimalShelter {

    private LinkedList<Animal> animals;

    AnimalShelter() {
        animals = new LinkedList<>();
    }

    /**
     * Enqueue an animal to the end of the queue.
     * @param animal Animal (Cat or Dog) to add to the end of the queue.
     */
    public void enqueue(Animal animal) {
        animals.addLast(animal);
    }

    /**
     * Remove an animal from the start of the queue.
     * @return The animal removed from the queue.
     */
    public Animal dequeueAny() {
        return animals.removeFirst();
    }

    /**
     * Remove a cat from the start of the queue.
     * @return The cat removed from the queue.
     */
    public Cat dequeueCat() {
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                animals.remove(animal);
                return (Cat) animal;
            }
        }

        return null;
    }

    /**
     * Remove a dog from the start of the queue.
     * @return The dog removed from the queue.
     */
    public Dog dequeueDog() {
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                animals.remove(animal);
                return (Dog) animal;
            }
        }

        return null;
    }

    /**
     * Inner static class representing an animal (a cat or a dog) with a name and unique identifier.
     */
    public static class Animal {
        private int id;
        private String name;

        public Animal(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * A dog with a name and unique identifier.
     */
    public static class Dog extends Animal {

        public Dog(int id, String name) {
            super(id, name);
        }
    }

    /**
     * A cat with a name and unique identifier.
     */
    public static class Cat extends Animal {

        public Cat(int id, String name) {
            super(id, name);
        }
    }
}
