package dohaeng.com.bubang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by HeoDH on 2016-07-10.
 */
public class Activity_MasterLogin extends AppCompatActivity {

    public void onClick1(View view) {
        Intent intent = new Intent(this, Activity_Member.class);
        startActivity(intent);
    }

    public void onClick2(View view) {
        Intent intent = new Intent(this, Activity_Confirm.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterlogin);
    }
    public void onClick(View view)
    {
        finish();
    }
}
