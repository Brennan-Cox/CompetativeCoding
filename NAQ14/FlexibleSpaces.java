import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;

public class FlexibleSpaces {
    FlexibleSpaces() {
    }
    public static void main(String[] args) {
        new FlexibleSpaces();
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