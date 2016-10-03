package dohaeng.com.bubang;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Main extends AppCompatActivity {
//    private LayoutInflater inflater = null;

    private Button btnInfor;
    private Button BtnEnroll;

    private ArrayList<Activity_InfoClass> mCareList = null;
    private ListView mItemLV = null;

    private int imageList[] = {
        R.drawable.image, R.drawable.menu_settings, R.drawable.splash
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        btnInfor = (Button) findViewById(R.id.btnInfor);
        btnInfor.setOnClickListener(mClickListener);

        BtnEnroll = (Button) findViewById(R.id.BtnEnroll);
        BtnEnroll.setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnInfor) {
                startActivity(new Intent(Activity_Main.this, Activity_Information.class));
            }
            if (view.getId() == R.id.BtnEnroll) {
                startActivity(new Intent(Activity_Main.this, Activity_Enrollment.class));
                finish();
            }
            btnInfor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            BtnEnroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    };

    public void initUI(){
        mItemLV = (ListView) findViewById(R.id.list);
//
        mCareList = new ArrayList<Activity_InfoClass>();

        for(int i = 0 ; i < 12 ; i++){
            mCareList.add(new Activity_InfoClass(getResources().getDrawable(imageList[i%3]),i + "번째" + " 매물 입니다."));
        }

        mItemLV.setAdapter(new Activity_CustomBaseAdapter(this, mCareList));
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {

            Toast.makeText(
                    getApplicationContext(),
                    "ITEM CLICK = " + position,
                    Toast.LENGTH_SHORT
            ).show();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
//            case R.id.menu_home:
//                Intent intent = new Intent(this, Activity_Main.class);
//                startActivity(intent);
//                finish();
//                Toast.makeText(this, "홈 버튼이 선택되었습니다.", Toast.LENGTH_SHORT).show();
//                break;
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

//    ListView listView = (ListView) findViewById(R.id.list);
//        this.inflater = LayoutInflater.from(this);
//        listView.setAdapter(new BaseAdapter() {
//            private Context mContext = null;
//
//            @Override
//            public int getCount() {
//                return 12;
//            }
//
//            @Override
//            public Object getItem(int i) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int i) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int i, View view, ViewGroup viewGroup) {
//                View v = view;
//                if (inflater == null)
//                    Log.e("gm", "null");
//                else Log.e("gm", "not null");
//                v = inflater.inflate(R.layout.activity_cell, null);
//                return v;
//            }
//        });
//    }