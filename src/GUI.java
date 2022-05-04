import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class GUI extends JFrame {
	private static final int FRAME_WIDTH = 650;
	private static final int FRAME_HEIGHT = 550;

	//Initialize the frame and panel
	private JFrame frame;
	private JPanel panelInfo;
	private JPanel panelButton;
	private JPanel panelContent;

	//Initialize the component
	private JButton submitButton;
	private JLabel startLabel;
	private JLabel endLabel;
	private JTextField start;
	private JTextField end;

	/** Default constructor, initialize the GUI.
	 * no param
	 * no return
	 */
	public GUI () {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Address Book Application");
		setLayout(new BorderLayout());
		panelInfo = new JPanel();
		panelInfo.setLayout(new GridLayout(2,2));
		TitledBorder border1 = new TitledBorder("Enter New Contact Information");
		panelInfo.setBorder(border1);

		panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());

		panelContent = new JPanel();
		TitledBorder border3 = new TitledBorder("Saved Contacts");
		panelContent.setBorder(border3);

		//Initialize add button
		submitButton = new JButton("Submit");

		//Initialize the name field
		start = new JTextField(20);
		startLabel = new JLabel("Start point:     ");

		//Initialize the email field
		end = new JTextField(20);
		endLabel = new JLabel("End point:      ");

		//Initialize the TextArea, print the content already in the file "contents.txt"

		//Add all element to the panel
		panelInfo.add(startLabel);
		panelInfo.add(start);

		panelInfo.add(endLabel);
		panelInfo.add(end);

		panelButton.add(submitButton);

		//Add panel to the frame
		add(panelInfo, BorderLayout.NORTH);
		add(panelContent, BorderLayout.SOUTH);
		add(panelButton, BorderLayout.CENTER);


	}

  public static void main(String[] args) {
    GUI gui = new GUI();
    gui.setResizable(false);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    gui.setVisible(true);
  }
}
