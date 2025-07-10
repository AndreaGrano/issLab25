package main.java.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class HoldMapLoader {
	
	public HoldMapLoader() {
		super();
	}
	
	public HoldMap loadHoldMap(String fileName) throws IOException, ClassNotFoundException {		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		
		HoldMap holdMap = (HoldMap) ois.readObject();
		
		ois.close();
		
		return holdMap;
	}
	
	public void saveHoldMap(HoldMap holdMap, String fileName) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + ".bin"));
		PrintWriter pw = new PrintWriter(new FileWriter(fileName + ".txt"));
		
		oos.writeObject(holdMap);
		pw.print(holdMap.toString());
		
		oos.close();
		pw.close();
	}
}
