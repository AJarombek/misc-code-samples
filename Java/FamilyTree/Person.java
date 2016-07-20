package FamilyTree;

/**
 * Author: Andrew Jarombek
 * Date: 7/19/2016
 * A Person object that can be put into a family tree
 */
public class Person {

    private String firstName;
    private String lastName;
    private String born;
    private boolean alive;
    private String died;
    private Person[] partners;
    private Person[] children;
    private Person[] parents;

    public Person(String firstName, String lastName, String born, boolean alive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.born = born;
        this.alive = alive;
    }

    public Person(String firstName, String lastName, String born, boolean alive, String died,
                  Person[] partners, Person[] children, Person[] parents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.born = born;
        this.alive = alive;
        this.died = died;
        this.partners = partners;
        this.children = children;
        this.parents = parents;
    }

    @Override
    public String toString() {
        // Create a formatted substring for the persons partners
        String partnerString = "[";
        if (partners != null) {
            for (int i = 0; i < partners.length; i++) {
                if (i < 0)
                    partnerString += ", ";
                partnerString += partners[i].getFirstName() + " " + partners[i].getLastName();
            }
        }
        partnerString += "]";

        // Create a formatted substring for the persons parents
        String parentString = "[";
        if (parents != null) {
            parentString += parents[0].getFirstName() + " " + parents[0].getLastName() + ", "
                    + parents[1].getFirstName() + " " + parents[1].getLastName();
        }
        parentString += "]";

        // Create a formatted substring for the persons children
        String childrenString = "[";
        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                if (i > 0)
                    childrenString += ", ";
                childrenString += children[i].getFirstName() + " " + children[i].getLastName();
            }
        }
        childrenString += "]";

        // Add a status to the string that tells if they are living
        String status = (alive) ? "Living" : "Deceased";
        return "[Name:" + getFirstName() + " " + getLastName() + ", Born:" + getBorn() + ", Died:" + getDied() +
                ", Status:" + status + ", Parents:" + parentString + ", Partners:" + partnerString +
                ", Children:" + childrenString + "]";
    }

    /**
     * Create a shorter string of the Person object
     * @return String representation of the person
     */
    public String shortToString() {
        String status = (alive) ? "Living" : "Deceased";
        String personString = "[Name:" + getFirstName() + " " + getLastName() + ", Born:" + getBorn();
        if (!alive) personString += ", Died:" + getDied();
        personString += ", Status:" + status + "]";
        return personString;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getDied() {
        return died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public Person[] getPartners() {
        return partners;
    }

    public void setPartners(Person... partners) {
        this.partners = partners;
    }

    public Person[] getParents() {
        return parents;
    }

    public void setParents(Person mother, Person father) {
        this.parents = new Person[]{mother, father};
    }

    public Person[] getChildren() {
        return children;
    }

    public void setChildren(Person... children) {
        this.children = children;
    }
}
