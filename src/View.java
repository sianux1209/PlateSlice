import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

class View extends JFrame {

	Cursor cursor;
	ArrayList<Plate> plate_view;
	final static int sizeup = 7;
	static int number = 1;
	Dimension screenSize;
	
	View(Cursor cursor){
		this.cursor = cursor;
		plate_view = new ArrayList<>();
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
	}
	
	public void drawPlate(int time){
		
		Dimension frameSize = new Dimension(cursor.backPlateWidth * sizeup, cursor.getMaxHeight() * sizeup);
		try{
		
		super.setSize(frameSize);
		super.setLocation((screenSize.width - frameSize.width)/2, 200);
		super.setTitle("Test Case " + Main.t + " (후판의 높이: " + cursor.getMaxHeight() + " )");
		super.setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		revalidate();
		repaint();
		
		Thread.sleep(time);
		
		}
		catch(InterruptedException eeee){}
		
	}
	public void drawPlate(){
		

		Dimension frameSize = new Dimension(cursor.backPlateWidth * sizeup, cursor.getMaxHeight() * sizeup);
		super.setSize(frameSize);
		super.setLocation((screenSize.width - frameSize.width)/2, 200);
		super.setTitle("Test Case " + Main.t + " (후판의 높이: " + cursor.getMaxHeight() + " )");
		super.setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		revalidate();
		repaint();
		
	
		
		
	}
	@Override
	public void paint(Graphics g) throws ConcurrentModificationException {

		int color[] = new int[]{250, 0, 100};
		
		plate_view.forEach( plate -> {
			
			
			g.setColor(new Color(color[0], color[1], color[2]));
			g.fillRect(plate.getY() * sizeup, plate.getX()* sizeup, plate.getWidth()* sizeup, plate.getHeight()* sizeup);
			g.setColor(new Color(color[1], color[2], color[0]));
			g.drawRect(plate.getY() * sizeup, plate.getX()* sizeup, plate.getWidth()* sizeup  , plate.getHeight()* sizeup);
			
			for(int i=0; i<3; i++){
				if(color[i] == 250)
					color[i] = 0;
				else
					color[i]+=50;

			}
			
		});
		
	
		
		

	}

	void printView() {


		plate_view.stream().forEach(System.out::println);
		
		
		
	}
}
