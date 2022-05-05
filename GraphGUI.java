/**
 * @author: Tom Ho
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphGUI extends JFrame implements ActionListener {
	private JFrame frame;
	private JComboBox start, end;
	private JLabel startLabel, endLabel;
    private JCheckBox useDistCostCheckBox, returnAddressCheckBox;
    private Graph graph;
    
    
    public GraphGUI() {
    	// Create start/end drop boxes
    	// Get an array of vertices name
    	ArrayList<String> vertices = new ArrayList<String>();
    	for (String vertex : graph.getGraphData().getKey().getName()) {
    		vertices.add(vertex);
    	}
    	
    	start = new JComboBox(vertices.toArray());
    	end = new JComboBox(vertices.toArray());
    	// Create a drop box
    	start.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                return new BasicComboPopup(comboBox) {
                    @Override
                    protected JScrollPane createScroller() {
                        JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                            @Override
                            protected JButton createDecreaseButton(int orientation) {
                                return createZeroButton();
                            }

                            @Override
                            protected JButton createIncreaseButton(int orientation) {
                                return createZeroButton();
                            }

                            @Override
                            public Dimension getPreferredSize(JComponent b) {
                                return new Dimension(10, super.getPreferredSize(b).height);
                            }

                            private JButton createZeroButton() {
                                return new JButton() {
                                    @Override
                                    public Dimension getMinimumSize() {
                                        return new Dimension(new Dimension(0, 0));
                                    }

                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(new Dimension(0, 0));
                                    }

                                    @Override
                                    public Dimension getMaximumSize() {
                                        return new Dimension(new Dimension(0, 0));
                                    }
                                };
                            }
                        });
                        return scroller;
                    }
                };
            }
        });

        end.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                return new BasicComboPopup(comboBox) {
                    @Override
                    protected JScrollPane createScroller() {
                        JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                            @Override
                            protected JButton createDecreaseButton(int orientation) {
                                return createZeroButton();
                            }

                            @Override
                            protected JButton createIncreaseButton(int orientation) {
                                return createZeroButton();
                            }

                            @Override
                            public Dimension getPreferredSize(JComponent b) {
                                return new Dimension(10, super.getPreferredSize(b).height);
                            }

                            private JButton createZeroButton() {
                                return new JButton() {
                                    @Override
                                    public Dimension getMinimumSize() {
                                        return new Dimension(new Dimension(0, 0));
                                    }

                                    @Override
                                    public Dimension getPreferredSize() {
                                        return new Dimension(new Dimension(0, 0));
                                    }

                                    @Override
                                    public Dimension getMaximumSize() {
                                        return new Dimension(new Dimension(0, 0));
                                    }
                                };
                            }
                        });
                        return scroller;
                    }
                };
            }
        });
    	
    	// Set size and label
        start.setPreferredSize(new Dimension(40,15));
        startLabel = new JLabel("Start Vertex");
        end.setPreferredSize(new Dimension(40,15));
        endLabel = new JLabel("End Vertex");
        
        String startVertex = "";
        String endVertex = "";
        
        // Add ActionListener for end drop box
        start.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent startEvent) {
        		startVertex = start.getSelectedItem().toString();
        	}

        });
        
        // Add ActionListener for end drop box
        end.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent endEvent) {
        		endVertex = end.getSelectedItem().toString();
        	}

        });
        
        // Initialize useDistCost
        useDistCostCheckBox = new JCheckBox("Use Distance Cost");
        useDistCostCheckBox.addActionListener(this);
        // Initialize returnAddress
        returnAddressCheckBox = new JCheckBox("Return Address");
        returnAddressCheckBox.addActionListener(this);


    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// Set useDistCost
		if (e.getActionCommand().equals("Use Distance Cost")) {
            if (graph != null) graph.setUseDistCost(useDistCostCheckBox.isSelected());
            
        } else if (e.getActionCommand().equals("Return Address")) { // Set returnAddress
            if (graph != null) graph.setReturnAddress(returnAddressCheckBox.isSelected());
        }
	}
}
