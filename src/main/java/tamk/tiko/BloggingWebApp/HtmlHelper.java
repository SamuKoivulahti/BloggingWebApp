package tamk.tiko.BloggingWebApp;

public interface HtmlHelper {
    String createHtmlPage(String content);
    String createBlogPost(String name, String title, String content);
    String createHtmlTable(String[][] data);
}
