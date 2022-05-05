public class Test {
  public static void main(String[] args) {
    Graph graph = new Graph("MapInformationXY2.txt", true);
    graph.findShortestPath("A", "E");
  }
}
