public class Test {
  public static void main(String[] args) {
    Graph graph = new Graph("MapInformationXY.txt", true);
    graph.findShortestPath("K", "T");
  }
}
