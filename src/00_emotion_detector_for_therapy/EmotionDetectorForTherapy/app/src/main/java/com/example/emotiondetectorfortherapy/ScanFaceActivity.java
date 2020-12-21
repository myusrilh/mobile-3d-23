package com.example.emotiondetectorfortherapy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ScanFaceActivity extends AppCompatActivity {

    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private Executor executor = Executors.newSingleThreadExecutor();

    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

//    private NavController navController=null;

    private TextView tvHasilScan;
    private ImageView homeBtn;
    private ImageView medicalBtn;
    private ImageView profileBtn;
    private ImageView cameraBtn;

//    private ImageCapture imageCapture = null;

    private GraphicOverlay mGraphicOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_face);


        previewView = findViewById(R.id.frame_scan_face);
        tvHasilScan = findViewById(R.id.hasil_scan_face);

        homeBtn = findViewById(R.id.home_button_scan_face);
        medicalBtn = findViewById(R.id.medical_report_button_scan_face);
        profileBtn = findViewById(R.id.profile_button_scan_face);

        cameraBtn = findViewById(R.id.camera_icon_scan_face);

        mGraphicOverlay = findViewById(R.id.graphic_overlay);

//        final NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_main);

        Intent getIntent = getIntent();
        Bundle b = getIntent.getExtras();

        assert b != null;
        if (b.getBoolean("startCamera")) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                ActivityCompat.requestPermissions(
                        this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
//                Toast.makeText(getApplicationContext(), "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
//                finish();
            }
        }

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("page", "home");
                startActivity(i);
            }
        });
        medicalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("page", "medical");
                startActivity(i);
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("page", "profile");
                startActivity(i);
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageCapture imageCapture = new ImageCapture.Builder().build();
                imageCapture.takePicture(executor, new ImageCapture.OnImageCapturedCallback() {
                    @Override
                    @ExperimentalGetImage
                    public void onCaptureSuccess(@NonNull ImageProxy image) {
                        Image mediaImage = image.getImage();
                        if (mediaImage != null) {
                            InputImage inputImage = InputImage.fromMediaImage(mediaImage, 0);
//                            InputImage inputImage = InputImage.fromMediaImage(mediaImage,image.getImageInfo().getRotationDegrees());

//                    Detect image from camera
                            detectImage(inputImage);

                        }

                        image.close();
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {

                    }
                });

            }
        });

    }

    public void detectImage(InputImage image) {
        FaceDetectorOptions highAccuracyOpts =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                        .build();

        FaceDetectorOptions realTimeOpts =
                new FaceDetectorOptions.Builder()
                        .setContourMode(FaceDetectorOptions.CONTOUR_MODE_ALL)
                        .build();

        FaceDetector detector = FaceDetection.getClient(highAccuracyOpts);

        Task<List<Face>> result =
                detector.process(image).addOnSuccessListener(new OnSuccessListener<List<Face>>() {
                    @Override
                    public void onSuccess(List<Face> faces) {

                        if (faces.size() == 0){
                            Toast.makeText(getApplicationContext(),"No Face Found",Toast.LENGTH_LONG).show();
                        }


                            mGraphicOverlay.clear();
                        for(Face face : faces){
//                            for (int i = 0; i < faces.size(); ++i) {
//                                Face face = faces.get(i);
                                Toast.makeText(getApplicationContext(),"Face found",Toast.LENGTH_SHORT).show();

                                StringBuilder stringBuilder = new StringBuilder();

                                FaceContourGraphic faceGraphic = new FaceContourGraphic(mGraphicOverlay);
                                mGraphicOverlay.add(faceGraphic);
                                faceGraphic.updateFace(face);
//                            }

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Error Detect", Objects.requireNonNull(e.getMessage()));
                    }
                });


    }

    private boolean allPermissionsGranted() {

        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(getApplicationContext(), "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindImageAnalysis(cameraProvider);

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }, ContextCompat.getMainExecutor(this));
    }


    private void bindImageAnalysis(@NonNull ProcessCameraProvider cameraProvider) {
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setTargetResolution(new Size(1280, 720))
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), new ImageAnalysis.Analyzer() {
            @Override
            @ExperimentalGetImage
            public void analyze(@NonNull ImageProxy image) {
                Image mediaImage = image.getImage();
                if (mediaImage != null) {
                    InputImage inputImage = InputImage.fromMediaImage(mediaImage, 0);
//                    InputImage inputImage = InputImage.fromMediaImage(mediaImage,image.getImageInfo().getRotationDegrees());

//                    Detect image from camera
                    detectImage(inputImage);

                }

                image.close();

            }
        });

        OrientationEventListener orientationEventListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                int rotation;

                if (orientation >= 45 && orientation < 135) {
                    rotation = Surface.ROTATION_270;
                } else if (orientation >= 135 && orientation < 225) {
                    rotation = Surface.ROTATION_180;
                } else if (orientation >= 225 && orientation < 315) {
                    rotation = Surface.ROTATION_90;
                } else {
                    rotation = Surface.ROTATION_0;
                }

                imageAnalysis.setTargetRotation(rotation);

//                Toast.makeText(getApplicationContext(), "Orientation : " + orientation, Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), "Rotation : "+ Integer.toString(rotation), Toast.LENGTH_LONG).show();
            }
        };
        orientationEventListener.enable();

        Preview preview = new Preview.Builder()
                .setTargetRotation(Surface.ROTATION_270)
                .build();
//        Toast.makeText(getApplicationContext(), "Preview rotation : "+ preview.getTargetRotation(), Toast.LENGTH_LONG).show();

        CameraSelector cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;

//                new CameraSelector.Builder()
//                .requireLensFacing(CameraSelector.LENS_FACING_FRONT).build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        cameraProvider.unbindAll();
        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, imageAnalysis, preview);

    }

}