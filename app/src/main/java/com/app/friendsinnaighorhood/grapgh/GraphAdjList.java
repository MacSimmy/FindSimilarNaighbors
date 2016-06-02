package com.app.friendsinnaighorhood.grapgh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apps on 31/5/16.
 */
public class GraphAdjList extends Graph {

    private Map<Integer, ArrayList<Integer>> adjListsMap;


    public GraphAdjList() {
        adjListsMap = new HashMap<Integer, ArrayList<Integer>>();
    }


    public void implementAddVertex() {
        int v = getNumVertices();
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        adjListsMap.put(v, neighbors);
    }


    public void implementAddEdge(int v, int w) {
        (adjListsMap.get(v)).add(w);
    }


    public List<Integer> getNeighbors(int v) {
        return new ArrayList<Integer>(adjListsMap.get(v));
    }


    public List<Integer> getInNeighbors(int v) {
        List<Integer> inNeighbors = new ArrayList<Integer>();
        for (int u : adjListsMap.keySet()) {
            //iterate through all edges in u's adjacency list and
            //add u to the inNeighbor list of v whenever an edge
            //with startpoint u has endpoint v.
            for (int w : adjListsMap.get(u)) {
                if (v == w) {
                    inNeighbors.add(u);
                }
            }
        }
        return inNeighbors;
    }


    public List<Integer> getDistance2(int v) {
        if (v >= getNumVertices()) {
            throw new IndexOutOfBoundsException();
        }
        List<Integer> twoHops = new ArrayList<Integer>();
        for (Integer i : adjListsMap.get(v)) {
            twoHops.addAll(adjListsMap.get(i));
        }
        return twoHops;
    }


    public String adjacencyString() {
        String s = "Adjacency list";
        s += " (size " + getNumVertices() + "+" + getNumEdges() + " integers):";

        for (int v : adjListsMap.keySet()) {
            s += "\n\t" + v + ": ";
            for (int w : adjListsMap.get(v)) {
                s += w + ", ";
            }
        }
        return s;
    }
}



