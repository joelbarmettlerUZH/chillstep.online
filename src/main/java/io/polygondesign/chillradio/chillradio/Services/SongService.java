package io.polygondesign.chillradio.chillradio.Services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.polygondesign.chillradio.chillradio.Entities.Song;
import io.polygondesign.chillradio.chillradio.Songmanager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private Thread t1;
    private Songmanager songmanager = new Songmanager();

    @Autowired
    public void init(){
        System.out.println("Starting songmanager");
        t1 = new Thread(songmanager);
        t1.start();
    }

    public Song getCurrentSong(){
        return songmanager.getCurrent();
    }
}