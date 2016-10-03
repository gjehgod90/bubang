package dohaeng.com.bubang;

/**
 * Created by user on 2016-09-08.
 */
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
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;


public class Activity_Nmap extends NMapActivity {

    private static final String API_KEY = "1EM5zbdBJvrJ5d5E1aP7";
    private NMapView mMapView;
    private NMapController mMapController;
    private static boolean USE_XML_LAYOUT = false;
    private LinearLayout mLinearLayout;
    LinearLayout MapContainer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        MapContainer = (LinearLayout) findViewById(R.id.testampmap);

//        if (USE_XML_LAYOUT) {
//            setContentView(R.layout.activity_map);
//
////            mMapView = (NMapView) findViewById(R.id.mapView);
//        }


            //네이버 지도 MapView를 생성해준다.
            mMapView = new NMapView(this);
            //지도에서 ZoomControll을 보여준다
            mMapView.setBuiltInZoomControls(true, null);
            //네이버 OPEN API 사이트에서 할당받은 KEY를 입력하여 준다.
            mMapView.setApiKey(API_KEY);
            //이 페이지의 레이아웃을 네이버 MapView로 설정해준다.
//            setContentView(mMapView);
            setContentView(R.layout.activity_map);
            mLinearLayout = (LinearLayout)findViewById(R.id.mapmap);
            mLinearLayout.addView(mMapView);
            //네이버 지도의 클릭이벤트를 처리 하도록한다.
            mMapView.setClickable(true);
            //맵의 상태가 변할때 이메소드를 탄다.
            mMapView.setOnMapStateChangeListener(new NMapView.OnMapStateChangeListener() {
                public void onZoomLevelChange(NMapView arg0, int arg1) {

                }

                public void onMapInitHandler(NMapView arg0, NMapError arg1) {

                    if (arg1 == null) {
                        //  표시해줄 위치
                        mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 11);
                    } else {
                        Toast.makeText(getApplicationContext(), arg1.toString(), Toast.LENGTH_SHORT).show();
                    }
                }

                public void onMapCenterChangeFine(NMapView arg0) {

                }

                public void onMapCenterChange(NMapView arg0, NGeoPoint arg1) {

                }

                public void onAnimationStateChange(NMapView arg0, int arg1, int arg2) {

                }
            });
            //맵뷰의 이벤트리스너의 정의.
            mMapView.setOnMapViewTouchEventListener(new NMapView.OnMapViewTouchEventListener() {

                public void onTouchDown(NMapView arg0, MotionEvent arg1) {

                }

                @Override
                public void onTouchUp(NMapView nMapView, MotionEvent motionEvent) {

                }

                public void onSingleTapUp(NMapView arg0, MotionEvent arg1) {

                }

                public void onScroll(NMapView arg0, MotionEvent arg1, MotionEvent arg2) {

                }

                public void onLongPressCanceled(NMapView arg0) {

                }

                public void onLongPress(NMapView arg0, MotionEvent arg1) {

                }
            });
            mMapController = mMapView.getMapController();
            super.setMapDataProviderListener(new OnDataProviderListener() {

                public void onReverseGeocoderResponse(NMapPlacemark arg0, NMapError arg1) {

                }
            });
    }
}
