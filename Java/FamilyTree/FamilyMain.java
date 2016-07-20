package FamilyTree;

/**
 * @author  Andrew Jarombek
 * Date: 7/19/2016
 * Main method for the family tree program
 */
public class FamilyMain {
    public static void main(String[] args) {
        Person andrew = new Person("Andrew", "Jarombek", "2/26/1995", true);
        Person jerry = new Person("Jerry", "Jarombek", "7/17/1950", true);
        Person kathy = new Person("Kathy", "Jarombek", "1/10/1957", true);
        jerry.setPartners(kathy);
        kathy.setPartners(jerry);
        andrew.setParents(kathy, jerry);
        System.out.println(andrew.toString());
    }
}
