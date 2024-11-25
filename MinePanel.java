 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.util.*;
 import java.io.*;
 public class MinePanel extends JPanel implements Serializable{
	
		MineFrame frame;
		mButton viewArr[][];
	
	 public MinePanel(MineFrame f){
		frame = f;
		frame.getModel().setMineField();
		setMineButtons();
		frame.getModel().setRevealed(false);
	 }
	 public void setMineButtons(){
		 
		 setLayout(new GridLayout(10,10));
		 viewArr = new mButton[10][10];
		 MouseHandler mh = new MouseHandler();
		 for(int i = 0; i<10; i++){
			 for(int j = 0; j<10;j++){
			 mButton button = new mButton(i,j, frame.getModel().getMineField()[i][j]);
			 button.addMouseListener(mh);
			 viewArr[i][j] = button;
			 button.setText("?");
			 add(button);
			 }
		 }
	 }
	 public void setMineButtons(int x, int y){
		 
		 setLayout(new GridLayout(x,y));
		 viewArr = new mButton[x][y];
		 MouseHandler mh = new MouseHandler();
		 for(int i = 0; i<x; i++){
			 for(int j = 0; j<y;j++){
			 mButton button = new mButton(i,j,frame.getModel().getMineField()[i][j]);
			 button.addMouseListener(mh);
			 viewArr[i][j] = button;
			 button.setText("?");
			 add(button);
			 }
		 }
	 }
	 public void resetButtons(){
		 removeAll();
	}
	 public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(frame.getModel().getGameOver() == true){
			frame.getModel().setRevealed(true);
		}
		
		for(int i = 0; i<viewArr.length;i++){
			for(int j = 0; j<viewArr[0].length;j++){
				if(frame.getModel().getRevealed()[i][j] == true){
					if(viewArr[i][j].getValue() > -1)viewArr[i][j].setText(String.valueOf(viewArr[i][j].getValue()));
					else viewArr[i][j].setText("M");
				}
			}
		}
		
	 }
	 
	 public mButton[][] getViewArr(){
		 return viewArr;
		 
	 }
	 
	private class MouseHandler extends MouseAdapter implements Serializable{
		MineModel model = new MineModel();
		public void mouseClicked(MouseEvent e) {
			mButton button = (mButton)e.getSource();
			if(e.getButton() == MouseEvent.BUTTON1){
				
				if (button.getValue() < 0){
					frame.getModel().gameOver();
					repaint(); 
				}
				else {
				button.setText(String.valueOf(button.getValue()));
 				frame.getModel().getRevealed()[button.getXvalue()][button.getYvalue()] = true;
				}
			
			}
			else if(e.getButton() == MouseEvent.BUTTON3){
			
			button.setText("F");
			
			}
			}	
	}

}