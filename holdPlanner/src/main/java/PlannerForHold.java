package main.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import main.java.model.Cell;
import main.java.model.HoldMap;
import main.java.model.Exceptions.CellNotFoundException;
import main.java.model.Parser.HoldMapParser;
import main.java.model.SearchAlgorithm.AStarPathfinding;
import main.java.model.SearchAlgorithm.Node;
import unibo.basicomm23.utils.CommUtils;
import unibo.planner23.model.Functions;
import unibo.planner23.model.RobotAction;
import unibo.planner23.model.RobotState;
import unibo.planner23.model.RobotState.Direction;

public class PlannerForHold {
	RobotState robotState;
	HoldMap holdMap;
	int[][] holdGridMap;
	
	public PlannerForHold() {
		super();
	}
	
	public void initRobotState() {
		robotState = new RobotState(0, 0, Direction.DOWN);
	}
	
	public void loadMap(String fileName) throws ClassNotFoundException, IOException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		
		holdMap = (HoldMap) ois.readObject();
		holdGridMap = HoldMapParser.toObstacleGrid(holdMap);
		
		ois.close();
	}
	
	public String findPath(int x, int y) {
		Node start = new Node(robotState.getX(), robotState.getY());
		Node target = new Node(x, y);
		
		List<Node> nodeList = AStarPathfinding.findPath(holdGridMap, start, target);
		String path = AStarPathfinding.FromPathToMoves(nodeList, start, target, robotState.getDirection());

		return path;
	}
	
	public void moveRobotInTheMap() {
        try {
        	int x = robotState.getX();
        	int y = robotState.getY();
			Cell currCell = holdMap.getCell(x, y);
			currCell.setRobot(true);
			holdMap.putCell(x, y, currCell);
		} catch (CellNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void doMove(char move) {
        Integer x   = robotState.getX();
        Integer y   = robotState.getY();
        
        try {
			Cell currCell = holdMap.getCell(x, y);
			currCell.setRobot(false);
			holdMap.putCell(x, y, currCell);
		} catch (CellNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
        try {
            switch (move) {
                case 'w' : {
                    robotState = (RobotState) new Functions().result(robotState, RobotAction.wAction) ;
                    moveRobotInTheMap();
                    return;
                }
                case 's': {
                    robotState = (RobotState) new Functions().result(robotState, RobotAction.sAction) ;
                    moveRobotInTheMap();
                    return;
                }
                case 'a'  : {
                    robotState = (RobotState) new Functions().result(robotState, RobotAction.lAction);
                    moveRobotInTheMap();
                    return;
                }
                case 'l' : {
                    robotState = (RobotState) new Functions().result(robotState, RobotAction.lAction) ;                    
                    moveRobotInTheMap();
                    return;
                }
                case 'd' : {
                    robotState = (RobotState) new Functions().result(robotState, RobotAction.rAction) ;
                    moveRobotInTheMap();
                    return;
                }
                case 'r' : {
                    robotState = (RobotState) new Functions().result(robotState, RobotAction.rAction) ;
                    moveRobotInTheMap();
                    return;
                }
            }//when
        } catch (Exception e ) {
            CommUtils.outred("Planner23Util doMove:" + move + " ERROR:" + e.getMessage());
        }
    }
	
	public void doPath(String path) {
		char[] moves = path.toCharArray();
		
		for(char move : moves) {
			this.doMove(move);
		}
	}
}
