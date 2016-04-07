package MediaDatabase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Project #4 CS 2334, Section 10 April 18, 2016
 * <P>
 * This class creates the Histogram Panel
 * </P>
 *
 * @version 1.0
 */
class HistogramView extends JPanel implements ActionListener {

	/**
	 * Generated Long Serial Version UID
	 */
	private static final long serialVersionUID = -1332601653491625247L;

	// Linked Hash Map of yearly credits.
	private LinkedHashMap<Integer, ArrayList<Integer>> yearlyCredits;
	// Linked Hash Map of ranged yearly credits.
	private LinkedHashMap<Integer, ArrayList<Integer>> rangedYearlyCredits;
	private String title;
	private Integer maxValue;
	private ArrayList<Integer> yearArray;
	private boolean useChartRanges;
	private ArrayList<Integer> chartRanges;

	/**
	 * This method creates the data for the Histogram and if more than 6 years of credits exist, it will set the bars up
	 * as a range of years relevant to the total number of years credits.
	 * 
	 * @param yearlyCredits
	 * @param t
	 */
	public HistogramView(LinkedHashMap<Integer, ArrayList<Integer>> yearlyCredits, String t) {
		this.yearlyCredits = yearlyCredits;
		useChartRanges = false;
		title = t;
		setSize(500, 500);
		yearArray = new ArrayList<Integer>(yearlyCredits.keySet());
		Collections.sort(yearArray);

		if (yearArray.size() > 22) {
			useChartRanges = true;
			chartRanges = new ArrayList<Integer>(16);
			int dataRange = yearArray.get(yearArray.size() - 2) - yearArray.get(1);
			int segments = dataRange / 15;
			chartRanges.add(yearArray.get(1) + segments);
			for (int i = 1; i < 16; i++) {
				chartRanges.add(chartRanges.get(i - 1) + segments);
			}
			//System.out.println(yearArray.toString());
			//System.out.println(chartRanges.toString());

			rangedYearlyCredits = new LinkedHashMap<Integer, ArrayList<Integer>>();
			ArrayList<Integer> newCreditValues4Ranges = new ArrayList<Integer>(6);

			for (int index = 0; index < chartRanges.size(); index++) {
				newCreditValues4Ranges = new ArrayList<Integer>();
				for (int z = 0; z < 6; z++)
					newCreditValues4Ranges.add(0);
				if (index == 0) {
					for (Integer year : yearArray) {
						if (year < chartRanges.get(index)) {
							ArrayList<Integer> tempValues = yearlyCredits.get(year);
							for (int arrayIndex = 0; arrayIndex < 6; arrayIndex++) {
								newCreditValues4Ranges.set(arrayIndex,
										newCreditValues4Ranges.get(arrayIndex) + tempValues.get(arrayIndex));
							}
						}
					}
				}

				if (index > 0 && index < chartRanges.size() - 1) {
					for (int yearIndex = 1; yearIndex < yearArray.size(); yearIndex++) {
						int currentYear = yearArray.get(yearIndex);
						if (currentYear < chartRanges.get(index) && currentYear >= chartRanges.get(index - 1)) {
							ArrayList<Integer> tempValues = yearlyCredits.get(currentYear);
							for (int arrayIndex = 0; arrayIndex < 6; arrayIndex++) {
								newCreditValues4Ranges.set(arrayIndex,
										newCreditValues4Ranges.get(arrayIndex) + tempValues.get(arrayIndex));
							}
						}
					}
				}
				
				if (index == chartRanges.size() - 1) {
					for (Integer year : yearArray) {
						//System.out.println();
						//System.out.println(yearArray.size() + " " + index + " year " + year + " compares to "
								//+ chartRanges.get(index - 1));
						if (year >= chartRanges.get(index - 1)) {
							//System.out.println(year + ">=" + chartRanges.get(index - 1));
							ArrayList<Integer> tempValues = yearlyCredits.get(year);
							for (int arrayIndex = 0; arrayIndex < 6; arrayIndex++) {
								newCreditValues4Ranges.set(arrayIndex,
										newCreditValues4Ranges.get(arrayIndex) + tempValues.get(arrayIndex));
							}
						}
					}
				}
				rangedYearlyCredits.put(chartRanges.get(index), newCreditValues4Ranges);
			}
			// Testing print statements
			//for (Integer year : yearArray) {
				//ArrayList<Integer> temp = yearlyCredits.get(year);
				//System.out.println("Year: " + year + " " + temp.toString());
			//}
			//for (Integer year : chartRanges) {
				//ArrayList<Integer> temp = rangedYearlyCredits.get(year);
				//System.out.println("Year: " + year + " " + temp.toString());
			//}
		}
		else {
			useChartRanges = false;
		}

		maxValue = 0;
		int summedValues = 0;
		if (useChartRanges) {
			for (Integer k : chartRanges) {
				ArrayList<Integer> values = new ArrayList<Integer>();
				values = rangedYearlyCredits.get(k);

				for (int i = 0; i < 6; i++) {
					summedValues += values.get(i);
				}
				if (summedValues > maxValue)
					maxValue = summedValues;
				summedValues = 0;
			}
		} else
			for (Integer k : yearArray) {
				ArrayList<Integer> values = new ArrayList<Integer>();
				values = yearlyCredits.get(k);

				for (int i = 0; i < 6; i++) {
					summedValues += values.get(i);
				}
		
				if (summedValues > maxValue)
					maxValue = summedValues;
				summedValues = 0;
			}
	}

