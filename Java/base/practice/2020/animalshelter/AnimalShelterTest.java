package animalshelter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * Unit Testing: Cracking the Coding Interview: Question 3.6
 * @author Andrew Jarombek
 * @since 1/6/2020
 */

public class AnimalShelterTest {

    @Test
    void testEmptyShelter() {
        AnimalShelter animalShelter = new AnimalShelter();
        assertThrows(NoSuchElementException.class, animalShelter::dequeueAny);
        assertNull(animalShelter.dequeueCat());
        assertNull(animalShelter.dequeueDog());
    }

    @Test
    void testPopulatedShelter() {
        AnimalShelter animalShelter = new AnimalShelter();

        // Animals in the shelter
        AnimalShelter.Dog lily = new AnimalShelter.Dog(1, "Lily");
        AnimalShelter.Cat bella = new AnimalShelter.Cat(2, "Bella");
        AnimalShelter.Cat doug = new AnimalShelter.Cat(3, "Doug");
        AnimalShelter.Dog riley = new AnimalShelter.Dog(4, "Riley");
        AnimalShelter.Cat freddie = new AnimalShelter.Cat(5, "Freddie");

        animalShelter.enqueue(lily);
        animalShelter.enqueue(bella);
        animalShelter.enqueue(doug);
        animalShelter.enqueue(riley);
        animalShelter.enqueue(freddie);

        assertEquals(lily, animalShelter.dequeueAny());
        assertEquals(riley, animalShelter.dequeueDog());
        assertNull(animalShelter.dequeueDog());

        assertEquals(bella, animalShelter.dequeueCat());
        assertEquals(doug, animalShelter.dequeueAny());
        assertEquals(freddie, animalShelter.dequeueCat());
        assertThrows(NoSuchElementException.class, animalShelter::dequeueAny);
        assertNull(animalShelter.dequeueCat());
    }
}
