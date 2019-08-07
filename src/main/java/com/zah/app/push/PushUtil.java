package com.zah.app.push;

import com.zah.app.push.android.AndroidCustomizedcast;
import org.json.JSONObject;

/**
 * Created by zah on 2017/6/20.
 */
public class PushUtil {
    private static PushClient client = new PushClient();

    public static void sendAndroidCustomizedcast(String spiderName, String title, String url) {
        AndroidCustomizedcast customizedcast = null;
        try {
            customizedcast = new AndroidCustomizedcast("594652daf29d98470b001154", "3ykkknsigsqnx937bja2swiggp1hv4ju");
            customizedcast.setAlias("developer", "SPIDER");
//		customizedcast.setAlias("alias", "SPIDER");
            customizedcast.setTicker("");
            customizedcast.setTitle("");
            customizedcast.setText("");
            JSONObject object = new JSONObject();
            object.put("spiderName", spiderName);
            object.put("title", title);
            object.put("url", url);
//        customizedcast.goCustomAfterOpen("{\"title\":\"spider new type\",\"url\":\"tt\"}");
            customizedcast.goCustomAfterOpen(object.toString());
            customizedcast.setDisplayType(AndroidNotification.DisplayType.MESSAGE);
            // TODO Set 'production_mode' to 'false' if it's a test device.
            // For how to register a test device, please see the developer doc.
//		customizedcast.setProductionMode();
            customizedcast.setProductionMode(false);
            client.send(customizedcast);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO Set your alias here, and use comma to split them if there are multiple alias.
        // And if you have many alias, you can also upload a file containing these alias, then
        // use file_id to send customized notification.

    }
}
