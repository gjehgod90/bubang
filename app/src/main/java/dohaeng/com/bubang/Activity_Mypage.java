package dohaeng.com.bubang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by HeoDH on 2016-07-25.
 */
public class Activity_Mypage extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        TabHost();
    }

    private void TabHost() {
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabSpec spec1 = tabHost.newTabSpec("Tab1").setContent(R.id.tab1)
                .setIndicator(getString(R.string.tab1));
        tabHost.addTab(spec1);

        TabSpec spec2 = tabHost.newTabSpec("Tab2").setContent(R.id.tab2)
                .setIndicator(getString(R.string.tab2));
        tabHost.addTab(spec2);

        tabHost.getTabWidget().getChildAt(0).getLayoutParams().height = 80;
        tabHost.getTabWidget().getChildAt(1).getLayoutParams().height = 80;

        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout r11 = (LinearLayout)tabHost.getTabWidget().getChildAt(0);
        r11.setGravity(Gravity.CENTER_VERTICAL);
        TextView tv1 = (TextView) r11.getChildAt(1);
        tv1.setLayoutParams(tvParams);
        tv1.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        tv1.setPadding(10, 0, 10, 0);
        tv1.setGravity(Gravity.CENTER);

        LinearLayout r12 = (LinearLayout) tabHost.getTabWidget()
                .getChildAt(1);
        r12.setGravity(Gravity.CENTER_VERTICAL);
        TextView tv2 = (TextView) r12.getChildAt(1);
        tv2.setLayoutParams(tvParams);
        tv2.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        tv2.setPadding(10, 0, 10, 0);
        tv2.setGravity(Gravity.CENTER);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Activity_Mypage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://dohaeng.com.bubang/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Activity_Mypage Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://dohaeng.com.bubang/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.menu_home:
                //액티비티를 새로 생성하지 않고, 이전 메인 액티비티로 돌아가야한다.
//                Intent intent = new Intent(this, Activity_Main.class);
//                startActivity(intent);
                finish();
                Toast.makeText(this, "홈 버튼이 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_mypage:
                Intent intent1 = new Intent(this, Activity_Mypage.class);
                startActivity(intent1);
                finish();
                Toast.makeText(this, "마이 페이지가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_logout:
                Intent intent2 = new Intent(this, Activity_Login.class);
                startActivity(intent2);
                finish();
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

