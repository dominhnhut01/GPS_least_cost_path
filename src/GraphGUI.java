/**
 * @author: Tom Ho
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphGUI extends JFrame implements ActionListener {
	private JMenuBar menuBar;
    private JMenu menu; 
    private JMenuItem MenuItem;
    private JPanel graphData, action, result; 
	private JTextArea start, end, resultText, verticesText, edgesText, queryText;
    private JScrollPane startList, endList, resultList, verticesList, edgesList, queryList;
	private JButton add, save;
    private JCheckBox useDistCostCheckBox, returnAddressCheckBox;
    private Graph graph;
    
    
    public GraphGUI() {
    	graphData = new JPanel();
        action = new JPanel();
        result = new JPanel();
        
        useDistCostCheckBox = new JCheckBox("Use Distance Cost");
        useDistCostCheckBox.addActionListener(this);
        action.add(useDistCostCheckBox);
        
        returnAddressCheckBox = new JCheckBox("Return Address");
        returnAddressCheckBox.addActionListener(this);
        action.add(returnAddressCheckBox);
        
        start = new JTextArea(5, 20);
        startList = new JScrollPane(start);
        startList.setBorder(new TitledBorder("Start vertices"));
        action.add(startList);
        
        end = new JTextArea(5, 20);
        endList = new JScrollPane(end);
        endList.setBorder(new TitledBorder("End vertices"));
        action.add(endList);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Use Distance Cost")) {
            if (graph != null) graph.setUseDistCost(useDistCostCheckBox.isSelected());
		} else if (e.getActionCommand().equals("Return Address")) {
			if (graph != null) graph.setUseDistCost(useDistCostCheckBox.isSelected());
		}
		
	}
}
