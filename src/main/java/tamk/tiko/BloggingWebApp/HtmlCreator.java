package tamk.tiko.BloggingWebApp;

public class HtmlCreator implements HtmlHelper {
    @Override
    public String createHtmlPage(String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n<html lang=\"en\">\n\t<head>\n\t\t<meta charset=\"UTF-8\">\n\t\t<title>" + "\n" + "Blogging Application" + "\n\t\t</title>\n\t<head>\n\n" +
                "\t<body>\n" + content + "\n\t</body>\n</html>");
        return builder.toString();
    }

    @Override
    public String createBlogPost(String name, String title, String content) {
        StringBuilder builder = new StringBuilder();
        builder.append("<h1>" + title + "</h1>");
        return null;
    }

    @Override
    public String createHtmlTable(String[][] data) {
        //<table><tr><td>a</td><td>b</td></tr><tr><td>c</td><td>d</td></tr></table>
        StringBuilder sb = new StringBuilder("<table border=\"1\">");

        for (int i = 0; i < data.length; i++) {
            sb.append("<tr>");

            for (int j = 0; j < data[i].length; j++) {
                sb.append("<td>");
                sb.append(data[i][j]);
                sb.append("</td>");
            }
            sb.append("</tr>");
        }

        sb.append("</table>");

        return sb.toString();
    }
}
