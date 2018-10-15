import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

//import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mPoints {
    public static void main(String[] args){

        int waitTime = 270;
        try{
            if (args.length == 1){
                waitTime = Integer.parseInt(args[0]);
                if (waitTime < 0){
                    throw NumberFormatException;
                }
            }
        }catch(NumberFormatException e){
            // Assuming command is invoked with bash script
            System.out.println("Usage: mpoint [number of milliseconds for timeout]")
        }
        
        try {
            WebClient web = new WebClient(BrowserVersion.CHROME);
            web.getOptions().setCssEnabled(false);
            java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);

            HtmlPage wstac = web.getPage("https://acadinfo.wustl.edu/WSHome/Default.aspx");
            HtmlForm form = wstac.getFormByName("aspnetForm");
            HtmlSubmitInput button = form.getInputByName("ctl00$cphBody$btnLogin");
            button.click();
            web.waitForBackgroundJavaScript(waitTime);

            HtmlPage p = web.getPage(web.getCurrentWindow().getEnclosedPage().getUrl());


            HtmlForm f1 = p.getFormByName("form1");
            f1.getInputByName("ucWUSTLKeyLogin$txtUsername").setAttribute("value", System.getenv("WEBSTAC_USERNAME"));
            f1.getInputByName("ucWUSTLKeyLogin$txtPassword").setAttribute("value", System.getenv("WEBSTAC_PASSWORD"));
            f1.getInputByName("ucWUSTLKeyLogin$btnLogin").click();
            web.waitForBackgroundJavaScript(waitTime);

            //web.getPage(web.getCurrentWindow().getEnclosedPage().getUrl());
            HtmlPage p2 = web.getPage("https://acadinfo.wustl.edu/WSHome/Generic.aspx?Type=Meal%20Plan&Page=/CBORD/MealPlan/");
            web.waitForBackgroundJavaScript(waitTime);

            Pattern pattern1 = Pattern.compile("Current Point Balance: (\\d+.\\d+)");
            Pattern pattern2 = Pattern.compile("Suggested Point Balance: (\\d+.\\d+)");
            Matcher match1 = pattern1.matcher(p2.asText());
            Matcher match2 = pattern2.matcher(p2.asText());
            match1.find();
            match2.find();
            System.out.println("Current: " + match1.group(1));
            System.out.println("Suggested: " + match2.group(1));



            //java.awt.Desktop.getDesktop().browse(new URI("https://www.google.com/"));


        }
        catch(Exception e){

        }
    }
}
