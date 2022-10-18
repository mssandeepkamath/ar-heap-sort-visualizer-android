package com.example.sceneformar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Sceneform;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.sceneform.ux.VideoNode;

import java.util.ArrayList;
import java.util.List;


public class NewArActivity extends AppCompatActivity implements
        FragmentOnAttachListener,
        BaseArFragment.OnTapArPlaneListener {

    private final List<MediaPlayer> mediaPlayers = new ArrayList<>();
    private ArFragment arFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_ar);
        getSupportFragmentManager().addFragmentOnAttachListener(this);

        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.arFragment, ArFragment.class, null)
                        .commit();
            }
        }
    }

    @Override
    public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if (fragment.getId() == R.id.arFragment) {
            arFragment = (ArFragment) fragment;
            arFragment.setOnTapArPlaneListener(this);
        }
    }



    @Override
    protected void onStart() {
        super.onStart();

        for (MediaPlayer mediaPlayer : this.mediaPlayers) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        for (MediaPlayer mediaPlayer : this.mediaPlayers) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (MediaPlayer mediaPlayer : this.mediaPlayers) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    @Override
    public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
        // Create the Anchor.
        Anchor anchor = hitResult.createAnchor();
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        // Create the transformable model and add it to the anchor.
        TransformableNode modelNode = new TransformableNode(arFragment.getTransformationSystem());
        modelNode.setParent(anchorNode);

        final int rawResId;
        final Color chromaKeyColor;
        rawResId = R.raw.heapvideo;
        chromaKeyColor = null;
        MediaPlayer player = MediaPlayer.create(this, rawResId);
        player.setLooping(true);
        player.start();
        mediaPlayers.add(player);
        VideoNode videoNode = new VideoNode(this, player, chromaKeyColor, new VideoNode.Listener() {
            @Override
            public void onCreated(VideoNode videoNode) {

                Toast.makeText(NewArActivity.this, "hey success", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Throwable throwable) {
                Toast.makeText(NewArActivity.this, "Unable to load material", Toast.LENGTH_LONG).show();
            }
        });
        videoNode.setParent(modelNode);

        // If you want that the VideoNode is always looking to the
        // Camera (You) comment the next line out. Use it mainly
        // if you want to display a Video. The use with activated
        // ChromaKey might look odd.
        //videoNode.setRotateAlwaysToCamera(true);

        modelNode.select();
    }
}