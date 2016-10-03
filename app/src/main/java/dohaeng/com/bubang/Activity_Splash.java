package dohaeng.com.bubang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by HeoDH on 2016-07-09.
 */
public class Activity_Splash extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceSteate)
    {
        super.onCreate(savedInstanceSteate);

        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        startActivity(new Intent(this, Activity_Login.class));
        finish();
    }
}
