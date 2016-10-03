//package dohaeng.com.bubang;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by HeoDH on 2016-07-23.
// */
//public class Activity_Enrollment3 extends AppCompatActivity {
//
//    private Button btnNext3;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activitiy_enrollment3);
//
//        getSupportActionBar().setTitle("방 등록");
//
//        btnNext3 = (Button) findViewById(R.id.btnNext3);
//        btnNext3.setOnClickListener(mClickListener);
//    }
//
//    View.OnClickListener mClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (view.getId() == R.id.btnNext3) {
//                startActivity(new Intent(Activity_Enrollment3.this, Activity_Enrollment4.class));
//            }
//            btnNext3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
//    };
//}

