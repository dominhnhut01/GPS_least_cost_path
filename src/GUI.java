import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class GUI extends JFrame {
	private static final int FRAME_WIDTH = 650;
	private static final int FRAME_HEIGHT = 750;

	private boolean useDist = false;

	//Initialize the frame and panel
	private JFrame frame;
	private JPanel panelInfo;
	private JPanel panelContent;
	private JPanel panelButton;
	private JPanel picPanel;

	private BufferedImage image;

	//Initialize the component
	private JButton submitButton;
	private JLabel startLabel;
	private JLabel endLabel;
	private JLabel useDistLabel;
	private JCheckBox useDistCheckbox;
	private JTextField start;
	private JTextField end;
	private GraphPic graphPic;
	private JButton buttonspec;

	/** Default constructor, initialize the GUI.
	 * no param
	 * no return
	 */
	public GUI () throws IOException {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Finding shortest path:");
		setLayout(new BorderLayout());
		panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(3,2));
		TitledBorder border1 = new TitledBorder("Enter New Contact Information");
		panelInfo.setBorder(border1);

		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());

		submitButton = new JButton("Submit");

		start = new JTextField(20);
		startLabel = new JLabel("Start point:     ");

		end = new JTextField(20);
		endLabel = new JLabel("End point:      ");

		useDistLabel = new JLabel("Use Distance:      ");
		useDistCheckbox = new JCheckBox("");
		useDistCheckbox.addItemListener(new CheckBoxListener());

		// BufferedImage myPicture = ImageIO.read(new File("FinalProjectGraph_Basic_400x400.png"));
		// graphPic = new JLabel(new ImageIcon(myPicture));

		image = ImageIO.read(new File("FinalProjectGraph_Basic_400x400.png"));
		graphPic = new GraphPic();

		//Add all element to the panel
		panelInfo.add(startLabel);
		panelInfo.add(start);

		panelInfo.add(endLabel);
		panelInfo.add(end);
		panelInfo.add(useDistLabel);
		panelInfo.add(useDistCheckbox);

		panelButton.add(submitButton);

		panelContent = new JPanel();
		panelContent.add(graphPic);

		//Add panel to the frame
		add(panelInfo, BorderLayout.NORTH);
		add(panelButton, BorderLayout.CENTER);
		add(panelContent, BorderLayout.SOUTH);
	}

	class CheckBoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			if (event.getStateChange() == 1)
				useDist = true;
			else
				useDist = false;
			//graph.setReturnAddress(useDist)
		}
	}

	class GraphPic extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 50, 50, this);
				image.setVisible(true); // see javadoc for more info on the parameters
    }
	}


  public static void main(String[] args) throws IOException {
    GUI gui = new GUI();
    gui.setResizable(false);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setVisible(true);
  }
}
