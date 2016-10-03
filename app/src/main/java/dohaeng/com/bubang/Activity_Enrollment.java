package dohaeng.com.bubang;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import dohaeng.com.bubang.utils.HttpComm;
import dohaeng.com.bubang.utils.HttpURL;
import dohaeng.com.bubang.utils.utils;
import dohaeng.com.bubang.R;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by HeoDH on 2016-07-23.
 */
public class Activity_Enrollment extends NMapActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    Button buttonLP;
    private ImageView mViewIV;
    Context mContext;
    private Button btnDialog;


    private static final String API_KEY = "1EM5zbdBJvrJ5d5E1aP7";
    private NMapView mMapView;
    private NMapController mMapController;
    private static boolean USE_XML_LAYOUT = false;
    private LinearLayout mLinearLayout;

    EditText etmapname;
    EditText etadress;
    EditText etPrice;
    EditText etroomInfo;
    EditText etmonthly_rent;
    EditText etacreage;
    EditText etdescribed;
    RadioButton etRb;
    RadioButton etRb1;
    RadioGroup etRgrp;
    ImageView imgView;
    String img;
    private RadioButton radioBT;
//    ImageView etImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_enrollment);

//        getSupportActionBar().setTitle("방 등록");

        etmapname = (EditText) findViewById(R.id.etmapname);
        etadress = (EditText) findViewById(R.id.etadress);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etroomInfo = (EditText) findViewById(R.id.etroomInfo);
        etmonthly_rent = (EditText) findViewById(R.id.etmonthly_rent);
        etacreage = (EditText) findViewById(R.id.etacreage);
        etdescribed = (EditText) findViewById(R.id.etdescribed);
        etRb = (RadioButton) findViewById(R.id.etRb);
        etRb1 = (RadioButton) findViewById(R.id.etRb1);
        etRgrp = (RadioGroup) findViewById(R.id.etRgrp);
        imgView = (ImageView) findViewById(R.id.imgView);


        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(mClickListener);

        buttonLP = (Button) findViewById(R.id.buttonLoadPicture);
        mViewIV = (ImageView)findViewById(R.id.imgView);
        buttonLP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagefromGallery(mViewIV);
            }
        });

        mMapView = new NMapView(this);
        mMapView.setBuiltInZoomControls(true, null);
        mMapView.setClickable(true);
        mMapView.setApiKey(API_KEY);

        mLinearLayout = (LinearLayout)findViewById(R.id.mapmap);
        mLinearLayout.addView(mMapView);

    }

    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정
        try {
                addr = coder.getFromLocationName(address, 5);
            } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
                }
            }
        return loc;
        }


    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnDialog) {
                EnollmentUser();
                startActivity(new Intent(Activity_Enrollment.this, Activity_BackDialog.class));
                finish();
            }
            btnDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    };

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                Log.e("gm",imgDecodableString); // /storage/emulated/0/DCIM/Screenshots/Screenshot_20160921-013909.png

                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

                img =getStringFromBitmap(BitmapFactory.decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("gm", e.toString());
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private String getStringFromBitmap(Bitmap bitmapPicture) {
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        Log.e("dh","encodedImage:\n"+encodedImage);
        return encodedImage;
    }





    public void EnollmentUser() {

            JSONObject mDataJO = new JSONObject();

            String adress = etmapname.getText().toString();
            String adress_descript = etadress.getText().toString();
            String roomInfo = etroomInfo.getText().toString();
            String described = etdescribed.getText().toString();
            int ug_include = etRgrp.getCheckedRadioButtonId();
            radioBT = (RadioButton) findViewById(ug_include);
            int deposit = Integer.parseInt(etPrice.getText().toString());
            int monthly_rent = Integer.parseInt(etmonthly_rent.getText().toString());
            int acreage = Integer.parseInt(etacreage.getText().toString());

        try {
            mDataJO.put("adress",adress);
            mDataJO.put("adress_descript",adress_descript);
            mDataJO.put("deposit",deposit);
            mDataJO.put("roomInfo",roomInfo);
            mDataJO.put("described",described);
            mDataJO.put("monthly_rent",monthly_rent);
            mDataJO.put("acreage",acreage);
            mDataJO.put("ug_include",radioBT.getText());
            mDataJO.put("img",img);
            Log.e("dh","server req:\n"+mDataJO.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
//        JSONArray mDataAR = new JSONArray();
//
//        Image img = Integer.parseInt(etImg.getimage().toString());
//
//        try {
//            mDataAR.put(img);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        final HttpComm mHttpComm = new HttpComm(mContext);
        //서버 통신에 필요한 url 셋팅
        mHttpComm.setUrl(HttpURL.UserEnrollment);

        //서버 통신에 필요한 데이터
        mHttpComm.setQeryJO(mDataJO);
        //서버 통신 후 할일을 등록
        mHttpComm.setRunnable(new Runnable() {
            @Override
            public void run() {
                // 성공 여부 확인
                if(mHttpComm.isSuccess())

                {
                    //성공시
                    Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();
                    Activity_Enrollment.this.finish();
                }

                else

                {
                    //실패시
                    Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mHttpComm.start();
    }
}


