//package PoliciesAndAlgorithms;
//
////import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Map;
//
//public class Weather {
//
//    int cloudCover;
//    int humidity;
//    int avgtemp;
//    String description;
//
//
//    public Weather createWeather(String city) {
//        Weather weather = null;
//        try {
//
//            city = city.replaceAll(" ", "%20");
//            StringBuilder result = new StringBuilder();
//            URL url = new URL("http://api.weatherstack.com/current?access_key=2bde74e601c967ad20c9189c9826df4c&query=" + city);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            //Get Response
//            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while ((line = rd.readLine()) != null) {
//                result.append(line);
//            }
//            rd.close();
//
//
//            //JSONObject jsonObject = new JSONObject(result.toString());
//            //Map<String, Object> map = jsonObject.toMap();
//            //Map<String, Object> currentMap = (Map<String, Object>) map.get("current");
//
//            weather = new Weather((int) currentMap.get("cloudcover"), (int) currentMap.get("humidity"), (int) currentMap.get("avgtemp"), (String) currentMap.get("weather_descriptions"));
//
//        } catch (
//                IOException e) {
//            e.printStackTrace();
//        }
//
//        return weather;
//    }
//
//    private Weather(int cloudCover, int humidity, int avgtemp, String description) {
//        this.cloudCover = cloudCover;
//        this.humidity = humidity;
//        this.avgtemp = avgtemp;
//        this.description = description;
//    }
//}
