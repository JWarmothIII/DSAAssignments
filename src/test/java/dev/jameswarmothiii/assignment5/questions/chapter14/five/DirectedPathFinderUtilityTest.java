package dev.jameswarmothiii.assignment5.questions.chapter14.five;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DirectedPathFinderUtilityTest {

  @Test
  void hasDirectedPathShouldReturnTrueForDirectEdge() {
    DirectedGraph graph = new DirectedGraph(3);
    graph.addDirectedEdge(0, 2);

    assertTrue(DirectedPathFinderUtility.hasDirectedPath(graph, 0, 2));
  }

  @Test
  void hasDirectedPathShouldReturnTrueForMultiStepRoute() {
    DirectedGraph graph = new DirectedGraph(6);
    graph.addDirectedEdge(0, 1);
    graph.addDirectedEdge(1, 4);
    graph.addDirectedEdge(4, 5);

    assertTrue(DirectedPathFinderUtility.hasDirectedPath(graph, 0, 5));
  }

  @Test
  void hasDirectedPathShouldRespectDirectionAndReturnFalseWhenNoRouteExists() {
    DirectedGraph graph = new DirectedGraph(5);
    graph.addDirectedEdge(0, 1);
    graph.addDirectedEdge(1, 2);
    graph.addDirectedEdge(3, 4);

    assertFalse(DirectedPathFinderUtility.hasDirectedPath(graph, 2, 0));
    assertFalse(DirectedPathFinderUtility.hasDirectedPath(graph, 0, 4));
  }

  @Test
  void hasDirectedPathShouldReturnTrueWhenStartAndEndAreSameVertex() {
    DirectedGraph graph = new DirectedGraph(4);

    assertTrue(DirectedPathFinderUtility.hasDirectedPath(graph, 3, 3));
  }

  @Test
  void hasDirectedPathShouldHandleCyclesWithoutLoopingForever() {
    DirectedGraph graph = new DirectedGraph(4);
    graph.addDirectedEdge(0, 1);
    graph.addDirectedEdge(1, 2);
    graph.addDirectedEdge(2, 0);
    graph.addDirectedEdge(2, 3);

    assertTrue(DirectedPathFinderUtility.hasDirectedPath(graph, 0, 3));
    assertFalse(DirectedPathFinderUtility.hasDirectedPath(graph, 3, 0));
  }

  @Test
  void hasDirectedPathShouldRejectNullGraph() {
    assertThrows(
        DirectedGraphException.class, () -> DirectedPathFinderUtility.hasDirectedPath(null, 0, 0));
  }

  @Test
  void hasDirectedPathShouldRejectOutOfRangeVertexNumbers() {
    DirectedGraph graph = new DirectedGraph(3);

    assertThrows(
        DirectedGraphException.class,
        () -> DirectedPathFinderUtility.hasDirectedPath(graph, -1, 1));
    assertThrows(
        DirectedGraphException.class, () -> DirectedPathFinderUtility.hasDirectedPath(graph, 0, 3));
  }
}
