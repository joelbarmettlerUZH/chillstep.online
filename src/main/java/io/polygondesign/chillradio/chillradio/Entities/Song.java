package io.polygondesign.chillradio.chillradio.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;


@Embeddable
public class Song {

    public String url;
    public String artist;
    public String songname;
    public String creditsUrl;

    public float length;
    public int currentTime;

    public Song(String url, String artist, String songname, String creditsUrl, float length){
        this.url = url;
        this.artist = artist;
        this.songname = songname;
        this.creditsUrl = creditsUrl;
        this.length = length;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public int getCurrentTime(){
        return this.currentTime;
    }

    public float getLength(){
        return this.length;
    }
}
