package com.example.tango_dev.read_camera;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.graphics.SurfaceTexture;
import android.view.TextureView.SurfaceTextureListener;
import android.hardware.Camera;
import android.util.Log;
import android.widget.FrameLayout;
import android.view.Gravity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{
    private TextureView my_texture_view;
    private Camera my_camera;
    private String TAG = "mylog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        my_texture_view = new TextureView(this);
        my_texture_view.setSurfaceTextureListener(this);
        setContentView(R.layout.activity_main);
        setContentView(my_texture_view);

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture arg0,int arg1,int arg2){
        my_camera = Camera.open();
        Camera.Size preview_dim = my_camera.getParameters().getPreviewSize();
        my_texture_view.setLayoutParams(new FrameLayout.LayoutParams(preview_dim.width,preview_dim.height, Gravity.CENTER));
        try{
            my_camera.setPreviewTexture(arg0);
        }catch(IOException e){
            e.printStackTrace();
        }
        my_camera.startPreview();
        my_texture_view.setAlpha(1.0f);
//        my_texture_view.setRotation(90.0f); // uncomment for moto-g, comment for google tango
    }
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
        my_camera.stopPreview();
        my_camera.release();
        return true;
    }
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,int arg2) {
    }
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
