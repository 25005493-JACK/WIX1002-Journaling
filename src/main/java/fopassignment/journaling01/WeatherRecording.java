
package fopassignment.journaling01;

public class WeatherRecording {
    
    private API api = new API();
    
    public String getTodayWeather() {
        try {
            // the url might need to change
            String url = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
            
            // call GET request
            String json = api.get(url);
            
            // extract summary_forecast
            return extractSummaryForecast(json);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Weather unavailable";
        }
    }
    
    public String extractSummaryForecast(String json) {
        
//        json = json.trim();
//        
//        if (json.startsWith("[")) {
//            json = json.substring(1);
//        }
//        
//        if (json.endsWith("]")) {
//            json = json.substring(0, json.length() - 1);
//        }
        
        String key = "\"summary_forecast\":\"";
        int start = json.indexOf(key);
        
        if (start == -1) {
            return "Weather not found";
        }
        
        start += key.length();
        int end = json.indexOf("\"", start);
        
        return json.substring(start, end);
    }       

}
