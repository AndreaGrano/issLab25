package main.java.model;

import main.java.model.SearchAlgorithm.AStarPathfinding;
import unibo.planner23.Planner23Util;

public class PlannerForHold {
	Planner23Util planner;
	AStarPathfinding finder;
	
	public PlannerForHold() {
		super();
		
		planner = new Planner23Util();
		planner.initAI();
	}
}
