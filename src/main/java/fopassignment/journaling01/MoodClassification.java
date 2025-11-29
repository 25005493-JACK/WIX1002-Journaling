//LeeXinYiStart
package fopassignment.journaling01;

import java.util.Map;

public class MoodClassification {
    
    private final API api = new API();
    private final String POST_URL = "https://router.huggingface.co/hf-inference/models/distilbert/distilbert-base-uncased-finetuned-sst-2-english";
    
    // method to classify sentiment
    public String classifySentiment(String journalInput) {
        String bearerToken = "";
        
        try {
            // load the environment variables to get the token
            Map<String, String> env = EnvLoader.loadEnv(".env");
            bearerToken = env.get("BEARER_TOKEN");  
            
            if (bearerToken == null || bearerToken.isEmpty()) {
                System.err.println("Error: BEARER_TOKEN is not set in the .env file.");
                return "Unavailable (Token Missiong)";
            }
            
            // format the JSON body with the user's input
            String jsonBody = "{\"inputs\": \"" + journalInput.replace("\"", "\\\"") + "\"}";
            
            // call the post request
            String postResponse = api.post(POST_URL, bearerToken, jsonBody);
            
            // extract the mood label
            return extractTopLabel(postResponse);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Unavailable (API Error)";
        }   
    }
    
    // method to extract the mood from the JSON response
    private String extractTopLabel(String json) {
        String key = "\"label\":\"";
        int start = json.indexOf(key);
            
        if (start == -1) {
            return "UNKNOWN";
        }
            
        start += key.length();
            
        // find the closing quote after the label value
        int end = json.indexOf("\"", start);
            
        if (end == -1) {
            return "UNKNOWN";
        }
            
        return json.substring(start, end);
    }
}
//LeeXinYiEnd