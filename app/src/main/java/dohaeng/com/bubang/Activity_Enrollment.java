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
import android.provider.Settings;
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
import android.widget.TextView;
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
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.nmapmodel.NMapPlacemark;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by HeoDH on 2016-07-23.
 */
public class Activity_Enrollment extends NMapActivity implements RadioGroup.OnCheckedChangeListener {

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
    RadioGroup etType;
    RadioButton etMr;
    RadioButton etDps;
    RadioButton etSP;
    ImageView imgView;
    LinearLayout monthly;
    LinearLayout charter;
    LinearLayout Trading;
    String img;
    private RadioButton radioBT;
    private RadioButton radioBT1;
    private EditText textBT;

//    ImageView etImg;


    private NMapOverlayManager mOverlayManager;
    private NMapMyLocationOverlay mMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCompassManager;
    private NMapViewerResourceProvider mMapViewerResourceProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_enrollment);


//        getSupportActionBar().setTitle("방 등록");

        monthly = (LinearLayout) findViewById(R.id.monthly);
        charter = (LinearLayout) findViewById(R.id.charter);
        Trading = (LinearLayout) findViewById(R.id.Trading);


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
        etMr = (RadioButton) findViewById(R.id.etMr);
        etDps = (RadioButton) findViewById(R.id.etDps);
        etSP = (RadioButton) findViewById(R.id.etSp);
        etType = (RadioGroup) findViewById(R.id.etType);
        imgView = (ImageView) findViewById(R.id.imgView);

        findViewById(R.id.monthly).setVisibility(View.GONE);
        findViewById(R.id.charter).setVisibility(View.GONE);
        findViewById(R.id.Trading).setVisibility(View.GONE);

        RadioGroup etType = (RadioGroup) findViewById(R.id.etType);
        etType.setOnCheckedChangeListener(this);


        btnDialog = (Button) findViewById(R.id.btnDialog);
        btnDialog.setOnClickListener(mClickListener);

        buttonLP = (Button) findViewById(R.id.buttonLoadPicture);
        mViewIV = (ImageView) findViewById(R.id.imgView);
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

        mLinearLayout = (LinearLayout) findViewById(R.id.mapmap);
        mLinearLayout.addView(mMapView);

        super.setMapDataProviderListener(onDataProviderListener);
