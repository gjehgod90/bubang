package dohaeng.com.bubang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by HeoDH on 2016-08-01.
 */
public class Activity_BackDialog extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backdialog);

        findViewById(R.id.btnbackHome).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnbackHome:
                this.finish();
                 startActivity(new Intent(Activity_BackDialog.this, Activity_Main.class));
                finish();
                break;
        }
    }
}