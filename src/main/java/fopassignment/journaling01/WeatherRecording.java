//LeeXinYiStart
package fopassignment.journaling01;

public class WeatherRecording {
    
    private API api = new API();
    
    public String getTodayWeather() {
        try {
            // the url might need to change
            String url = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
            
            // call GET request
            String json = api.get(url);
            
            String rawForecast = extractSummaryForecast(json);
            
            // extract summary_forecast
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
        
//        switch (forecast) {
//            case "berjerebu":
//                return "Hazy";
//            
//            case "tiada hujan":
//                return "No rain";
//                
//            case "hujan":
//                return "Rain";
//                
//            case "hujan di beberapa tempat":
//                return "Scattered rain";
//                
//            case "hujan di satu dua tempat":
//                return "Isolated rain";
//                
//            case "hujan di satu dua tempat di kawasan pantai":
//                return "Isolated rain over coastal areas";
//                
//            case "hujan di satu dua tempat di kawasan pedalaman":
//                return "Isolated rain over inland areas";
//                
//            case "ribut petir":
//                return "Thunderstorms";
//                
//            case "ribut petir di beberapa tempat":
//                return "Scattered thunderstorms";
//                
//            case "ribut petir di kebanyakan tempat":
//                return "Thunderstorms in most places";
//                
//            case "ribut petir di beberapa tempat di kawasan pedalaman":
//                return "Scattered thunderstorms over inland areas";
//                
//            case "ribut petir di satu dua tempat":
//                return "Isolated thunderstorms";
//                
//            case "ribut petir di satu dua tempat di kawasan pantai":
//                return "Isolated thunderstorms over coastal areas";
//                
//            case "ribut petir di satu dua tempat di kawasan pedalaman":
//                return "Isolated thunderstorms over inland areas";
//            
//        }
        
        return rawForecast;
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
//LeeXinYiEnd