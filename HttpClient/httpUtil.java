import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class httpUtil {

    /**
     * HTTP REQUEST GET -> JDK 11
     * 
     * @param url       -> request url
     * @param paramMap  -> url parameter
     * @param headerMap -> header option
     * @throws IOException
     * @throws InterruptedException
     */
    public static void get(String url, HashMap<String, String> paramMap, HashMap<String, String> headerMap)
            throws IOException, InterruptedException {
        HttpClient httpclient = HttpClient.newHttpClient();
        // uri builder -> add parameter and make uri
        UriComponents uri = null;
        UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString(url);

        // url parameter setting
        if (paramMap != null) {
            paramMap.forEach((key, value) -> {
                ucb.queryParam(key, value);
            });
        }

        uri = ucb.build(true);

        // httpRequest build
        HttpRequest request = null;
        HttpRequest.Builder rb = HttpRequest.newBuilder()
                .uri(uri.toUri())
                // timeout setting
                .timeout(Duration.ofSeconds(3000));

        // http header setting
        if (headerMap != null) {
            headerMap.forEach((key, value) -> {
                rb.header(key, value);
            });
        }

        request = rb.GET().build();

        // response parsing
        HttpResponse<String> response = httpclient.send(request, BodyHandlers.ofString());
        // way to send async (비동기)
        // HttpResponse<String> response = httpclient.sendAsync(request,
        // BodyHandlers.ofString());
        System.out.println(response);
    }

    /**
     * HTTP REQUEST POST -> JDK 11
     * 
     * @param url         -> request url
     * @param paramMap    -> url parameter
     * @param requestBody -> post parameter request body ( string only )
     * @param headerMap   -> header option
     * @throws IOException
     */
    public static void post(String url, HashMap<String, String> paramMap, String requestBody,
            HashMap<String, String> headerMap) throws IOException, InterruptedException {
        HttpClient httpclient = HttpClient.newHttpClient();
        // uri builder -> add parameter and make uri
        UriComponents uri = null;
        UriComponentsBuilder ucb = UriComponentsBuilder.fromUriString(url);

        // url parameter setting
        if (paramMap != null) {
            paramMap.forEach((key, value) -> {
                ucb.queryParam(key, value);
            });
        }

        uri = ucb.build(true);

        // httpRequest build
        HttpRequest request = null;
        HttpRequest.Builder rb = HttpRequest.newBuilder()
                .uri(uri.toUri())
                // timeout setting
                .timeout(Duration.ofSeconds(3000));

        // http header setting
        if (headerMap != null) {
            headerMap.forEach((key, value) -> {
                rb.header(key, value);
            });
        }

        // reqeust body setting
        request = rb.POST(HttpRequest.BodyPublishers.ofString(requestBody)).build();

        // response parsing
        HttpResponse<String> response = httpclient.send(request, BodyHandlers.ofString());
        // way to send async (비동기)
        // HttpResponse<String> response = httpclient.sendAsync(request,
        // BodyHandlers.ofString());
        System.out.println(response);
    }

    /**
     * HTTP REQUEST GET -> JDK 8
     * 
     * @param url       -> request url
     * @param paramMap  -> url parameter
     * @param headerMap -> header option
     * @throws IOException
     */
    public static void get(String url, HashMap<String, String> paramMap, HashMap<String, String> headerMap)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        // parameter append
        if (paramMap != null) {
            if (paramMap.size() > 0)
                sb.append("?");
            paramMap.forEach((key, value) -> {
                try {
                    sb.append(URLEncoder.encode(key, "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                    sb.append("&");
                } catch (UnsupportedEncodingException e) {
                    System.out.println("paramMap append error");
                    e.printStackTrace();
                }
            });
        }
        // uri setting
        URL uri = new URL(sb.toString());
        // connection
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        // connection option setting
        conn.setRequestMethod("GET");
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(15000);
        // header setting
        if (headerMap != null) {
            headerMap.forEach((key, value) -> {
                conn.setRequestProperty(key, value);
            });
        }
        // response parsing
        StringBuilder responseContents;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            responseContents = new StringBuilder();
            while ((line = in.readLine()) != null) {
                responseContents.append(line);
                responseContents.append(System.lineSeparator());
            }
            in.close();
        }
        // response after processing [customize]
        System.out.println(responseContents);
    }

    /**
     * HTTP REQUEST POST -> JDK 8
     * 
     * @param url         -> request url
     * @param paramMap    -> url parameter
     * @param requestBody -> post parameter request body ( string only )
     * @param headerMap   -> header option
     * @throws IOException
     */
    public static void post(String url, HashMap<String, String> paramMap, String requestBody,
            HashMap<String, String> headerMap) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(url);
        // parameter append
        if (paramMap != null) {
            if (paramMap.size() > 0)
                sb.append("?");
            paramMap.forEach((key, value) -> {
                try {
                    sb.append(URLEncoder.encode(key, "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(value, "UTF-8"));
                    sb.append("&");
                } catch (UnsupportedEncodingException e) {
                    System.out.println("paramMap append error");
                    e.printStackTrace();
                }
            });
        }
        // uri setting
        URL uri = new URL(sb.toString());
        // connection
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        // connection option setting
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        // header setting
        if (headerMap != null) {
            headerMap.forEach((key, value) -> {
                conn.setRequestProperty(key, value);
            });
        }
        // post request body parsing
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        // response parsing
        StringBuilder responseContents;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            responseContents = new StringBuilder();
            while ((line = in.readLine()) != null) {
                responseContents.append(line);
                responseContents.append(System.lineSeparator());
            }
            in.close();
        }
        // response after processing [customize]
        System.out.println(responseContents);
    }
}