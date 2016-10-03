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
//public class Activity_Enrollment4 extends AppCompatActivity {
//
//    private Button btnDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activitiy_enrollment4);
//
//        getSupportActionBar().setTitle("방 등록");
//
//        btnDialog = (Button) findViewById(R.id.btnDialog);
//        btnDialog.setOnClickListener(mClickListener);
//    }
//    View.OnClickListener mClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (view.getId() == R.id.btnDialog) {
//                startActivity(new Intent(Activity_Enrollment4.this, Activity_BackDialog.class));
//            }
//            btnDialog.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                }
//            });
//        }
//    };
//}
