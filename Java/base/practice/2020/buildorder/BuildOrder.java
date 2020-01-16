package buildorder;

import java.util.*;

/**
 * Cracking the Coding Interview: Question 4.7
 * You are given a list of projects and a list of dependencies (which is a list of pairs of projects, where the second
 * project is dependent on the first project).  All of a project's dependencies must be built before the project is.
 * Find a build order that will allow the projects to be built.  If there is no valid build order, return an error.
 *
 * Example:
 * Input:
 *  projects: a, b, c, d, e, f
 *  dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
 *
 * Output: f, e, a, b, d, c
 *
 * @author Andrew Jarombek
 * @since 1/15/2020
 */

public class BuildOrder {

    static class BuildOrderTree<T> {
        Map<Character, BuildOrderTree<T>> nodes;

        BuildOrderTree() {
            nodes = new HashMap<>();
        }

        BuildOrderTree<T> findBuildOrderTree(T item) {
            // TODO
        }
    }

    static List<Character> construct(List<Character> projects, List<Map.Entry<Character, Character>> dependencies) {
        BuildOrderTree<Character> result = new BuildOrderTree<>();

        for (Map.Entry<Character, Character> dependency : dependencies) {
            Character first = dependency.getKey();
            Character second = dependency.getKey();

            BuildOrderTree<Character> firstBuildOrderTree = result.findBuildOrderTree(first);
            BuildOrderTree<Character> secondBuildOrderTree = result.findBuildOrderTree(second);

            if (firstBuildOrderTree != null) {
                if (secondBuildOrderTree != null) {
                    // Both the dependency and the dependent project already exist in the build order tree.
                    BuildOrderTree<Character> secondTree = secondBuildOrderTree.nodes.remove(second);
                    firstBuildOrderTree.nodes.get(first).nodes.putAll(secondTree.nodes);
                } else {
                    // The dependency project exists in the build order tree but the dependent project does not.
                    // TODO
                }
            } else {
                if (secondBuildOrderTree != null) {
                    // The dependency project does not exist in the build order tree but the dependent project does.
                    // TODO
                } else {
                    // Neither the dependency or the dependent project exist in the build order tree.
                    // TODO
                }
            }
        }
    }
}
