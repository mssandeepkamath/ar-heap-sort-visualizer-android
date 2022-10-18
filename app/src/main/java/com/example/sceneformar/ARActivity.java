//package com.example.sceneformar;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//
//import android.app.Activity;
//import android.app.ActivityManager;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.WindowManager;
//import android.widget.Toast;
//import android.view.Window;
//import com.google.ar.core.Anchor;
//import com.google.ar.sceneform.AnchorNode;
//import com.google.ar.sceneform.rendering.ModelRenderable;
//import com.google.ar.sceneform.ux.ArFragment;
//import com.google.ar.sceneform.ux.TransformableNode;
//
//import java.util.HashMap;
//import java.util.Objects;
//
//public class ARActivity extends AppCompatActivity {
//    private String name;
//    private ArFragment arCam;
//
//    // helps to render the 3d model
//    // only once when we tap the screen
//    private int clickNo = 0;
//
//    public static boolean checkSystemSupport(Activity activity) {
//
//        // checking whether the API version of the running Android >= 24
//        // that means Android Nougat 7.0
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            String openGlVersion = ((ActivityManager) Objects.requireNonNull(activity.getSystemService(Context.ACTIVITY_SERVICE))).getDeviceConfigurationInfo().getGlEsVersion();
//
//            // checking whether the OpenGL version >= 3.0
//            if (Double.parseDouble(openGlVersion) >= 3.0) {
//                return true;
//            } else {
//                Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_SHORT).show();
//                activity.finish();
//                return false;
//            }
//        } else {
//            Toast.makeText(activity, "App does not support required Build Version", Toast.LENGTH_SHORT).show();
//            activity.finish();
//            return false;
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Window window = this.getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
//        setContentView(R.layout.activity_ar);
//
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            name = extras.getString("name");
//        }
//        if (checkSystemSupport(this)) {
//
//
//            // ArFragment is linked up with its respective id used in the activity_main.xml
//            arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);
//
//            assert arCam != null;
//            arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
//                clickNo++;
//
//
//
////             the 3d model comes to the scene only
////             when clickNo is one that means once
//                if (clickNo == 1) {
//                    Anchor anchor = hitResult.createAnchor();
//                    ModelRenderable.builder()
//                            .setSource(this,R.raw.heap)
//                            .setIsFilamentGltf(true)
//                            .build()
//                            .thenAccept(modelRenderable -> addModel(anchor, modelRenderable))
//                            .exceptionally(throwable -> {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                                builder.setMessage("Something is not right" + throwable.getMessage()).show();
//                                return null;
//                            });
//                }
//            });
//        }
//
//        }
//    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {
//
//     // Creating a AnchorNode with a specific anchor
//        AnchorNode anchorNode = new AnchorNode(anchor);
//;
//      // attaching the anchorNode with the ArFragment
//        anchorNode.setParent(arCam.getArSceneView().getScene());
//
//     // attaching the anchorNode with the TransformableNode
//        TransformableNode model = new TransformableNode(arCam.getTransformationSystem());
//        model.setParent(anchorNode);
//       // attaching the 3d model with the TransformableNode
//       // that is already attached with the node
//       model.setRenderable(modelRenderable);
//        model.select();
//    }
//
//
//}
//
