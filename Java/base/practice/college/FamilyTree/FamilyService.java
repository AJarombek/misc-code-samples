package FamilyTree;

import java.util.ArrayList;

/**
 * @author Andrew Jarombek
 * Date: 7/20/2016
 * Service Class to Print out a family tree and create a family tree
 */
public class FamilyService {

    /**
     * Takes a person and generates a family tree for them
     * @param person a Person object
     * @return a FamilyTree object for that person
     */
    public static FamilyTree<Person> generateFamilyTree(Person person) {
        FamilyTree<Person> mainFamilyTree = new FamilyTree<Person>(person);
        return generateFamilyTree(mainFamilyTree);
    }

    /**
     * Helper function for generateFamilyTree - takes a family tree and builds upon that family tree
     * @param personTree a PersonTree object
     * @return this persons family tree
     */
    private static FamilyTree<Person> generateFamilyTree(FamilyTree<Person> personTree) {

        // If the person has partners add them to their tree
        Person[] partners = personTree.getPerson().getPartners();
        if (partners != null)
            personTree.setPartners(partners);

        // If the person has children add them to their tree
        ArrayList<FamilyTree<Person>> childrenList = new ArrayList<FamilyTree<Person>>();
        Person[] children = personTree.getPerson().getChildren();
        if (children != null) {
            for (Person child : children) {
                // Each child should be its own tree
                FamilyTree<Person> childTree = new FamilyTree<Person>(child);
                childrenList.add(childTree);
                generateFamilyTree(childTree);
            }
        }
        personTree.setChildren(childrenList);
        return personTree;
    }

    /**
     * Print a formatted family tree
     * @param personFamilyTree The family tree to be printed
     */
    public static void printFamilyTree(FamilyTree<Person> personFamilyTree) {
        printFamilyTree(personFamilyTree, 0, "");
    }

    /**
     * Helper function for the printFamilyTree public method
     * @param personFamilyTree The family tree to be printed
     * @param level What generation of the family tree we are on
     * @param levelIndent A string to indent this level of the family tree for printing
     */
    private static void printFamilyTree(FamilyTree<Person> personFamilyTree, int level, String levelIndent) {

        // Print out this person
        Person person = personFamilyTree.getPerson();
        System.out.println(levelIndent + person.shortToString());

        ArrayList<FamilyTree<Person>> childTrees = personFamilyTree.getChildren();
        // If this person has partners print them out with their respective children nested under them
        if (person.getPartners() != null) {
            String newLevelIndent = levelIndent + "\t";
            for (Person partner : person.getPartners()) {
                System.out.println(levelIndent + "+" + partner.shortToString());
                if (childTrees != null) {
                    printChildren(childTrees, person, partner, level+1, newLevelIndent);
                }
            }
        }
    }

    /**
     * Helper function for the printFamilyTree public method that prints out the children in a family tree
     * @param childTrees an array list of all the child trees for this person
     * @param parent1 Person object for one parent
     * @param parent2 Person object for another parent
     * @param level What generation of the family tree we are on
     * @param levelIndent A string to indent this level of the family tree for printing
     */
    private static void printChildren(ArrayList<FamilyTree<Person>> childTrees, Person parent1, Person parent2,
                                     int level, String levelIndent) {
        for (FamilyTree<Person> childTree : childTrees) {
            Person child = childTree.getPerson();
            Person[] parents = child.getParents();
            // If this child is from these two parents, print out that childs family tree nested under their parents
            if (parents[0].equals(parent1) || parents[0].equals(parent2) &&
                    parents[1].equals(parent1) || parents[1].equals(parent2)) {
                printFamilyTree(childTree, level, levelIndent);
            }
        }
    }
}
