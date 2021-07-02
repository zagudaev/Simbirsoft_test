import java.util.HashMap;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите URL: ");
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();

        DownloadHtml downloadHtml = new DownloadHtml(url,"D:\\test","site.html");
        downloadHtml.SaveHtml();
        Work work = new Work();
        Object [] objects = work.FilterWord(downloadHtml.RemoveHtml());
        HashMap<String,Integer> map = (HashMap<String, Integer>) work.WordCounter(objects);
        work.printMap(map);
        work.AddDatabese(map);


       // https://www.simbirsoft.com/




    }


}
