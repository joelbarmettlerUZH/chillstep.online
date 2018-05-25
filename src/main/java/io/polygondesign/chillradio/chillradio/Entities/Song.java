package io.polygondesign.chillradio.chillradio.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Embeddable;


@Embeddable
public class Song {

    private String url;
    private String artist;
    private String songname;
    private String creditsUrl;

    private float length;
    private int currentTime;

    private static final String baseUrl = "//s3.eu-central-1.amazonaws.com/online.chillstep.s3/";

    public Song(String url, String artist, String songname, String creditsUrl, float length){
        this.url = baseUrl + url;
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

    public String getUrl() {
        return url;
    }


    public String getArtist() {
        return artist;
    }


    public String getSongname() {
        return songname;
    }


    public String getCreditsUrl() {
        return creditsUrl;
    }


}
