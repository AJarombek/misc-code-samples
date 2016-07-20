package FamilyTree;

import java.util.ArrayList;

/**
 * @author Andrew Jarombek
 * Date: 7/19/2016
 * Generate a familty tree
 */
public class FamilyTree<Person> {
    private Person person;
    private ArrayList<Person> partners;
    private ArrayList<FamilyTree<Person>> children;

    public FamilyTree(Person person) {
        this.person = person;
        partners = new ArrayList<>();
        children = new ArrayList<FamilyTree<Person>>();
    }

    public ArrayList<Person> getPartners() {
        return partners;
    }

    public void setPartners(Person... partners) {
        for (Person partner : partners)
            this.partners.add(partner);
    }

    public ArrayList<FamilyTree<Person>> getChildren() {
        return children;
    }

    public void setChildren(FamilyTree<Person>... children) {
        for (FamilyTree<Person> child : children)
            this.children.add(child);
    }

    public void setChildren(ArrayList<FamilyTree<Person>> children) {
        this.children = children;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
