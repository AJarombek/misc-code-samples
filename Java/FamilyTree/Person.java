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

    @Override
    public String toString() {
        String partnerString = "[";
        if (partners != null) {
            for (int i = 0; i < partners.length; i++) {
                if (i < 0)
                    partnerString += ", ";
                partnerString += partners[i].getFirstName() + " " + partners[i].getLastName();
            }
        }
        partnerString += "]";

        String parentString = "[";
        if (parents != null) {
            parentString += parents[0].getFirstName() + " " + parents[0].getLastName() + ", "
                    + parents[1].getFirstName() + " " + parents[1].getLastName();
        }
        parentString += "]";

        String childrenString = "[";
        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                if (i < 0)
                    childrenString += ", ";
                childrenString += children[i].getFirstName() + " " + children[i].getLastName();
            }
        }
        childrenString += "]";

        String status = (alive) ? "Living" : "Deceased";
        return "[Name:" + getFirstName() + " " + getLastName() + ", Born:" + getBorn() + ", Died:" + getDied() +
                ", Status:" + status + ", Parents:" + parentString + ", Partners:" + partnerString +
                ", Children:" + childrenString + "]";
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
