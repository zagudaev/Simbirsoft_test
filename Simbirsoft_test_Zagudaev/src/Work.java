import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Work {
    String regex = "\\s+|\\n+|\\t+|\\r+|,|!|\\)|\\(|\\[|]|\\.|=|\\+|;|:|\\?|\\*|\"|\'|}|\\{|>|<|«|»";

    public Object[] FilterWord(String st) {
        String bufer = "";
        String words = "";
        boolean chekStart = false;
        boolean chekFinish = true;
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == '>' && chekFinish) {
                chekStart = true;
                chekFinish = false;
            }
            if (st.charAt(i) == ';') {
                chekStart = false;
                bufer = "";
            }
            if (!chekStart && st.charAt(i) == '<') {
                chekFinish = true;
            }
            if (chekStart && st.charAt(i) != '<') {
                bufer += st.charAt(i);
            }
            if (chekStart && st.charAt(i) == '<') {
                words += " " + bufer;
                chekStart = false;
                chekFinish = true;
                bufer = "";
            }
        }
        Object[] arrg = Arrays.stream(words.trim().split(regex)).filter(s-> !s.equals("")).map(s -> s.trim()).toArray();
        // String[] arrg = words.split(regex);
        return arrg;
    }

    public void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            System.out.println("Key : " + entry.getKey() +"   Value : " + entry.getValue());

        }

    }

    public void AddDatabese(Map<String, Integer> map) throws SQLException, ClassNotFoundException {
        DatabeseHandler databeseHandler= new DatabeseHandler();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            databeseHandler.AddWord(entry.getKey(),entry.getValue());
        }
    }

    public Map WordCounter(Object[] st) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < st.length; i++) {
            int newValue = map.getOrDefault(st[i], 0) + 1;
            map.put((String) st[i], newValue);

        }
        return map;
    }
}
