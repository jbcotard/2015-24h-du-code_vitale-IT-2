package fr.sesamvitale.l24hc2015.urbanflow.graphe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DirectedWeightedMultigraph;

import fr.sesamvitale.l24hc2015.urbanflow.data.Arret;
import fr.sesamvitale.l24hc2015.urbanflow.data.Liaison;

public class DijkstraAlgorithm {

	private final List<Arret> nodes;
	private final List<Liaison> edges;
	private Set<Arret> settledNodes;
	private Set<Arret> unSettledNodes;
	private Map<Arret, Arret> predecessors;
	private Map<Arret,Liaison> predecessorsLigne;
	private Map<Arret, Integer> distance;
	private int changementLigne = -1;
	private String lignePrecedente = "";

	public DijkstraAlgorithm(DirectedWeightedMultigraph<Arret,Liaison> graph) {
		// create a copy of the array so that we can operate on this array
		this.nodes = new ArrayList<Arret>(graph.vertexSet());
		this.edges = new ArrayList<Liaison>(graph.edgeSet());
	}

	public void execute(Arret source) {
		settledNodes = new HashSet<Arret>();
		unSettledNodes = new HashSet<Arret>();
		distance = new HashMap<Arret, Integer>();
		predecessors = new HashMap<Arret, Arret>();
		predecessorsLigne = new HashMap<Arret, Liaison>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Arret node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Arret node) {
		List<Arret> adjacentNodes = getNeighbors(node);
		for (Arret target : adjacentNodes) {
			Liaison l = null;
			int distanceInt = getDistance(node, target, l);
			
			if (getShortestDistance(target) > getShortestDistance(node)
					+ distanceInt) {
				distance.put(target, getShortestDistance(node)
						+ distanceInt);
				predecessors.put(target, node);
				predecessorsLigne.put(target, l);
				unSettledNodes.add(target);
			}
		}
	}

	private int getDistance(Arret node, Arret target, Liaison l) {
		for (Liaison edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getTarget().equals(target)) {
				l = edge ;
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Arret> getNeighbors(Arret node) {
		List<Arret> neighbors = new ArrayList<Arret>();
		for (Liaison edge : edges) {
			if (edge.getSource().equals(node)
					&& !isSettled(edge.getTarget())) {
				neighbors.add(edge.getTarget());
			}
		}
		return neighbors;
	}

	private Arret getMinimum(Set<Arret> vertexes) {
		Arret minimum = null;
		for (Arret vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Arret vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(Arret destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Arret> getPath(Arret target) {
		LinkedList<Arret> path = new LinkedList<Arret>();
		Arret step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}
	
	public Liaison getNextArret(Arret target) {
		LinkedList<Arret> path = new LinkedList<Arret>();
		Arret step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		Arret suivant = path.get(1);
		return predecessorsLigne.get(suivant);
	}

} 

