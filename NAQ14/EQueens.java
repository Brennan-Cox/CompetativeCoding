import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class EQueens {

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
            return x + y;
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
    }

    boolean valid(HashSet<Pair> queens) {
        for (Pair queen : queens) {
            // move vertically
            for (int i = 0; i < 8; i++) {
                if (i != queen.x) {
                    if (queens.contains(new Pair(i, queen.y))) {
                        return false;
                    }
                }
            }
            // move horizontally
            for (int i = 0; i < 8; i++) {
                if (i != queen.y) {
                    if (queens.contains(new Pair(queen.x, i))) {
                        return false;
                    }
                }
            }
            // move diagonally \
            {
                int x = queen.x;
                int y = queen.y;
                while (x > 0 && y > 0) {
                    x--;
                    y--;
                }
                while (x < 8 && y < 8) {
                    if (x != queen.x && y != queen.y) {
                        if (queens.contains(new Pair(x, y))) {
                            return false;
                        }
                    }
                    x++;
                    y++;
                }
            }
            // move diagonally /
            {
                int x = queen.x;
                int y = queen.y;
                while (x > 0 && y < 7) {
                    x--;
                    y++;
                }
                while (x < 8 && y >= 0) {
                    if (x != queen.x && y != queen.y) {
                        if (queens.contains(new Pair(x, y))) {
                            return false;
                        }
                    }
                    x++;
                    y--;
                }
            }
        }
        // if we have 8 queens, then we are valid!
        return queens.size() == 8;
    }

    EQueens() {
        FastScanner sc = new FastScanner(System.in);
        HashSet<Pair> queens = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            String line = sc.next();
            for (int j = 0; j < 8; j++) {
                if (line.charAt(j) == '*') {
                    queens.add(new Pair(i, j));
                }
            }
        }
        System.out.println(valid(queens) ? "valid" : "invalid");
    }

    public static void main(String[] args) {
        new EQueens();
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