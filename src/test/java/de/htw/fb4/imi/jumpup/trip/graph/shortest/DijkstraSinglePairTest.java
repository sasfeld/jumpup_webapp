/**
 * JumpUp.Me Car Pooling Application
 *
 * Copyright (c) 2014-2015 Sebastian Renner, Marco Seidler, Sascha Feldmann
 */
package de.htw.fb4.imi.jumpup.trip.graph.shortest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htw.fb4.imi.jumpup.trip.graph.Edge;
import de.htw.fb4.imi.jumpup.trip.graph.Graph;
import de.htw.fb4.imi.jumpup.trip.graph.Path;
import de.htw.fb4.imi.jumpup.trip.graph.Vertex;
import de.htw.fb4.imi.jumpup.util.math.CoordinateUtil;

/**
 * <p></p>
 *
 * @author <a href="mailto:me@saschafeldmann.de">Sascha Feldmann</a>
 * @since 09.08.2015
 *
 */
public class DijkstraSinglePairTest
{
    private Graph graph;
    private Vertex origin;
    private Vertex destination;

    @Before
    public void before()
    {
        this.graph = buildTestGraph();
    }
    
    protected Graph buildTestGraph()
    {
        Graph g = new Graph();
        
        fillRouteFromKarlshorstToSchoeneweide(g);
        fillRouteFromSchoenweideToHtw(g);       
        
        return g;
    }
    
    protected void fillRouteFromKarlshorstToSchoeneweide(Graph g)
    {
        // Aristotelessteig
        this.origin = new Vertex(CoordinateUtil.newCoordinatesBy("52.491587,13.521616"));
        // Römerweg corner Treskowallee
        Vertex b = new Vertex(CoordinateUtil.newCoordinatesBy("52.491979,13.526595"));
        // Karlshorst center
        Vertex c = new Vertex(CoordinateUtil.newCoordinatesBy("52.486883,13.52711"));
        // Karlshorst Trabrennbahn
        Vertex d = new Vertex(CoordinateUtil.newCoordinatesBy("52.477553,13.523977"));
        // Edisonstr / Wilhelminenhofstr
        Vertex e = new Vertex(CoordinateUtil.newCoordinatesBy("52.46205,13.514063"));
        // S-Bahn Schöneweide
        Vertex f = new Vertex(CoordinateUtil.newCoordinatesBy("52.455905,13.510416"));
        
        // edges
        Edge ab = new Edge(this.origin, b, CoordinateUtil.calculateDistanceBetween(this.origin.getCoordinates(), b.getCoordinates()));
        Edge bc = new Edge(b, c, CoordinateUtil.calculateDistanceBetween(b.getCoordinates(), c.getCoordinates()));
        Edge cd = new Edge(c, d, CoordinateUtil.calculateDistanceBetween(c.getCoordinates(), d.getCoordinates()));
        Edge de = new Edge(d, e, CoordinateUtil.calculateDistanceBetween(d.getCoordinates(), e.getCoordinates()));
        Edge ef = new Edge(e, f, CoordinateUtil.calculateDistanceBetween(e.getCoordinates(), f.getCoordinates()));
        
        g.addVertex(origin)
         .addVertex(b)
         .addVertex(c)
         .addVertex(d)
         .addVertex(e)
         .addVertex(f);
        
        g.addEdge(ab)
         .addEdge(bc)
         .addEdge(cd)
         .addEdge(de)
         .addEdge(ef);        
    }
    
    protected void fillRouteFromSchoenweideToHtw(Graph g)
    {
        // Nalepastr
        Vertex a = new Vertex(CoordinateUtil.newCoordinatesBy("52.467985,13.504794"));
        // Edisonstr / Wilhelminenhofstr
        Vertex b = new Vertex(CoordinateUtil.newCoordinatesBy("52.462651,13.513548"));
        // Wilhelminenhofstr 78
        Vertex c = new Vertex(CoordinateUtil.newCoordinatesBy("52.460664,13.521402"));
        // HTW Wilhelminenhofstr
        this.destination = new Vertex(CoordinateUtil.newCoordinatesBy("52.457369,13.528097"));
        
        // edges
        Edge ab = new Edge(a, b, CoordinateUtil.calculateDistanceBetween(a.getCoordinates(), b.getCoordinates()));
        Edge bc = new Edge(b, c, CoordinateUtil.calculateDistanceBetween(b.getCoordinates(), c.getCoordinates()));
        Edge cd = new Edge(c, this.destination, CoordinateUtil.calculateDistanceBetween(c.getCoordinates(), this.destination.getCoordinates()));
  
        g.addVertex(a)
        .addVertex(b)
        .addVertex(c)
        .addVertex(destination);
        
        g.addEdge(ab)
        .addEdge(bc)
        .addEdge(cd);        
    }
    
    @Test
    public void testFindShortestPath() throws PathNotFoundException
    {
        Routable shortestPathStrategy = new DijkstraSinglePair();
        
        Path foundPath = shortestPathStrategy.findShortestPath(this.graph, this.origin, this.destination);
        
        assertEquals(2, foundPath.getTripsOnPath().size());
        assertEquals(1, foundPath.getIntersectionsOnPath().size());
    }

}
