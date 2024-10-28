package model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataUtil {

    @DataProvider(name="JsonDataProvider")
    public Object[] getTestDrivenData() {

        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        Object obj = null;
        try {
            obj = parser.parse(new FileReader("src/resources/testcase/data1.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        jsonObject = (JSONObject) obj;

        assert jsonObject != null;
        JSONArray formInfo = (JSONArray) jsonObject.get("form info");

        String[] dataArray = new String[formInfo.size()];

        JSONObject formInfoData;
        String cardNum, expMonth, expYear, cvvNum;

        for(int i=0; i<formInfo.size(); i++){

            formInfoData = (JSONObject) formInfo.get(i);
            cardNum = (String) formInfoData.get("CardNumber");
            expMonth = (String) formInfoData.get("ExpireMonth");
            expYear = (String) formInfoData.get("ExpireYear");
            cvvNum = (String) formInfoData.get("CVVNumber");

            dataArray[i] = cardNum + "," + expMonth + "," + expYear + "," + cvvNum;
        }
        return dataArray;
    }

    //Demo
    @Test(dataProvider = "JsonDataProvider")
    public void reader(String data){
        String info[] = data.split(",");

        System.out.println(info[0]);
        System.out.println(info[1]);
        System.out.println(info[2]);
        System.out.println(info[3]);

    }
}
