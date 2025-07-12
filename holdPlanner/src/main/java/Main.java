package main.java;

import java.io.IOException;
import java.util.Arrays;

import main.java.Exceptions.CellNotFoundException;
import main.java.HoldMap.CellType;
import main.java.HoldMap.HoldMap;
import main.java.HoldMap.HoldMapLoader;

public class Main {

	public static void main(String[] args) {
		try {
			HoldMapLoader.fromTextToBinFile("./HoldMap.txt");
			
			PlannerForHold planner = new PlannerForHold();
			planner.initRobotState();
			
			HoldMap holdMap = planner.loadMap("HoldMap.bin");
			System.out.println(holdMap.toString());
			
			int[] robotCoords = planner.getRobotCoords();
			System.out.println("Initialized robot coords: " + Arrays.toString(robotCoords));
			
			int[] homeCoords = planner.getCellCoordsByType(CellType.HOME);
			System.out.println("HOME coords: " + Arrays.toString(homeCoords));
			
			int[] ioportCoords = planner.getCellCoordsByType(CellType.IOPORT);
			System.out.println("IOPORT coords: " + Arrays.toString(ioportCoords));
			
			int[] slot3Coords = planner.getCellCoordsByType(CellType.SLOT3);
			System.out.println("SLOT3 coords: " + Arrays.toString(slot3Coords));
			
			String path = planner.findPath(5, 1);
			System.out.println(path);
			planner.doPath(path);
			
			robotCoords = planner.getRobotCoords();
			System.out.println("Current robot coords: " + Arrays.toString(robotCoords));
			
			path = planner.findPath(0, 0);
			System.out.println(path);
			planner.doPath(path);
			
			robotCoords = planner.getRobotCoords();
			System.out.println("Current robot coords: " + Arrays.toString(robotCoords));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (CellNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}

}
