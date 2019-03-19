package tamk.tiko.BloggingWebApp;

public class HtmlCreator implements HtmlHelper {
    @Override
    public String createHtmlPage(String title, String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n<html lang=\"en\">\n\t<head>\n\t\t<meta charset=\"UTF-8\">\n\t\t<title>" + "\n" + title + "\n\t\t</title>\n\t<head>\n\n" +
                "\t<body>\n" + content + "\n\t</body>\n</html>");
        return builder.toString();
    }
}
