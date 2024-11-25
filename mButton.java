 import javax.swing.*;
 import java.awt.*;
 import java.util.*;
 import java.io.*;
 public class mButton extends JButton implements Serializable{
		private int xValue;
		private int yValue;
		private int value;
	mButton(int x, int y, int val){
		xValue = x;
		yValue = y;
		value = val;

	}
	public int getXvalue(){
		return xValue;
	}
	public int getYvalue(){
		return yValue;
	}
	public int getValue(){
		return value;
	}
	
	
 }