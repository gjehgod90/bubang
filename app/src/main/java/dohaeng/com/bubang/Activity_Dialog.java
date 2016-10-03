package dohaeng.com.bubang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by HeoDH on 2016-08-01.
 */
public class Activity_Dialog extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btnClose).setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnClose:
                this.finish();
                break;
        }
    }

    public void onClick1(View view)
    {
        finish();
    }

}
