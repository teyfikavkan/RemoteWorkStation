package tr.com.havelsan.RemoteWorkStation.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping(path = "/get")
public class RemoteWorkStationCommandImpl implements RemoteWorkStationCommand{

    @GetMapping(path="/deneme", produces = "application/json")
    public void deneme()
    {
        try {
            blockSite("www.facebook.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void blockSite(String url) throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();

        // Use OS name to find correct location of hosts file
        String hostsFile = "";
        if ((OS.indexOf("win") >= 0)) {
            // Doesn't work before Windows 2000
            hostsFile = "C:\\Windows\\System32\\drivers\\etc\\hosts";
        } else if ((OS.indexOf("mac") >= 0)) {
            // Doesn't work before OS X 10.2
            hostsFile = "etc/hosts";
        } else if ((OS.indexOf("nux") >= 0)) {
            hostsFile = "/etc/hosts";
        } else {
            // Handle error when platform is not Windows, Mac, or Linux
            System.err.println("Sorry, but your OS doesn't support blocking.");
            System.exit(0);
        }

        // Actually block site
        Files.write(Paths.get(hostsFile), ("\n127.0.0.1:3000 " + url).getBytes(), StandardOpenOption.APPEND);
    }
}
