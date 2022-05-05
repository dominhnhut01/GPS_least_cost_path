import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class GUI extends JPanel {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 700;

    public static boolean useDistCost;
    private JTextArea resultText, verticesText, edgesText;
    private JScrollPane edgesScroll, verticesScroll, resultScroll;
    private JCheckBox useDistCostBox;
    private JButton findPath;
    private Graph graph;
    private Path shortestPath;
    private ArrayList<String> startOption, endOption;
    private JComboBox<String> startList, endList;
    private String start, end;
    private JPanel resultPanel;

    JFrame window = new JFrame("GPS Least Cost Path");

    public GUI() {
        window.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        window.setAlwaysOnTop(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(new BorderLayout());

        startOption = new ArrayList<String>();
        startOption.add("None");
        endOption = new ArrayList<String>();
        endOption.add("None");

        // Container pane = window.getContentPane();

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1));

        edgesText = new JTextArea(15, 15);
        verticesText = new JTextArea(15, 15);

        LoadData("MapInformationXY.txt");
        System.out.println("Read Done");

        edgesScroll = new JScrollPane(edgesText);
        edgesScroll.setPreferredSize(new Dimension(20, 30));
        edgesScroll.setBorder(new TitledBorder("Edges Info (Source, Destination, Time, Distance)"));

        verticesScroll = new JScrollPane(verticesText);
        edgesScroll.setPreferredSize(new Dimension(20, 30));
        verticesScroll.setBorder(new TitledBorder("Vertices Info (Name, Address, X, Y)"));

        JPanel selectionPanel = new JPanel(new GridLayout(3, 3));

        startList = new JComboBox<String>(startOption.toArray(new String[startOption.size()]));
        endList = new JComboBox<String>(endOption.toArray(new String[endOption.size()]));

        startList.addActionListener(new AddStartListActionListener());
        endList.addActionListener(new addEndActionListener());

        JLabel startLabel = new JLabel("Select your starting point: ");
        JLabel endLabel = new JLabel("Select your destination: ");

        startLabel.setForeground(Color.red);
        endLabel.setForeground(Color.blue);

        selectionPanel.add(startLabel);
        selectionPanel.add(startList);
        selectionPanel.add(endLabel);
        selectionPanel.add(endList);

        inputPanel.add(verticesScroll);
        inputPanel.add(edgesScroll);
        inputPanel.add(selectionPanel);

        window.add(inputPanel, BorderLayout.WEST);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(2, 1));

        useDistCostBox = new JCheckBox("Use Distance Cost");
        useDistCostBox.addActionListener(new AddDistCostActionListener());
        actionPanel.add(useDistCostBox);

        findPath = new JButton("Find Shortest Paths");
        findPath.addActionListener(new AddButtonActionListener());
        actionPanel.add(findPath);

        // actionPanel.setPreferredSize(new Dimension(20, 100));
        window.add(actionPanel, BorderLayout.SOUTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        JLabel outLabel = new JLabel("Result: ");
        outLabel.setForeground(Color.red);
        resultText = new JTextArea(10, 30);
        resultScroll = new JScrollPane(resultText);

        JPanel output_text = new JPanel();
        output_text.add(outLabel);
        output_text.add(resultScroll);

        resultPanel.add(this, BorderLayout.CENTER);
        resultPanel.add(output_text, BorderLayout.SOUTH);

        // Result Graph
        window.getContentPane().add(resultPanel, BorderLayout.CENTER);
        window.setVisible(true);
}

    class AddButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            resultText.setText("");
            graph.setUseDistCost(useDistCost);
            shortestPath = graph.findShortestPath(start, end);

            if(shortestPath == null) {
                resultText.append("No path found");
            } else if (shortestPath.getStart().equals(shortestPath.getEnd())){
                resultText.append("Using " + (useDistCost ? "Distance" : "Time") + " Cost\n");
                Vertex v = shortestPath.getStart();
                resultText.append(String.format("Path: %s-->%s\tTime Cost: 0\tDistance Cost: 0", v, v));
            }
              else {
                resultText.append("Using " + (useDistCost ? "Distance" : "Time") + " Cost\n");
                resultText.append(shortestPath.toString()+ "\n");
                repaint();
            }
        }
    }

    class AddDistCostActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (useDistCostBox.isSelected()) {
                useDistCost = true;
            } else {
                useDistCost = false;
            }
        }
    }

    class AddStartListActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JComboBox comboBox1 = (JComboBox) event.getSource();
            start = (String) comboBox1.getSelectedItem();
        }
    }

    class addEndActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JComboBox comboBox2 = (JComboBox) event.getSource();
            end = (String) comboBox2.getSelectedItem();
        }
    }

    public void LoadData(String fileName) {
        try {
            // initilize the graph
            graph = new Graph(fileName, useDistCost);
            for (Map.Entry<String, Vertex> entry : graph.getVertices().entrySet()) {
                String key = entry.getKey();
                Vertex v = entry.getValue();
                startOption.add(key);

                endOption.add(key);

                String result = String.format("%s\t%s\t%s\t%s", v.getName(), v.getAddress(), v.getX(), v.getY());
                verticesText.append(result + "\n");
            }

            for (Map.Entry<Vertex, ArrayList<Edge>> entry : graph.getGraphData().entrySet()) {
                ArrayList<Edge> edgeList = entry.getValue();

                for (Edge e : edgeList) {
                    String dataLine = e.toString();
                    edgesText.append(dataLine + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (shortestPath != null) {
            ArrayList<Vertex> vlist = shortestPath.getVertexList();

            for (Map.Entry<String, Vertex> entry : graph.getVertices().entrySet()) {
                Vertex v = entry.getValue();
                if(vlist.contains(v)) {
                    v.setState(1);
                } else {
                    v.setState(0);
                }
                graph.getVertices().replace(v.getName(), v);
            }


            for (Vertex v : vlist) {
                v.setState(1);
                graph.getVertices().replace(v.getName(), v);
            }
            graph.drawPath(g, shortestPath.getStart(), shortestPath.getEnd());
		} else
            graph.draw(g);
	}

    public static void main(String[] args) {
        try {
            new GUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
