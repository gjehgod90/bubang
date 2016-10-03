package dohaeng.com.bubang;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HeoDH on 2016-07-27.
 */

public class Activity_Spre {
    SharedPreferences pref;
    Activity_Spre(Context mContext){
        pref = mContext.getSharedPreferences("pref", mContext.MODE_PRIVATE);
    }
    // 값 불러오기
    public String getPreferences(String key){
        return pref.getString(key, "");
    }

    // 값 저장하기
    public void savePreferences(String key, String value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    public void removePreferences(String key){
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    // 값(ALL Data) 삭제하기
    public void removeAllPreferences(){
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
