package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import dbutils.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ym on 2014/7/20.
 */
public class JsonTest {

    public static void main(String[] args) {
        JSONArray jsonArray3 = (JSONArray) JSONArray.parse("['json','is','easy']");

//        JSONArray.parseObject()
        System.out.println( jsonArray3 );


        List list = new ArrayList();

        list.add( "first" );
        list.add( "second" );

        String strJson = JSON.toJSONString(list,true);
//        JSONObject jsonArray2 = JSONArray.parseObject(String.valueOf(list));
        System.out.println( "--"+strJson+"--" );

        User user = null;
        list = new ArrayList();
        int i=0;
        int size = 20;
        while(i++<size) {
            user = new User();
            user.setId(i);
            user.setName("myu-"+i);
            user.setAge("13-"+i);
            list.add(user);
        }

        strJson = JSON.toJSONString(list,true);
        System.out.println(strJson);




    }
}