	/**
	 * This Method builds the histogram with legend.
	 */
	public void buildHistogram() {
		Integer[] sumCredits = new Integer[6];
		for (int i = 0; i < 6; i++)
			sumCredits[i] = 0;
		String[] legend = new String[6];

		legend[0] = "Actor Movies: ";
		legend[1] = "Actor Episodes: ";
		legend[2] = "Producer Movies: ";
		legend[3] = "Producer Episodes: ";
		legend[4] = "Director Movies: ";
		legend[5] = "Director Episodes: ";

		for (Integer k : yearArray) {
			ArrayList<Integer> values = new ArrayList<Integer>();
			values = yearlyCredits.get(k);
			for (int i = 0; i < 6; i++) {
				sumCredits[i] += values.get(i);
			}
		}

		legend[0] += sumCredits[0];
		legend[1] += sumCredits[1];
		legend[2] += sumCredits[2];
		legend[3] += sumCredits[3];
		legend[4] += sumCredits[4];
		legend[5] += sumCredits[5];

		// Legend component.
		JLabel label1 = new JLabel(legend[0]);
		label1.setBackground(java.awt.Color.RED);
		label1.setForeground(java.awt.Color.WHITE);
		label1.setOpaque(true);

		// Legend component.
		JLabel label2 = new JLabel(legend[1]);
		label2.setBackground(java.awt.Color.YELLOW);
		label2.setOpaque(true);

		// Legend component.
		JLabel label3 = new JLabel(legend[2]);
		label3.setBackground(java.awt.Color.GREEN);
		label3.setOpaque(true);

		// Legend component.
		JLabel label4 = new JLabel(legend[3]);
		label4.setBackground(java.awt.Color.BLACK);
		label4.setForeground(java.awt.Color.WHITE);
		label4.setOpaque(true);

		// Legend component.
		JLabel label5 = new JLabel(legend[4]);
		label5.setBackground(java.awt.Color.ORANGE);
		label5.setOpaque(true);

		// Legend component.
		JLabel label6 = new JLabel(legend[5]);
		label6.setBackground(java.awt.Color.BLUE);
		label6.setForeground(java.awt.Color.WHITE);
		label6.setOpaque(true);

		// Create a new frame.
		JFrame frame = new JFrame();
		// Set frame size.
		frame.setSize(1000, 700);
		// Center the frame dependent on the size of the screen.
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
		// Create new Panel.
		JPanel mainPanel = new JPanel();
		// Set Border Layout for Main Panel.
		mainPanel.setLayout(new BorderLayout());
		// Panel 1, Histogram panel.
		JPanel panel1 = new JPanel(new BorderLayout());
		Histogram histogram;
		if (useChartRanges)
			histogram = new Histogram(rangedYearlyCredits, title, maxValue, chartRanges, useChartRanges);
		else
			histogram = new Histogram(yearlyCredits, title, maxValue, yearArray, useChartRanges);

		panel1.add(histogram, BorderLayout.CENTER);
		panel1.setOpaque(true);
		panel1.setVisible(true);
		mainPanel.add(panel1, BorderLayout.CENTER);

		// Panel 2, Legend.
		JPanel panel2 = new JPanel();
		mainPanel.add(panel2, BorderLayout.SOUTH);
		panel2.add(label1);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);

		panel2.setSize(new Dimension(300, 300));

		JPanel panel3 = new JPanel();
		mainPanel.add(panel3, BorderLayout.WEST);

		// Add the panel to the frame.
		frame.add(mainPanel);
		// Add Panel1 to the main panel.
		mainPanel.add(panel1);
		// Add a window listener.
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		// Make the Frame Visible
		frame.setVisible(true);
		// Repaint the frame.
		frame.repaint();
		// Set the title of the frame.
		frame.setTitle("Histogram");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

class Histogram extends JPanel {

	/**
	 * Generated Serialized UID.
	 */
	private static final long serialVersionUID = 1L;

