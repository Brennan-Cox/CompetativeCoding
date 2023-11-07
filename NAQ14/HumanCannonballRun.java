import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://open.kattis.com/problems/humancannonball
 * Convert problem into a Dijkstra's algorithm problem
 * and then solve!
 */
public class HumanCannonballRun {

    /**
     * Something meant to represent a node
     */
    class Node {
        List<Edge> edges;
        double x, y;
        boolean isCannon;

        public Node(FastScanner s, boolean isCannon) {
            edges = new ArrayList<Edge>();
            x = s.nextDouble();
            y = s.nextDouble();
            this.isCannon = isCannon;
        }
    }

    /**
     * Something meant to represent a directed edge
     * ARGH I WISH KATTIS SUPPORTED RECORDS!
     */
    class Edge {
        Node node;
        double time;

        public Edge(Node node, double time) {
            this.node = node;
            this.time = time;
        }
    }

    /**
     * Something meant to represent a node in a priority queue
     */
    class QueueNode implements Comparable<QueueNode> {
        Node node;
        double time;

        public QueueNode(Node node, double time) {
            this.node = node;
            this.time = time;
        }

        public int compareTo(QueueNode other) {
            return Double.compare(time, other.time);
        }
    }

    /**
     * Creates a list of nodes from the input
     * 
     * @return
     */
    List<Node> readInput() {
        FastScanner s = new FastScanner(System.in);
        List<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node(s, false));
        nodes.add(new Node(s, false));
        int n = s.nextInt();
        for (; n > 0; n--) {
            nodes.add(new Node(s, true));
        }
        return nodes;
    }

    // use a helper!
    double distance(Node a, Node b) {
        // sqrt((x2-x1)^2 + (y2-y1)^2)
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    /**
     * return the seconds to walk from a to b
     * 
     * @param a
     * @param b
     * @return
     */
    double walkTime(Node a, Node b) {
        // 5 times the distance
        return distance(a, b) / 5.0;
    }

    /**
     * returns the seconds to cannon then walk from a to b
     * 
     * @param a
     * @param b
     * @return
     */
    double cannonTime(Node a, Node b) {
        // min of walking dist and
        // of dist minus 50 over 5 plus 2
        // must be a dist of 50 in some direction (overshoot possible) => abs
        // |dist(a, b) - 50| / 5 + 2
        return Math.abs(distance(a, b) - 50) / 5.0 + 2;
    }

    /**
     * Calculates all edges between nodes
     * creating a fully connected graph
     * 
     * @param nodes
     */
    void calculateEdges(List<Node> nodes) {
        for (Node current : nodes) {
            for (Node other : nodes) {
                // For every natural pair of nodes
                if (current == other) {
                    continue;
                }
                // Calculate the time to get from current Node to other Node
                double time = Double.POSITIVE_INFINITY;
                if (current.isCannon) {
                    time = cannonTime(current, other);
                }
                time = Math.min(time, walkTime(current, other));
                current.edges.add(new Edge(other, time));
            }
        }
    }

    public HumanCannonballRun() {
        List<Node> nodes = readInput();
        calculateEdges(nodes);
        // start location was the first read in
        Node start = nodes.get(0);
        // end location was the second read in
        Node end = nodes.get(1);
        // Dijkstra's algorithm
        // PriorityQueue will keep the best option first
        PriorityQueue<QueueNode> queue = new PriorityQueue<>();
        // visited will keep track of which edges we've already visited
        // This is useful because we can't remove edges from the queue
        // we would end up with many duplicates and cycles I.E will not work!
        // although mathematically sound
        HashSet<Edge> visited = new HashSet<>();
        // enter your start point into the queue
        queue.add(new QueueNode(start, 0));
        // while there are still nodes to visit
        while (!queue.isEmpty()) {
            // get the best from front of the queue
            QueueNode qn = queue.poll();
            // if the best is the end, we're done!
            if (qn.node == end) {
                // format output
                System.out.println(qn.time);
                return;
            }
            // for every edge from the best node
            for (Edge e : qn.node.edges) {
                if (visited.contains(e)) {
                    continue;
                }
                // if not visited add to queue
                // AND ADD TO VISITED!
                visited.add(e);
                queue.add(new QueueNode(e.node, qn.time + e.time));
            }
        }
    }

    public static void main(String... args) {
        new HumanCannonballRun();
    }
}

class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner(Reader in) {
        br = new BufferedReader(in);
    }

    public FastScanner(InputStream in) {
        this(new InputStreamReader(in));
    }

    public String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }

    public double nextDouble() {
        return Double.parseDouble(next());
    }

}