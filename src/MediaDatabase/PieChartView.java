package MediaDatabase;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Set;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PieChartView implements Serializable, ActionListener{

	/**
	 * Generated Serialized UID.
	 */
	private static final long serialVersionUID = 1L;
	private String[] legendInfo = null;
	private Integer[] sliceValues = null;
	private String title = null;
		
	/** 
	 * Pie Chart Constructor.
	 * 
	 * @param mediaMakerCredits
	 * @param title
	 */
	public PieChartView(LinkedHashMap<String, Integer> mediaMakerCredits, String title){
		legendInfo = new String[6];
		sliceValues = new Integer[6];
		this.title = title;
		int arrayIndex = 0;
		Set<String> keys = mediaMakerCredits.keySet();
		for(String k:keys){
			legendInfo[arrayIndex] = k;
			//System.out.println(legendInfo[arrayIndex]);
			sliceValues[arrayIndex] = mediaMakerCredits.get(k);
			//System.out.println(sliceValues[arrayIndex]);
			arrayIndex++;
		}
			
	
	}

	// Frame work for the pie chart interface.
	public void buildPieChart(){
		
		// Set Name, Color, and value of the legend labels.
		JLabel label1 = new JLabel(legendInfo[0] + ": " + sliceValues[0]);
		label1.setBackground(java.awt.Color.RED);
		label1.setForeground(java.awt.Color.WHITE);
		label1.setOpaque(true);
	
		JLabel label2 = new JLabel(legendInfo[1] + ": " + sliceValues[1]);
		label2.setBackground(java.awt.Color.YELLOW);
		label2.setOpaque(true);
	
		JLabel label3 = new JLabel(legendInfo[2] + ": " + sliceValues[2]);
		label3.setBackground(java.awt.Color.GREEN);
		label3.setOpaque(true);
	
		JLabel label4 = new JLabel(legendInfo[3] + ": " + sliceValues[3]);
		label4.setBackground(java.awt.Color.BLACK);
		label4.setForeground(java.awt.Color.WHITE);
		label4.setOpaque(true);
	
		JLabel label5 = new JLabel(legendInfo[4] + ": " + sliceValues[4]);
		label5.setBackground(java.awt.Color.ORANGE);
		label5.setOpaque(true);
	
		JLabel label6 = new JLabel(legendInfo[5] + ": " + sliceValues[5]);
		label6.setBackground(java.awt.Color.BLUE);
		label6.setForeground(java.awt.Color.WHITE);
		label6.setOpaque(true);
	
		// Construct new Frame.
		JFrame frame = new JFrame();
		// Set Frame Size.
		frame.setSize(700, 700);
		// Set the position of the frame when displayed to always be centered on viewing medium.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	
		// Main panel.
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());// FlowLayout, Grid
	
		// Panel 1 Pie Chart.
		MediaMakerPie pie = new MediaMakerPie();
		
		// Testing statement
		//boolean success = pie.setSlices(sliceValues);
		pie.setSlices(sliceValues);
		
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.add(pie, BorderLayout.CENTER);
		//panel1.add(new MediaMakerPie(), BorderLayout.CENTER);
		
		panel1.setVisible(true);
		panel.add(panel1, BorderLayout.CENTER);
	
		// Panel 2 Legend. 
		JPanel panel2 = new JPanel();
		panel.add(panel2, BorderLayout.SOUTH);
		panel2.add(label1);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);
	
		panel2.setSize(new Dimension(300, 300));
	
		frame.add(panel);
		frame.setVisible(true);
		frame.repaint();
		Font titleFont = new Font("SansSerif", Font.BOLD, 30);
		frame.setFont(titleFont);
		frame.setTitle(title + "'s Categorized Media Credits");
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


class Slice {
	double value;
	Color color;
	
	// Slice constructor.
	public Slice(double value, Color color) {
		this.value = value;
		this.color = color;
	}
}

class MediaMakerPie extends JComponent {
	
	private static final long serialVersionUID = 1083395895844571154L;
	
	// Set slice values.
	Slice[] slices = new Slice[6];
	public boolean setSlices(Integer[] nums) {
		slices[0] = new Slice(nums[0], Color.red);
		slices[1] = new Slice(nums[1], Color.yellow);
		slices[2] = new Slice(nums[2], Color.green);
		slices[3] = new Slice(nums[3], Color.black);
		slices[4] = new Slice(nums[4], Color.orange);
		slices[5] = new Slice(nums[5], Color.blue);
		return true;
	}

	// Paint Pie.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawPie((Graphics2D) g, getBounds(), slices);
	}

	public void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
		double total = 0.0D;
		for (int i = 0; i < slices.length; i++) {
			total += slices[i].value;
		}
		double curValue = 0.0D;
		int startAngle = 0;
		for (int i = 0; i < slices.length; i++) {
			startAngle = (int) (curValue * 360 / total);
			int arcAngle = (int) (slices[i].value * 360 / total);
			g.setColor(slices[i].color);
			g.fillArc(area.x, area.y, area.width, area.height, startAngle, arcAngle);
			curValue += slices[i].value;
		}
	}
}