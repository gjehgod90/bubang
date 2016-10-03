package dohaeng.com.bubang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import dohaeng.com.bubang.utils.HttpComm;
import dohaeng.com.bubang.utils.HttpURL;
import dohaeng.com.bubang.utils.utils;
import dohaeng.com.bubang.R;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by HeoDH on 2016-07-10.
 */
public class Activity_Signup extends AppCompatActivity {

    Context  mContext;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etName;
    private Button btnDone;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mContext = this;
        initUI();
    }

    private void initUI(){
        etPhone       = (EditText) findViewById(R.id.etPhone);
        etEmail    = (EditText) findViewById(R.id.etEmail);
        etName     = (EditText) findViewById(R.id.etName);
        etPassword      = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnDone.setOnClickListener(mClickListener);
        btnCancel.setOnClickListener(mClickListener);

//        TelephonyManager tMgr = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        String mPhoneNumber = tMgr.getLine1Number();
//        if(mPhoneNumber.length() > 0){
//            etPhone.setText(mPhoneNumber);
//        }
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnDone) {
                registerUser();
            }
            if (view.getId() == R.id.btnCancel) {
                finish();
            }
        }
    };


    public void registerUser(){
        JSONObject mDataJO = new JSONObject();
        String PNumber  = etPhone.getText().toString();
        String Email    = etEmail.getText().toString();
        String Name     = etName.getText().toString();

        String Pwd      = etPassword.getText().toString();
        String PwdCheck = etPasswordConfirm.getText().toString();

        if(!Pwd.equals(PwdCheck)){
            Toast.makeText(mContext, R.string.desc_pwd_compare_fails,Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(PNumber.length() > 0)){
            Toast.makeText(mContext, R.string.desc_ph_need,Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(Name.length() > 0)){
            Toast.makeText(mContext, R.string.desc_name_need,Toast.LENGTH_SHORT).show();
            return;
        }

        if(!utils.isEmailValid(Email)){
            Toast.makeText(mContext, R.string.desc_email_check,Toast.LENGTH_SHORT).show();
        }

        try {
            mDataJO.put("phoneNumber",PNumber);
            mDataJO.put("userId",Email);
            mDataJO.put("name",Name);
            mDataJO.put("pwd",Pwd);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        final HttpComm mHttpComm = new HttpComm(mContext);
        //서버 통신에 필요한 url 셋팅
        mHttpComm.setUrl(HttpURL.UserRegist);

        //서버 통신에 필요한 데이터
        mHttpComm.setQeryJO(mDataJO);

        //서버 통신 후 할일을 등록
        mHttpComm.setRunnable(new Runnable() {
            @Override
            public void run() {
                // 성공 여부 확인
                if(mHttpComm.isSuccess()) {
                    //성공시
                    Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();
                    Activity_Signup.this.finish();
                }else{
                    //실패시
                    Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
                }
            }
        });
        mHttpComm.start();


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}


//        etPhone = (EditText) findViewById(R.id.etPhone);
//        etName = (EditText) findViewById(R.id.etName);
//        etEmail = (EditText) findViewById(R.id.etEmail);
//        etPassword = (EditText) findViewById(R.id.etPassword);
//        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
//        btnDone = (Button) findViewById(R.id.btnDone);
//        btnCancel = (Button) findViewById(R.id.btnCancel);

// 비밀번호 일치 검사
//        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String password = etPassword.getText().toString();
//                String confirm = etPasswordConfirm.getText().toString();
//
//                if( password.equals(confirm) ) {
//                    etPassword.setBackgroundColor(Color.GREEN);
//                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
//                } else {
//                    etPassword.setBackgroundColor(Color.RED);
//                    etPasswordConfirm.setBackgroundColor(Color.RED);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        btnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if( etPhone.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Signup.this, "폰번호를 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etPhone.requestFocus();
//                    return;
//                }
//
//
//                if( etName.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Signup.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etName.requestFocus();
//                    return;
//                }
//
//                // 이메일 입력 확인
//                if( etEmail.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Signup.this, "Email을 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etEmail.requestFocus();
//                    return;
//                }
//
//                // 비밀번호 입력 확인
//                if( etPassword.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Signup.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etPassword.requestFocus();
//                    return;
//                }
//
//                // 비밀번호 확인 입력 확인
//                if( etPasswordConfirm.getText().toString().length() == 0 ) {
//                    Toast.makeText(Activity_Signup.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
//                    etPasswordConfirm.requestFocus();
//                    return;
//                }
//
//                // 비밀번호 일치 확인
//                if( !etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ) {
//                    Toast.makeText(Activity_Signup.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
//                    etPassword.setText("");
//                    etPasswordConfirm.setText("");
//                    etPassword.requestFocus();
//                    return;
//                }


//                String Phone = etPhone.getText().toString();
//                String Name = etName.getText().toString();
//                String email = etEmail.getText().toString();
//                String pwd = etPassword.getText().toString();
//
//                Activity_Spre mSpre = new Activity_Spre(Activity_Signup.this);
//               mSpre.savePreferences("name",Name);
//                mSpre.savePreferences("email",email);
//                mSpre.savePreferences("pwd",pwd);
//
//                Intent result = new Intent();
//                result.putExtra("email", etEmail.getText().toString());
//
//                // 자신을 호출한 Activity로 데이터를 보낸다.
//                setResult(RESULT_OK, result);
//                finish();
//            }
//        });