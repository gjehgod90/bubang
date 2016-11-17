package dohaeng.com.bubang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dohaeng.com.bubang.utils.HttpComm;
import dohaeng.com.bubang.utils.HttpURL;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;


public class Activity_Login extends AppCompatActivity {
    public static int LOGIN_RESULT_SUCCESS = 5;

    Context mContext;
    EditText etEmail;
    EditText etPassword;
    Button btnSignup;
    Button btnLogin;
    Button btnPwfind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        initUI();
    }

    private void initUI() {
        // Set up the login form.
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(mClickListener);

        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(mClickListener);

        btnPwfind = (Button) findViewById(R.id.btnPwfind);
        btnPwfind.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnLogin) {
                login();
//                startActivity(new Intent(Activity_Login.this, Activity_Main.class));
            }
            if (view.getId() == R.id.btnSignup) {
                startSignupActivity();
            }
            if (view.getId() == R.id.btnPwfind) {
                startActivity(new Intent(Activity_Login.this, Activity_Pw.class));
            }
        }

        public void startSignupActivity() {
            startActivity(new Intent(Activity_Login.this, Activity_Signup.class));
        }


        public void login() {
            if (!(etEmail.length() > 0)) {
                Toast.makeText(mContext, R.string.desc_pz_enter_email, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(etPassword.length() > 0)) {
                Toast.makeText(mContext, R.string.desc_pz_enter_pwd, Toast.LENGTH_SHORT).show();
                return;
            }

            JSONObject mDataJO = new JSONObject();
            String Email = etEmail.getText().toString();
            String Pwd = etPassword.getText().toString();

            try {
                mDataJO.put("userId", Email);
                mDataJO.put("pwd", Pwd);

            } catch (JSONException e) {
                e.printStackTrace();
            }



            final HttpComm mHttpComm = new HttpComm(mContext);  //mContext가 포함된 httpComm이라는 객체를 생성
            mHttpComm.setUrl(HttpURL.UserLogin);    //mHttpComm에 있는 setUrl을 불러온다.(HttpURL에 UserLogin이 포함된.)
            mHttpComm.setQeryJO(mDataJO);   //mHttpComm에 있는 setQerJo(mDataJO를 포함한)를 불러온다.
            mHttpComm.setRunnable(new Runnable() {  //mhTttpComm에 있는 setRunnable 를 불러온다.
                @Override
                public void run() { //오버라이딩하여 런펑션을 재정의한다.
                    if (mHttpComm.isSuccess()) {    //mHttpComm에 있는 isSuccess를 불러온다.
                        Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show(); //입력한값이 트루라면 성공이라는 텍스트를 보여준다.
                        Intent intent = new Intent();   //새로운 인텐트 객체 생성
                        Activity_Login.this.setResult(LOGIN_RESULT_SUCCESS, intent);//RESULT_OK를 돌려주면 MainActivity 에서 받는다.
                        startActivity(new Intent(Activity_Login.this, Activity_Main.class));    //성공하면 로그인엑티비티를 종료하고 메인엑티비티를 띄어준다.
                        finish();   //엑티비티 종료
                    } else {
                        Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();  //실패하면 mBodS에 저장된 메시지를 출력.
                    }
                }
            });
            mHttpComm.start(); //mHttpComm 시작
        }
    };
}


//        etEmail = (EditText) findViewById(R.id.etEmail);
//        etPassword = (EditText) findViewById(R.id.etPassword);
//        btnRegist = (Button) findViewById(R.id.btnRegist);
//        btnLogin = (Button)findViewById(R.id.btnLogin);
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 이메일 입력 확인
//                if( etEmail.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Login.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etEmail.requestFocus();
//                    return;
//                }
//
//                // 비밀번호 입력 확인
//                if( etPassword.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Login.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etPassword.requestFocus();
//                    return;
//                }
//
//                String email = etEmail.getText().toString();
//                String pwd = etPassword.getText().toString();
//
//                Activity_Spre mSpre = new Activity_Spre(Activity_Login.this);
//                String saveEamil =mSpre.getPreferences("email");
//                String savePwd =mSpre.getPreferences("pwd");
//                Log.e("gm","email"+email);
//                Log.e("gm","email"+saveEamil);
//                Log.e("gm","pwd"+pwd);
//                Log.e("gm","savePwd"+savePwd);
//                if(!email.equals(saveEamil)){
//                    Toast.makeText(Activity_Login.this, "등록되어 있는 메일이 아닙니다.", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(!pwd.equals(savePwd)){
//                    Toast.makeText(Activity_Login.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
//                    return;

//                }
//                Intent intent = new Intent(Activity_Login.this, Activity_Main.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btnRegist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Activity_Signup.class);
//
//                // SINGLE_TOP : 이미 만들어진게 있으면 그걸 쓰고, 없으면 만들어서 써라
//                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//
//                // 동시에 사용 가능
//                // intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                // intent를 보내면서 다음 액티비티로부터 데이터를 받기 위해 식별번호(1000)을 준다.
//                startActivityForResult(intent, 1000);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // setResult를 통해 받아온 요청번호, 상태, 데이터f
//        Log.d("RESULT", requestCode + "");
//        Log.d("RESULT", resultCode + "");
//        Log.d("RESULT", data + "");
//
//        if (requestCode == 1000 && resultCode == RESULT_OK) {
//            Toast.makeText(Activity_Login.this, "회원가입을 완료했습니다!", Toast.LENGTH_SHORT).show();
//            etEmail.setText(data.getStringExtra("email"));
//        }
//    }
//
//    final HttpComm mHttpComm = new HttpComm(mContext);
//    mHttpComm.setUrl(HttpURL.UserLogin);
//    mHttpComm.setQeryJO(mDataJO);
//    mHttpComm.setRunnable(new Runnable() {
//        @Override
//        public void run() {
//            if(mHttpComm.isSuccess()) {
//                Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                ActivityLogin.this.setResult(LOGIN_RESULT_SUCCESS, intent);//RESULT_OK를 돌려주면 MainActivity 에서 받는다.
//                finish();
//            }else{
//                Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
//            }
//        }
//    });
//    mHttpComm.start();
//}


