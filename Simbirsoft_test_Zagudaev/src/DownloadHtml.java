import java.io.*;

import java.net.*;
import java.nio.charset.Charset;

public class DownloadHtml {
    private  String url ;
    private String directctory;
    private String nameFile;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirectctory() {
        return directctory;
    }

    public void setDirectctory(String directctory) {
        this.directctory = directctory;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public DownloadHtml(String url, String directctory, String nameFile) {
        this.url = url;
        this.directctory = directctory;
        this.nameFile = nameFile;
    }

    public void SaveHtml() throws IOException {
        URLConnection connection = new URL(getUrl()).openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.connect();
        BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);
        }
        File file = new File(directctory,nameFile);
        if (file.exists()){
            file.createNewFile();
        }
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(sb);
        printWriter.close();

    }
    public String RemoveHtml() throws Exception{
        File file = new File(directctory,nameFile);
        BufferedReader bufferedReader  = new BufferedReader(new FileReader(file));

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
      return stringBuilder.toString();
    }
}
