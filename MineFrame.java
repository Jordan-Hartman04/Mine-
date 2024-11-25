import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class MineFrame  extends JFrame implements Serializable{
	
	private MineModel model;
	private MinePanel panel1;
	private JMenuBar menuB;
	private JMenu menuF;
	
	public MineFrame(){
	model = new MineModel();
	panel1 = new MinePanel(this);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Mine Sweeper!");
		add(panel1);
		
		menuB = new JMenuBar();
		menuF = new JMenu("File");
		MouseHandler action = new MouseHandler();
		
		JMenuItem nu = new JMenuItem("New");
				nu.addActionListener(action);
		JMenuItem save = new JMenuItem("Save");
				save.addActionListener(action);
		JMenuItem load = new JMenuItem("Load");
				load.addActionListener(action);
		JMenuItem quit = new JMenuItem("Quit");
				quit.addActionListener(action);

		menuF.add(nu);
		menuF.add(save);
		menuF.add(load);
		menuF.add(quit);
		
		menuB.add(menuF);
		
		setJMenuBar(menuB);
	
		setVisible(true);	
		
	}
	public MineModel getModel(){
		return model;
	}
	public MinePanel getPanel(){
		return panel1;
	}
	
	private class MouseHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem pressed = (JMenuItem)e.getSource();
			
			if(pressed.getText().equals("Quit")){
				System.exit(0);
			}else if(pressed.getText().equals("New")){
				DifficultyLevel(MineFrame.this);
				}
				else if(pressed.getText().equals("Save")){
					SaveGame();
				}else if(pressed.getText().equals("Load")){
					LoadGame();
				}
			 }
			
	public void DifficultyLevel(MineFrame f) {
        JDialog dialog = new JDialog(f, "Difficulty Level", true);

        JPanel panel = new JPanel();

        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");

        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
			dialog.dispose();
            f.getPanel().resetButtons();
			f.getModel().resetMines();
			f.getModel().setMineField(10, 10, 15); 
			f.getPanel().setMineButtons(10,10);
			f.setSize(f.getWidth()-1,f.getHeight()-1);
			f.repaint();
			f.getPanel().repaint();
		    f.setSize(500,500);
            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
			f.getPanel().resetButtons();
			f.getModel().resetMines();
			f.getModel().setMineField(15, 15, 20); 
			f.getPanel().setMineButtons(15,15);
            f.getPanel().repaint(); 
			f.setSize( 700,700);
            dialog.dispose();
            }
        });
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
			f.getPanel().resetButtons();
			f.getModel().resetMines();
            f.getModel().setMineField(20, 20, 30); 
			f.getPanel().setMineButtons(20,20);
            f.getPanel().repaint(); 
			f.setSize(900,900);
            dialog.dispose();
            }
        });

        panel.add(easy);
        panel.add(medium);
        panel.add(hard);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(f);
        dialog.setVisible(true);
    }
	
	}
	
		public void SaveGame(){
			JFileChooser directory = new JFileChooser();
			
			int result = directory.showSaveDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION){
			try{
			File selectedFile = directory.getSelectedFile();
			String fileName = selectedFile.getAbsolutePath();
		
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			
			objectOut.writeObject(model);
			objectOut.close();
			fileOut.close();
			}
			catch(IOException e){
			e.printStackTrace();
		}
			}
			}
	
	public void LoadGame(){
				JFileChooser directory = new JFileChooser();
	
				int result = directory.showSaveDialog(this);
					
				if(result == JFileChooser.APPROVE_OPTION){
			   
				File selectedFile = directory.getSelectedFile();
				String fileName = selectedFile.getAbsolutePath();
				MineModel loadedModel = loadModel(fileName);
				model = loadedModel;
				panel1.removeAll();
				validate();
				panel1.setMineButtons(model.getNumRows(),model.getNumCols());
				repaint();
			
				}
		}
		
	public MineModel loadModel(String file){
			try{
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			MineModel saveModel = (MineModel)objectIn.readObject();
			objectIn.close();
			fileIn.close();
			return saveModel;
			}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
			return null;
			}
	}
	
	
	
}