//        mMapView.setOnMapStateChangeListener(this);
        // 지도에 대한 상태 변경 이벤트 연결

        // create resource provider
        mMapViewerResourceProvider = new NMapViewerResourceProvider(this);
        mOverlayManager = new NMapOverlayManager(this, mMapView, mMapViewerResourceProvider);

        // location manager
        mMapLocationManager = new NMapLocationManager(this);
        mMapLocationManager.setOnLocationChangeListener(onMyLocationChangeListener);

        // compass manager
        mMapCompassManager = new NMapCompassManager(this);

        // create my location overlay
        mMyLocationOverlay = mOverlayManager.createMyLocationOverlay(mMapLocationManager, mMapCompassManager);

        startMyLocation();

    }

    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {
        @Override
        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
            if (errorInfo == null) { // success
                startMyLocation();//현재위치로 이동
                mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 14);
            } else { // fail
                android.util.Log.e("NMAP", "onMapInitHandler: error=" + errorInfo.toString());
            }
        }

        @Override
        public void onMapCenterChange(NMapView nMapView, NGeoPoint nGeoPoint) {

        }

        @Override
        public void onMapCenterChangeFine(NMapView nMapView) {

        }

        @Override
        public void onZoomLevelChange(NMapView nMapView, int i) {

        }

        @Override
        public void onAnimationStateChange(NMapView nMapView, int i, int i1) {

        }
    };

    private void startMyLocation() {
        boolean isMyLocationEnabled = mMapLocationManager.enableMyLocation(true);
        if (!isMyLocationEnabled) {
            Toast.makeText(
                    Activity_Enrollment.this,
                    "시스템에서 GPS설정을 활성화 하세요.", Toast.LENGTH_LONG).show();

            Intent goToSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(goToSettings);
        } else {

        }
    }

    private void stopMyLocation() {
        if (mMyLocationOverlay != null) {
            mMapLocationManager.disableMyLocation();

            if (mMapView.isAutoRotateEnabled()) {
                mMyLocationOverlay.setCompassHeadingVisible(false);

                mMapView.setAutoRotateEnabled(false, false);

                mLinearLayout.requestLayout();
            }
            onPause();
        }
    }

    private final NMapActivity.OnDataProviderListener onDataProviderListener = new NMapActivity.OnDataProviderListener() {

        @Override
        public void onReverseGeocoderResponse(NMapPlacemark placeMark, NMapError errInfo) {

            if (errInfo != null) {
                Log.e("myLog", "Failed to findPlacemarkAtLocation: error=" + errInfo.toString());
                Toast.makeText(Activity_Enrollment.this, errInfo.toString(), Toast.LENGTH_LONG).show();
                return;
            } else {
                Toast.makeText(Activity_Enrollment.this, placeMark.toString(), Toast.LENGTH_LONG).show();

            }
        }
    };


    private final NMapLocationManager.OnLocationChangeListener onMyLocationChangeListener = new NMapLocationManager.OnLocationChangeListener() {

        @Override
        public boolean onLocationChanged(NMapLocationManager locationManager, NGeoPoint myLocation) {

            if (mMapController != null) {
                mMapController.animateTo(myLocation);
            }
            Log.d("myLog", "myLocation  lat " + myLocation.getLatitude());
            Log.d("myLog", "myLocation  lng " + myLocation.getLongitude());

//            findPlacemarkAtLocation(myLocation.getLongitude(), myLocation.getLatitude());
            //위도경도를 주소로 변환
            return true;
        }

        @Override
        public void onLocationUpdateTimeout(NMapLocationManager locationManager) {

            Toast.makeText(Activity_Enrollment.this, "현재위치를 검색 할 수 없습니다.",
                    Toast.LENGTH_LONG).show();

        }

        @Override
        public void onLocationUnavailableArea(NMapLocationManager locationManager, NGeoPoint myLocation) {

            Toast.makeText(Activity_Enrollment.this,
                    "현재위치는 지도상에 표시할 수 없는 지역입니다.", Toast.LENGTH_LONG).show();
            stopMyLocation();
        }
    };


    @Override
    public void onCheckedChanged(RadioGroup arg0, int arg1) {
        // TODO Auto-generated method stub
        switch (arg1) {
            case R.id.etMr:
                monthly.setVisibility(LinearLayout.VISIBLE);
                charter.setVisibility(LinearLayout.VISIBLE);
                Trading.setVisibility(LinearLayout.GONE);
                break;
            case R.id.etDps:
                monthly.setVisibility(LinearLayout.VISIBLE);
                charter.setVisibility(LinearLayout.GONE);
                Trading.setVisibility(LinearLayout.GONE);
                break;
            case R.id.etSp:
                monthly.setVisibility(LinearLayout.GONE);
                charter.setVisibility(LinearLayout.GONE);
                Trading.setVisibility(LinearLayout.VISIBLE);
                break;

        }
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
                Log.e("gm", imgDecodableString); // /storage/emulated/0/DCIM/Screenshots/Screenshot_20160921-013909.png

                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

                img = getStringFromBitmap(BitmapFactory.decodeFile(imgDecodableString));

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
        Log.e("dh", "encodedImage:\n" + encodedImage);
        return encodedImage;
    }


    public void EnollmentUser() {

        final JSONObject mDataJO = new JSONObject();

        String adress = etmapname.getText().toString();
        String adress_descript = etadress.getText().toString();
        String roomInfo = etroomInfo.getText().toString();
        String described = etdescribed.getText().toString();
        int ug_include = etRgrp.getCheckedRadioButtonId();
        int Type = etType.getCheckedRadioButtonId();
        radioBT = (RadioButton) findViewById(ug_include);
        radioBT1 = (RadioButton) findViewById(Type);
        int deposit = Integer.parseInt(etPrice.getText().toString());
        int monthly_rent = Integer.parseInt(etmonthly_rent.getText().toString());
        int acreage = Integer.parseInt(etacreage.getText().toString());

        try {
            mDataJO.put("adress", adress);
            mDataJO.put("adress_descript", adress_descript);
            mDataJO.put("deposit", deposit);
            mDataJO.put("roomInfo", roomInfo);
            mDataJO.put("described", described);
            mDataJO.put("monthly_rent", monthly_rent);
            mDataJO.put("acreage", acreage);
            mDataJO.put("ug_include", radioBT.getText());
            mDataJO.put("Type", radioBT1.getText());
            mDataJO.put("img", img);
            mDataJO.put("key", "안양시 동안구 부흥동 은하수청구");
            Log.e("dh", "server req:\n" + mDataJO.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final HttpComm mHttpComm = new HttpComm(mContext);
        //서버 통신에 필요한 url 셋팅
//        mHttpComm.setUrl(HttpURL.UserEnrollment);
        mHttpComm.setUrl(HttpURL.Nmapaddress);
        //서버 통신에 필요한 데이터
        mHttpComm.setQeryJO(mDataJO);
        //서버 통신 후 할일을 등록
        mHttpComm.setRunnable(new Runnable() {
            @Override
            public void run() {
                // 성공 여부 확인
                if (mHttpComm.isSuccess())
                {
                    //성공시
                    Toast.makeText(mContext, R.string.desc_success, Toast.LENGTH_SHORT).show();
                    Activity_Enrollment.this.finish();
                    Log.e("ddhdh", "server req:\n" + mDataJO.toString());
                } else {
                    //실패시
                    Toast.makeText(mContext, mHttpComm.mBodyS, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mHttpComm.start();
    }
}


