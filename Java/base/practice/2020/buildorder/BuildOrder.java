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
 * @since 1/16/2020
 */

public class BuildOrder {

    /**
     * Static inner class representing a tree of projects in their build order hierarchy.
     */
    static class BuildOrderTree {
        Map<Character, BuildOrderTree> nodes;

        BuildOrderTree() {
            nodes = new HashMap<>();
        }

        /**
         * Find the nested build order tree for a certain project.
         * @param item The character identifier for a project.
         * @return A projects {@code BuildOrderTree} object.
         */
        BuildOrderTree findBuildOrderTree(Character item) {
            return findBuildOrderTree(item, this);
        }

        /**
         * Helper class for finding the build order tree of a project.  This is a recursive function.
         * @param item The character identifier for a project.
         * @param tree The current nested BuildOrderTree being viewed.
         * @return A projects {@code BuildOrderTree} object.
         */
        BuildOrderTree findBuildOrderTree(Character item, BuildOrderTree tree) {
            if (tree.nodes != null && !tree.nodes.isEmpty()) {
                for (Map.Entry<Character, BuildOrderTree> node : tree.nodes.entrySet()) {
                    if (node.getKey().equals(item)) {
                        return tree;
                    }

                    if (!node.getValue().nodes.isEmpty()) {
                        BuildOrderTree result = findBuildOrderTree(item, node.getValue());

                        if (result != null) {
                            return result;
                        }
                    }
                }
            }

            return null;
        }
    }

    /**
     * Determine the order of projects based on their dependencies.
     * @param projects A list of projects represented by a unique character.
     * @param dependencies A list of dependencies which are key value pairs.
     * @return An ordered list of projects based on the build order.
     */
    static List<Character> construct(List<Character> projects, List<Map.Entry<Character, Character>> dependencies) {
        BuildOrderTree result = new BuildOrderTree();

        for (Character project : projects) {
            result.nodes.put(project, new BuildOrderTree());
        }

        for (Map.Entry<Character, Character> dependency : dependencies) {
            Character first = dependency.getKey();
            Character second = dependency.getValue();

            BuildOrderTree firstBuildOrderTree = result.findBuildOrderTree(first);
            BuildOrderTree secondBuildOrderTree = result.findBuildOrderTree(second);

            if (firstBuildOrderTree != null) {
                if (secondBuildOrderTree != null) {
                    // Both the dependency and the dependent project already exist in the build order tree.
                    BuildOrderTree secondTree = new BuildOrderTree();
                    secondTree.nodes.put(second, secondBuildOrderTree.nodes.remove(second));
                    firstBuildOrderTree.nodes.get(first).nodes.putAll(secondTree.nodes);
                } else {
                    // The dependency project exists in the build order tree but the dependent project does not.
                    firstBuildOrderTree.nodes.put(second, new BuildOrderTree());
                }
            } else {
                if (secondBuildOrderTree != null) {
                    // The dependency project does not exist in the build order tree but the dependent project does.
                    BuildOrderTree secondTree = secondBuildOrderTree.nodes.remove(second);
                    secondBuildOrderTree.nodes.put(first, secondTree);
                } else {
                    // Neither the dependency or the dependent project exist in the build order tree.
                    BuildOrderTree secondTree = new BuildOrderTree();
                    secondTree.nodes.put(second, new BuildOrderTree());
                    result.nodes.put(first, secondTree);
                }
            }
        }

        List<Character> buildOrder = new ArrayList<>();
        constructBuildOrder(result, buildOrder);
        return buildOrder;
    }

    /**
     * Construct a build order list from a build order tree.
     * @param result The build order tree.
     * @param buildOrder The resulting build order list.
     */
    private static void constructBuildOrder(BuildOrderTree result, List<Character> buildOrder) {
        if (result != null && !result.nodes.isEmpty()) {
            for (Map.Entry<Character, BuildOrderTree> node : result.nodes.entrySet()) {
                buildOrder.add(node.getKey());
            }

            for (Map.Entry<Character, BuildOrderTree> node : result.nodes.entrySet()) {
                constructBuildOrder(node.getValue(), buildOrder);
            }
        }
    }
}
