//package dohaeng.com.bubang;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//import android.widget.ImageView;
//import android.provider.MediaStore;
//import android.database.Cursor;
//
///**
// * Created by HeoDH on 2016-07-23.
// */
//public class Activity_Enrollment2 extends AppCompatActivity {
//    private static int RESULT_LOAD_IMG = 1;
//    String imgDecodableString;
//
//    Button buttonLP;
//    private ImageView mViewIV;
//    private Button btnNext2;
//
//    @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activitiy_enrollment2);
//            getSupportActionBar().setTitle("방 등록");
//
//
//        btnNext2 = (Button) findViewById(R.id.btnNext2);
//        btnNext2.setOnClickListener(mClickListener);
//
//            buttonLP = (Button) findViewById(R.id.buttonLoadPicture);
//            mViewIV = (ImageView)findViewById(R.id.imgView);
//            buttonLP.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    loadImagefromGallery(mViewIV);
//
//                }
//            });
////            findViewById(R.id.buttonLoadPicture).setOnClickListener(this);
//        }
//
//    View.OnClickListener mClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (view.getId() == R.id.btnNext2) {
//                startActivity(new Intent(Activity_Enrollment2.this, Activity_Enrollment3.class));
//            }
//        }
//    };
//
//
//        public void loadImagefromGallery(View view) {
//            // Create intent to Open Image applications like Gallery, Google Photos
//            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            // Start the Intent
//            galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
//        }
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            try {
//                // When an Image is picked
//                if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
//                        && null != data) {
//                    // Get the Image from data
//
//                    Uri selectedImage = data.getData();
//                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                    // Get the cursor
//                    Cursor cursor = getContentResolver().query(selectedImage,
//                            filePathColumn, null, null, null);
//                    // Move to first row
//                    cursor.moveToFirst();
//
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    imgDecodableString = cursor.getString(columnIndex);
//                    cursor.close();
//                    ImageView imgView = (ImageView) findViewById(R.id.imgView);
//                    // Set the Image in ImageView after decoding the String
//                    imgView.setImageBitmap(BitmapFactory
//                            .decodeFile(imgDecodableString));
//
//                } else {
//                    Toast.makeText(this, "You haven't picked Image",
//                            Toast.LENGTH_LONG).show();
//                }
//            } catch (Exception e) {
//                Log.e("gm",e.toString());
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
//                        .show();
//            }
//
//            btnNext2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }
//}