	private LinkedHashMap<Integer, ArrayList<Integer>> yearlyCredits;
	private String title;
	private Integer maxValue;
	private ArrayList<Integer> yearArray;
	private boolean useChartRanges;


	/**
	 * Histogram constructor.
	 * 
	 * @param yearlyCredits
	 * @param title
	 * @param maxValue
	 * @param yearArray
	 * @param useChartRanges
	 * @param useBeginningRange
	 */
	public Histogram(LinkedHashMap<Integer, ArrayList<Integer>> yearlyCredits, String title, Integer maxValue,
			ArrayList<Integer> yearArray, boolean useChartRanges) {
		this.yearlyCredits = yearlyCredits;
		this.maxValue = maxValue;
		this.yearArray = yearArray;
		this.useChartRanges = useChartRanges;
		this.title = title;
		setSize(500, 500);
	}

	/**
	 * Paint component method for drawing the item.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Dimension d = getSize();
		int clientWidth = d.width;
		int clientHeight = d.height;
		int barWidth = clientWidth / yearArray.size();
		int rectWidth = barWidth - 80 + 4 * yearArray.size();
		String[] legend = new String[yearArray.size()];
		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 15);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		if (useChartRanges) {
			legend[0] = "<" + yearArray.get(0).toString();
			legend[yearArray.size() - 1] = ">=" + yearArray.get(yearArray.size() - 2).toString();
			for (int i = 1; i < yearArray.size() - 1; i++) {
				Integer tempYear = yearArray.get(i) - 1;
				legend[i] = yearArray.get(i - 1).toString() + "-" + (tempYear).toString().substring(2, 4);
			}
		} else {
			for (int i = 1; i < yearArray.size(); i++) {
				legend[i] = yearArray.get(i).toString();
			}
		}
		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title + "'s Media Credits by Year(s)", x, y);

		int top = titleFontMetrics.getHeight();
		int bottom = clientHeight - labelFontMetrics.getHeight();

		double scale = (bottom / maxValue) * 1.02;
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);

		for (int i = 0; i < yearArray.size(); i++) {
			int valueX = i * barWidth + 20;

			ArrayList<Integer> values = new ArrayList<Integer>();
			values = yearlyCredits.get(yearArray.get(i));
			Integer sum = 0;
			for (Integer value : values) {
				sum += value;
			}
			int valueY = bottom - sum * (int) scale;
			int height = values.get(0) * (int) scale;

			labelFont = new Font("SansSerif", Font.PLAIN, 9);
			g.setFont(labelFont);

			// Set bar color, width, and height.
			g.setColor(Color.red);
			g.fillRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, rectWidth, height);
			if (values.get(0) > 1)
				g.drawString(values.get(0).toString(), valueX + rectWidth / 2, valueY + height / 2 + 5);

			valueY = valueY + height;
			height = values.get(1) * (int) scale;

			// Set bar color, width, and height.
			g.setColor(Color.yellow);
			g.fillRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, rectWidth, height);

			if (values.get(1) > 1)
				g.drawString(values.get(1).toString(), valueX + rectWidth / 2, valueY + height / 2 + 5);

			valueY = valueY + height;
			height = values.get(2) * (int) scale;

			// Set bar color, width, and height.
			g.setColor(Color.green);
			g.fillRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, rectWidth, height);
			if (values.get(2) > 1)
				g.drawString(values.get(2).toString(), valueX + rectWidth / 2, valueY + height / 2 + 5);

			valueY = valueY + height;
			height = values.get(3) * (int) scale;

			// Set bar color, width, and height.
			g.setColor(Color.black);
			g.fillRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.white);
			if (values.get(3) > 1)
				g.drawString(values.get(3).toString(), valueX + rectWidth / 2, valueY + height / 2 + 5);

			valueY = valueY + height;
			height = values.get(4) * (int) scale;

			// Set bar color, width, and height.
			g.setColor(Color.orange);
			g.fillRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, rectWidth, height);
			if (values.get(4) > 1)
				g.drawString(values.get(4).toString(), valueX + rectWidth / 2, valueY + height / 2 + 5);

			valueY = valueY + height;
			height = values.get(5) * (int) scale;

			// Set bar color, width, and height.
			g.setColor(Color.blue);
			g.fillRect(valueX, valueY, rectWidth, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, rectWidth, height);
			if (values.get(5) > 1)
				g.drawString(values.get(5).toString(), valueX + rectWidth / 2, valueY + height / 2 + 5);

			int labelWidth = labelFontMetrics.stringWidth(yearArray.get(i).toString());

			if (useChartRanges)
				labelFont = new Font("SansSerif", Font.PLAIN, 9);
			else
				labelFont = new Font("SansSerif", Font.BOLD, 15);
			g.setFont(labelFont);
			g.drawString(legend[i], valueX + rectWidth / 2 - 15, y);
		}
	}
}