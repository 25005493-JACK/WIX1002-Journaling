//LeeXinYiStart
//Weather recording 
package fopassignment.journaling01;

public class WeatherRecording {
    
    private API api = new API();
    
    public String getTodayWeather() {
        try {

            String url = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
            
            String json = api.get(url);
            
            String rawForecast = extractSummaryForecast(json);

            return mapForecast(rawForecast);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Weather unavailable";
        }
    }
    
    public String mapForecast(String rawForecast) {
        
        String forecast = rawForecast.toLowerCase();
        
        if (forecast.equalsIgnoreCase("berjerebu")) {
            return "Hazy";
            
        } else if (forecast.equalsIgnoreCase("tiada hujan")) {
            return "No rain";
            
        } else if (forecast.equalsIgnoreCase("hujan")) {
            return "Rain";
            
        }else if (forecast.equalsIgnoreCase("hujan di beberapa tempat")) {
            return "Scattered rain";
            
        } else if (forecast.equalsIgnoreCase("hujan di satu dua tempat")) {
            return "Isolated rain";
            
        } else if (forecast.equalsIgnoreCase("hujan di satu dua tempat di kawasan pantai")) {
            return "Isolated rain over coastal areas";
            
        } else if (forecast.equalsIgnoreCase("hujan di satu dua tempat di kawasan pedalaman")) {
            return "Isolated rain over inland areas";
            
        } else if (forecast.equalsIgnoreCase("ribut petir")) {
            return "Thunderstorms";
            
        } else if (forecast.equalsIgnoreCase("ribut petir di beberapa tempat")) {
            return "Scattered thunderstorms";
            
        } else if (forecast.equalsIgnoreCase("ribut petir di kebanyakan tempat")) {
            return "Thunderstorms in most places";
            
        } else if (forecast.equalsIgnoreCase("ribut petir di beberapa tempat di kawasan pedalaman")) {
            return "Scattered thunderstorms over inland areas";
            
        } else if (forecast.equalsIgnoreCase("ribut petir di satu dua tempat")) {
            return "Isolated thunderstorms";
            
        } else if (forecast.equalsIgnoreCase("ribut petir di satu dua tempat di kawasan pantai")) {
            return "Isolated thunderstorms over coastal areas";
            
        } else if (forecast.equalsIgnoreCase("ribut petir di satu dua tempat di kawasan pedalaman")) {
            return "Isolated thunderstorms over inland areas";
            
        } 
        
        return rawForecast;
    }
    
    public String extractSummaryForecast(String json) {
        
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
//LeeXinYiEnd