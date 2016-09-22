package com.example.yufeng.musicplayer;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileFilter;

public class MainActivity extends Activity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView)findViewById(R.id.list);
        initialList();
    }

    private void initialList() {
        String path = Environment.getExternalStorageDirectory() + "/Music";
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        File dir = new File(path);
        Log.d("DEBUG", path);
        File[] musics = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                // TODO: check file type
                return pathname.isFile();
            }
        });
        Log.d("DEBUG", Integer.toString(musics.length));
        String names[] = new String[musics.length];
        for(int i = 0; i < musics.length; i ++) {
            mmr.setDataSource(musics[i].getPath());
            names[i] = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, names);
        list.setAdapter(adapter);
    }
}
