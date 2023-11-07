import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class FlipFive {

    int stateToInt(boolean[][] state) {
        int res = 0;
        for (int i = 0; i < 9; i++) {
            res <<= 1;
            if (state[i / 3][i % 3]) {
                res |= 1;
            }
        }
        return res;
    }

    boolean[][] readState(FastScanner fs) {
        boolean[][] state = new boolean[3][3];
        for (int i = 0; i < 3; i++) {
            String line = fs.next();
            for (int j = 0; j < 3; j++) {
                state[i][j] = line.charAt(j) == '*';
            }
        }
        return state;
    }

    int flip(int state, int i) {
        int res = state;
        res ^= 1 << i;
        if (i % 3 > 0) {
            res ^= 1 << (i - 1);
        }
        if (i % 3 < 2) {
            res ^= 1 << (i + 1);
        }
        if (i / 3 > 0) {
            res ^= 1 << (i - 3);
        }
        if (i / 3 < 2) {
            res ^= 1 << (i + 3);
        }
        return res;
    }

    FlipFive() {
        FastScanner fs = new FastScanner(System.in);
        int t = fs.nextInt();
        for (; t > 0; t--) {
            int start = stateToInt(readState(fs));
            int end = 0;
            HashSet<Integer> visited = new HashSet<Integer>();
            visited.add(start);
            Deque<Integer> q = new ArrayDeque<Integer>();
            q.add(start);
            q.add(Integer.MAX_VALUE); // sentinel
            int count = 0;
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur == Integer.MAX_VALUE) {
                    count++;
                    q.add(Integer.MAX_VALUE);
                    continue;
                }
                if (cur == end) {
                    System.out.print(count);
                    break;
                }
                for (int i = 0; i < 9; i++) {
                    int next = flip(cur, i);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                    }
                }
            }
            if (t > 1) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new FlipFive();
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