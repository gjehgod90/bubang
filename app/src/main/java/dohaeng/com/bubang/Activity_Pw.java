package dohaeng.com.bubang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by HeoDH on 2016-07-10.
 */
public class Activity_Pw extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pw);
    }

    public void onClick1(View view) {
        Intent intent = new Intent(this, Activity_Dialog.class);
        startActivity(intent);
    }

    public void onClick(View view)
    {
        finish();
    }
}
