package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by user on 5/19/2017.
 */
public class CheckLink {

    public String printredirect(String url) throws Exception {
        String confURL = "";
        URL myUrl = new URL(url);
        URLConnection urlConn = myUrl.openConnection();
        urlConn.connect();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
        String str = null;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str + System.getProperty("line.separator"));
            if (str.contains("fastRedirect = ")) {
                confURL = str.replaceAll("fastRedirect = \"", "").replaceAll("\";", "");
            }
        }
        br.close();
        return confURL;
    }
}
