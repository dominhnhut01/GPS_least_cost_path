public class Test {
  public static void main(String[] args) {
    Graph graph = new Graph("MapInformationXY.txt", false);
    System.out.println(graph.findShortestPath("S", "T"));
  }
}
