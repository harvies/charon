//package io.github.harvies.eris.base.jdk.http;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.time.Duration;
//
///**
// * @author harvies
// */
//public class HttpTest {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_2)
//                .followRedirects(HttpClient.Redirect.ALWAYS)
//                .connectTimeout(Duration.ofSeconds(20))
//                .build();
//        HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create("https://baidu.com")).build();
//        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        System.out.println(response.body());
//    }
//}
