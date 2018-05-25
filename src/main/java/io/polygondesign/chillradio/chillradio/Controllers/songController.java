package io.polygondesign.chillradio.chillradio.Controllers;
import io.polygondesign.chillradio.chillradio.Entities.Song;
import io.polygondesign.chillradio.chillradio.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class songController{
    private final String CONTEXT = "/api/v1/songs";

    @Autowired
    private SongService songService;

    @GetMapping(value = CONTEXT + "/url")
    public String url(){
        return "src/Blackmill-Melody22.mp3";
    }

    @GetMapping(value = CONTEXT)
    public Song currentSong(){
        return songService.getCurrentSong();
    }

    @RequestMapping(value = CONTEXT + "/download")
    public String somePg() {
        return "Blackmill-Melody22.mp3";
    }
}