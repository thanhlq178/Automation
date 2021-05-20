package utilities.parse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import utilities.Constants;
import utilities.listeners.SuiteListener;
import utilities.listeners.TestListener;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class ReadWriteJson {

    public static String getValueFromConfigFile(String nodeName) {
        return getValueFromName(nodeName, Constants.RESOURCES_PATH + SuiteListener.configPath);
    }

    public static String getValueFromDataFile(String nodeName) {
        return getValueFromName(nodeName, Constants.RESOURCES_PATH + TestListener.dataPath);
    }

    private static String getValueFromName(String nodeName, String dataPath) {
        JSONParser parser = new JSONParser();
        String value = "";

        try (FileReader fileReader = new FileReader(Paths.get(dataPath).toFile())) {
            Object obj = parser.parse(fileReader);
            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);
            value = (String) jsonObject.get(nodeName);
        } catch (IOException | ParseException e) {
            Assert.fail("Fail for " + dataPath + " with the error " + e.getMessage());
        }
        return value;
    }
}


