package dohaeng.com.bubang.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 서버에 통신을 하는 쓰레드 입니다.
 */
public class HttpComm extends Thread {
    static final String TAG ="HttpComm";
    Context mContext;

    String DefaultURL = "http://bullswiz.com:10554";
    String mUrlS;

    // 통신이 끝나고 나서 적용하기 위한 변수
    Runnable mRunnable;

    // 서버에 변수를 던지기 위한 변수
    JSONObject mQeryJO;

    // 서버에서 응답 받은 메시지 ex > 비밀번호가 틀렸습니다.
    public String mBodyS;

    //서버에서 응답 받은 데이터
    JSONObject mDataJO;

    // 서버에서 응답한 메시지
    public String mRecvDataS;

    // 서버 통신의 성공 여부
    boolean             isSuccess = false;

    // 서비스 타입
    String ServiceType = "bubang";
//  String              ServiceType = "bisos";



    Handler mHandler = new Handler();

    public HttpComm(Context context){
        this.mContext = context;
        init();
    }
    public void init(){
            mQeryJO = new JSONObject();
    }
    public void setUrl(String urlS){
        this.mUrlS = DefaultURL+urlS;
    }
    public void setQeryJO(JSONObject qeryJO){
            this.mQeryJO = qeryJO;
    }
    public void setRunnable(Runnable runnable) {
        this.mRunnable = runnable;
    }
    public boolean isSuccess() {
        return isSuccess;
    }
    @Override
    public void run() {
        try {
            mQeryJO.put("serviceType",ServiceType);
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL(mUrlS);       // URL 설정
            HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            http.setDefaultUseCaches(false);
            http.setDoInput(true);                          // 서버에서 읽기 모드 지정
            http.setDoOutput(true);                         // 서버로 쓰기 모드 지정
            http.setRequestMethod("POST");                  // 전송 방식은 POST

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            http.setRequestProperty("content-type", "application/json");
            //--------------------------
            //   서버로 값 전송
            //--------------------------
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(mQeryJO.toString());
            writer.flush();
            //--------------------------
            //   서버에서 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {         // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
            builder.append(str);                                // View에 표시하기 위해 라인 구분자 추가
            }
            mRecvDataS = builder.toString();                    // 전송결과를 전역 변수에 저장

            JSONObject mRecvJO = new JSONObject(mRecvDataS);
            if(mRecvJO.getInt("Code") == 0) {
                isSuccess = true;
                mDataJO = mRecvJO.getJSONObject("Data");
                Log.e(TAG, "get Data:" + mDataJO.toString());
            }else{
                mBodyS  = mRecvJO.getString("Body");
            }

            Log.e("gm","recvDataS:"+mRecvDataS);
            if(mRunnable != null){
                mHandler.post(mRunnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.run();
    }
}