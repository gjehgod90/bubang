package dohaeng.com.bubang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * Created by HeoDH on 2016-07-10.
 */
public class Activity_Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main1, menu);
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
