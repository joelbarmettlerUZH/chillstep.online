package io.polygondesign.chillradio.chillradio;

import io.polygondesign.chillradio.chillradio.Entities.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Songmanager implements Runnable {

    private List<Song> songs;
    private Song current;

    public Songmanager(){
        this.songs = new ArrayList<>(
                Arrays.asList(
                        new Song("music/RamesesB-GoT.mp3", "Ramases B", "Game of Thrones", "https://soundcloud.com/ramesesb", 238),
                        new Song("music/brokenelegance-reflection.mp3", "Broken Elegance", "Reflection", "https://soundcloud.com/brokenelegance", 295),
                        new Song("music/lavrov-onlytime.mp3", "Lavrov (Enya)", "Only Time", "https://soundcloud.com/lehalavr", 223),
                        new Song("music/brokenelegance-longestroad.mp3", "Broken Elegance", "Longest Road", "https://soundcloud.com/brokenelegance", 190),
                        new Song("music/sentinentpulse-nightwalker.mp3", "Sentinent Pulse", "Nightwalker", "https://soundcloud.com/free-music-house", 247),
                        new Song("music/sentinentpulse-nebularush.mp3", "Sentinent Pulse", "Nebula Rush", "https://soundcloud.com/free-music-house", 245),
                        new Song("music/silencyde-reminisce.mp3", "Silencyde", "Reminisce", "https://soundcloud.com/free-music-house", 247),
                        new Song("music/silencyde-atmos.mp3", "Silencyde", "Atmos", "https://soundcloud.com/free-music-house", 230),
                        new Song("music/BXDN-return.mp3", "BXDN", "Return", "https://soundcloud.com/free-music-house", 251),
                        new Song("music/squegg-summermemories.mp3", "Squegg (Approaching Nirvana)", "Summer's Memories", "https://soundcloud.com/squeggonic", 191),
                        new Song("music/aurora-bluetime.mp3", "Aurora", "Blue Time", "https://soundcloud.com/aurorachillstep", 302),
                        new Song("music/aurora-thoughts.mp3", "Aurora", "Thoughts", "https://soundcloud.com/aurorachillstep", 299),
                        new Song("music/aurora-fromnowhere.mp3", "Aurora", "From Nowhere", "https://soundcloud.com/aurorachillstep", 261),
                        new Song("music/aurora-withyou.mp3", "Aurora", "With You", "https://soundcloud.com/aurorachillstep", 240),
                        new Song("music/Aurora-solace.mp3", "Aurora", "Solace", "https://soundcloud.com/aurorachillstep", 341),
                        new Song("music/aurora-mountainsaremelting.mp3", "Aurora", "Mountains Are Melting", "https://soundcloud.com/aurorachillstep", 337),
                        new Song("music/sick-alliwant.mp3", "Sick Mind (Sarah Blasko)", "All I Want", "https://soundcloud.com/sickmindsmedia", 349),
                        new Song("music/aurora-only.mp3", "Aurora", "Only", "https://soundcloud.com/aurorachillstep", 230),
                        new Song("music/aurora-bygone.mp3", "Aurora", "Bygone", "https://soundcloud.com/aurorachillstep", 255),
                        new Song("music/CMA-Free.mp3", "CMA", "You're Free", "https://soundcloud.com/cma-music", 362),
                        new Song("music/CMA-Silk.mp3", "CMA (Aiva)", "Silk", "https://soundcloud.com/cma-music", 271),
                        new Song("music/CMA-ISee.mp3", "CMA", "I See", "https://soundcloud.com/cma-music", 289),
                        new Song("music/CMA-ChasingDreams.mp3", "CMA", "Chasing Dreams", "https://soundcloud.com/cma-music", 309),
                        new Song("music/RamesesB-Moonlight.mp3", "Ramases B", "Moonlight", "https://soundcloud.com/ramesesb", 343),
                        new Song("music/RamesesB-SadTheme.mp3", "Ramases B", "Sad Theme", "https://soundcloud.com/ramesesb", 92),
                        new Song("music/RamesesB-LoneWanderer.mp3", "Ramases B", "Lone Wanderer", "https://soundcloud.com/ramesesb", 401),
                        new Song("music/RamesesB-Boat.mp3", "Ramases B & Zoe Philips", "Boat", "https://soundcloud.com/ramesesb", 322),
                        new Song("music/RamesesB-NightSky.mp3", "Ramases B", "Night Sky", "https://soundcloud.com/ramesesb", 400),
                        new Song("music/RamesesB-SpiritWalk.mp3", "Ramases B", "Spirit Walk", "https://soundcloud.com/ramesesb", 357),
                        new Song("music/RamesesB-EarthCalls.mp3", "Ramases B", "Earth Calls", "https://soundcloud.com/ramesesb", 298),
                        new Song("music/Amadeus-Nostalgia.mp3", "Amadeus", "Nostalgia", "https://soundcloud.com/amadeusmusic", 216),
                        new Song("music/Miro-StayAlive.mp3", "Miro", "Stay Alive", "https://soundcloud.com/thisismiro", 360),
                        new Song("music/Miro-Mendum.mp3", "Miro (Mendum)", "Elysium", "https://soundcloud.com/thisismiro", 288),
                        new Song("music/Miro-Promise.mp3", "Miro", "Promise", "https://soundcloud.com/thisismiro", 305),
                        new Song("music/Miro-Lauv.mp3", "Miro (Lauv)", "I Like Me Better", "https://soundcloud.com/thisismiro", 260),
                        new Song("music/Miro-TheGarden.mp3", "Miro", "The Garden of Escapism", "https://soundcloud.com/thisismiro", 236),
                        new Song("music/Miro-TheGardenOfMemories.mp3", "Miro", "The Garden of Memories", "https://soundcloud.com/thisismiro", 264),
                        new Song("music/Miro-Sacrifice.mp3", "Miro", "Sacrifice", "https://soundcloud.com/thisismiro", 293),
                        new Song("music/JulianKruse-Memories.mp3", "Julian Kruse", "Memories", "https://soundcloud.com/juliankruse", 360),
                        new Song("music/MikeBlackbird-SpiritOfLife.mp3", "Mike Blackbird (Blackmill)", "Spirit of Life", "https://soundcloud.com/mike-blackbird", 376),
                        new Song("music/nEXOW-Moments.mp3", "nExow", "CMA - Moments", "https://soundcloud.com/nexow53", 250),
                        new Song("music/Kaj-Daydream.mp3", "Kaj.", "Daydream", "https://soundcloud.com/kaj-6", 392),
                        new Song("music/candeman01-dontlookback.mp3", "candeman01 (CMA)", "Don't Look Back", "https://soundcloud.com/candeman01", 300),
                        new Song("music/chillmusic-FragmentsOfThePast.mp3", "ChillMusic (CMA)", "Fragments Of The Past", "https://soundcloud.com/chillmusic-465728706", 273),
                        new Song("music/RamesesB_TNCA.mp3", "Ramases B", "The Next Chapter Awaits", "https://soundcloud.com/ramesesb", 152)
                )
        );
    }

    @Override
    public void run(){
        while(true){
            int random = (int) (Math.random() * songs.size());

            current = songs.get(random);

            int second = 0;
            while(second < current.getLength()) {
                try{
                    current.setCurrentTime(second);
                    Thread.sleep(1000);
                    second++;
                } catch(InterruptedException e){

                }
            }
            current.setCurrentTime(0);
            try{
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }

        }

    }

    public Song getCurrent() {
        return current;
    }
}
