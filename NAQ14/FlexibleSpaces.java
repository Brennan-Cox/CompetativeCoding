import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class FlexibleSpaces {
    FlexibleSpaces() {
        FastScanner fs = new FastScanner(System.in);
        int w = fs.nextInt();
        int[] partitions = new int[fs.nextInt() + 2];
        {
            int index = 0;
            partitions[index++] = 0;
            for (;index < partitions.length - 1; index++) {
                partitions[index] = fs.nextInt();
            }
            partitions[index] = w;
        }
        // Guarantees no duplicates
        Set<Integer> possibleWidths = new HashSet<>();
        for (int i = 0; i < partitions.length; i++) {
            for (int j = i + 1; j < partitions.length; j++) {
                possibleWidths.add(partitions[j] - partitions[i]);
            }
        }
        // Guarantees sorted
        List<Integer> list = new ArrayList<>(possibleWidths);
        list.sort((o1, o2) -> o1 - o2);
        StringBuilder sb = new StringBuilder();
        list.stream().forEach((n) -> sb.append(n + " "));
        System.out.println(sb.toString().trim());
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