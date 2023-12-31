import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * https://open.kattis.com/problems/8queens
 */
class EightQueens {

    /**
     * Let's create a Pair to keep track of a position on the board
     */
    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return (x + "" + y).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Pair other = (Pair) obj;
            return (x == other.x && y == other.y);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    /**
     * Lets do the operation of moving the queen in all valid directions
     * 
     * @param queens
     * @param x
     * @param y
     */
    void queenMoves(HashSet<Pair> queens, int x, int y) {
        final int bound = 8;
        // queen moves up and down |
        for (int i = 0; i < bound; i++) {
            queens.add(new Pair(i, y));
        }
        // queen moves left and right --
        for (int i = 0; i < bound; i++) {
            queens.add(new Pair(x, i));
        }
        // queen moves diagonally \
        {
            int i = x, j = y;
            while (i > 0 && j > 0) {
                i--;
                j--;
            }
            while (i < bound && j < bound) {
                queens.add(new Pair(i, j));
                i++;
                j++;
            }
        }
        // queen moves diagonally /
        {
            int i = x, j = y;
            while (i > 0 && j < bound) {
                i--;
                j++;
            }
            while (i < bound && j > 0) {
                queens.add(new Pair(i, j));
                i++;
                j--;
            }
        }
    }

    /**
     * Implementation
     */
    EightQueens() {
        // read full input
        FastScanner s = new FastScanner(System.in);
        // keep track of all queens placed and their possible moves
        HashSet<Pair> queens = new HashSet<>();
        int count = 0;
        for (int i = 0; i < 8; i++) {

            String line = s.next();
            // for each cell if queen determine if possible to place
            for (int j = 0; j < 8; j++) {
                if (line.charAt(j) == '*') {
                    count++;
                    if (queens.contains(new Pair(i, j))) {
                        System.out.println("invalid");
                        return;
                    }
                    queens.add(new Pair(i, j));
                    queenMoves(queens, i, j);
                }
            }
        }
        // COUNT WAS IMPORTANT!
        System.out.println(count == 8 ? "valid" : "invalid");
    }

    public static void main(String[] args) {
        new EightQueens();
    }
}

/**
 * Scanner that will first read all input and
 * store it, then return it one token at a time
 * this is faster in cases of large input
 * with many calls to Scanner
 */
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