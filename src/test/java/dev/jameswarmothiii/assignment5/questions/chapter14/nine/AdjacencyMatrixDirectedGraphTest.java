package dev.jameswarmothiii.assignment5.questions.chapter14.nine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AdjacencyMatrixDirectedGraphTest {

  @Test
  void deleteVertexShouldRemoveLabelAndShiftRemainingVertices() {
    AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph(4);
    graph.setLabel(0, 10);
    graph.setLabel(1, 20);
    graph.setLabel(2, 30);
    graph.setLabel(3, 40);

    graph.deleteVertex(1);

    assertEquals(3, graph.getVertexCount());
    assertEquals(10, graph.getLabel(0));
    assertEquals(30, graph.getLabel(1));
    assertEquals(40, graph.getLabel(2));
  }

  @Test
  void deleteVertexShouldRemoveAllIncidentEdges() {
    AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph(4);
    graph.addDirectedEdge(0, 1);
    graph.addDirectedEdge(1, 2);
    graph.addDirectedEdge(3, 1);
    graph.addDirectedEdge(2, 3);
    graph.addDirectedEdge(0, 3);

    graph.deleteVertex(1);

    assertEquals(3, graph.getVertexCount());
    assertTrue(graph.hasDirectedEdge(1, 2));
    assertTrue(graph.hasDirectedEdge(0, 2));
    assertFalse(graph.hasDirectedEdge(0, 1));
  }

  @Test
  void deleteVertexShouldRejectOutOfRangeVertexNumber() {
    AdjacencyMatrixDirectedGraph graph = new AdjacencyMatrixDirectedGraph(2);

    assertThrows(AdjacencyMatrixDirectedGraphException.class, () -> graph.deleteVertex(-1));
    assertThrows(AdjacencyMatrixDirectedGraphException.class, () -> graph.deleteVertex(2));
  }
}
