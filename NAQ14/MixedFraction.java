import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class MixedFraction {
    MixedFraction() {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int d = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        while (n != 0 && d != 0) {
            int w = n / d;
            n %= d;
            sb.append(String.format("%d %d / %d\n", w, n, d));
            n = fs.nextInt();
            d = fs.nextInt();
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        new MixedFraction();
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