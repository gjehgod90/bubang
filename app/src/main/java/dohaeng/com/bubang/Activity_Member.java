package dohaeng.com.bubang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by HeoDH on 2016-07-10.
 */
public class Activity_Member extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }
    public void onClick(View view)
    {
        finish();
    }
}