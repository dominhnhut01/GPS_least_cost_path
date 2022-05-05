public class Test {
  public static void main(String[] args) {
    Graph graph = new Graph("MapInformationXY.txt");
    graph.findShortestPath("A", "E");
  }
}
