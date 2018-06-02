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

                        //06.02.2018 series
                        new Song("music/aviator.mp3", "Ampyx", "The Aviator", "https://soundcloud.com/ampyx", 279),
                        new Song("music/soundnet-losinghope.mp3", "SoundNet", "Loosing Hope Was Freedom", "https://soundcloud.com/chillmusic-465728706", 302),
                        new Song("music/aerocity-interlude.mp3", "Aerocity", "Interlude", "https://soundcloud.com/aerocity", 154),
                        new Song("music/aerocity-room208.mp3", "Aerocity", "Room 208", "https://soundcloud.com/aerocity", 199),
                        new Song("music/aerocity-dream.mp3", "Aerocity", "Fever Dream", "https://soundcloud.com/aerocity", 193),
                        new Song("music/aerocity-daylight.mp3", "Aerocity", "Daylight", "https://soundcloud.com/aerocity", 261),
                        new Song("music/aerocity-love.mp3", "Aerocity", "Love Lost", "https://soundcloud.com/aerocity", 281),
                        new Song("music/aerocity-passages.mp3", "Aerocity", "Passages", "https://soundcloud.com/aerocity", 239),
                        new Song("music/aearocity-couldwhaterkids.mp3", "Aerocity", "Cold Weather Kids", "https://soundcloud.com/aerocity", 200),
                        new Song("music/aurora-solace.mp3", "Aurora", "Solace", "https://soundcloud.com/aurorachillstep", 341),
                        new Song("music/lightscrape-awake.mp3", "Lightscrape & Trazer", "Awake", "https://soundcloud.com/chillmusic-465728706", 305),
                        new Song("music/lightscrape-escapethismoment.mp3", "Lightscape", "Escape This Moment", "https://soundcloud.com/chillmusic-465728706", 223),
                        new Song("music/ptr-huangshan.mp3", "Ptr.", "Huangshan", "https://soundcloud.com/ptr-11", 296),
                        new Song("music/xandra-guardian.mp3", "Xandra", "Guardian", "https://soundcloud.com/hearwegochannel", 207),
                        new Song("music/nExow-perception.mp3", "nExow", "Perception", "https://soundcloud.com/nexow53", 241),
                        new Song("music/soulfy-whenthesunsets.mp3", "Soulfy", "When The Sun Sets", "https://soundcloud.com/thechillbay", 266),
                        new Song("music/michaelstlaurent-knowyouwell.mp3", "Michael St Laurent", "Know You Well", "https://soundcloud.com/remissum-records", 271),
                        new Song("music/substract-falling.mp3", "Subtract", "Falling", "https://soundcloud.com/unsaidmusic", 297),
                        new Song("music/ento-missyou.mp3", "Ento", "Miss You", "https://soundcloud.com/enthoofficial", 266),
                        new Song("music/PikaMax-spheria.mp3", "PikaMaX", "Spherià", "https://soundcloud.com/maximilian-bauer-12", 245),
                        new Song("music/blureskyvoice-takemeaway.mp3", "Blure & Skyvoice", "Take Me Away", "https://soundcloud.com/thechillbay", 216),
                        new Song("music/hearwego-vexaic.mp3", "Vexaic & Event Horizon", "Break Away", "https://soundcloud.com/hearwegochannel", 253),
                        new Song("music/vexaic-lostgoodbye.mp3", "Vexaic", "Lost Goodbye", "https://soundcloud.com/hearwegochannel", 314),
                        new Song("music/poolz-youdonthaveto.mp3", "Poolz", "You Dont Have To", "https://soundcloud.com/hearwegochannel", 314),
                        new Song("music/sappheiros-byyourside.mp3", "Sappheiros", "By Your Side", "https://soundcloud.com/sappheirosmusic", 162),
                        new Song("music/MYKOOL-Ikiru.mp3", "MYKOOL", "Ikiru", "https://soundcloud.com/thechillbay", 358),
                        new Song("music/CMA-whatitwhatitis.mp3", "CMA", "What Is What It Is", "https://soundcloud.com/thechillbay", 229),
                        new Song("music/jimmis-flyinghome.mp3", "Jimmis", "Flying Home", "https://soundcloud.com/thechillbay", 191),
                        new Song("music/Blure-cycleoflife.mp3", "Blure", "Cycle Of Life", "https://soundcloud.com/thechillbay", 217),
                        new Song("music/killigrew-timeless.mp3", "Killigrew", "Timeless As The Waves", "https://soundcloud.com/thechillbay", 322),
                        new Song("music/hereafter-onlyyou.mp3", "Hereafter", "Only You", "https://soundcloud.com/thechillbay", 213),
                        new Song("music/subtract-falling.mp3", "Subtract", "Falling", "https://soundcloud.com/thechillbay", 297),
                        new Song("music/Slevemor-aftertherain.mp3", "Slevemor", "After The Rain", "https://soundcloud.com/thechillbay", 489),
                        new Song("music/sacred-reachingout.mp3", "Sacred", "Reaching Out", "https://soundcloud.com/thechillbay", 330),
                        new Song("music/SizzleBird-elixir.mp3", "SizzleBird", "Elixir", "https://soundcloud.com/thechillbay", 256),
                        new Song("music/SizzleBird-Memory.mp3", "SizzleBird", "Memory", "https://soundcloud.com/thechillbay", 227),
                        new Song("music/sizzlebird-imagine.mp3", "SizzleBird", "Imagine", "https://soundcloud.com/arkay92", 210),
                        new Song("music/SizzleBird-everafter.mp3", "SizzleBird & Emily Underhill", "Ever After", "https://soundcloud.com/scvetko", 216),
                        new Song("music/Sizzlebird-mystic.mp3", "SizzleBird", "Mystic", "https://soundcloud.com/remissum-records", 211),
                        new Song("music/twin-youismine.mp3", "Twin", "You Is Mine", "https://soundcloud.com/twin-liquidmusic", 221),
                        new Song("music/twin-youismine.mp3", "Twin", "You Is Mine", "https://soundcloud.com/twin-liquidmusic", 221),
                        new Song("music/mylove-dreamerchillout.mp3", "DreamerChillout", "My Love", "https://soundcloud.com/dreamerchillout", 206),
                        new Song("music/sappheiros-thesoundofrain.mp3", "Sappheiros", "The Sound Of Rain", "https://soundcloud.com/sappheirosmusic", 265),
                        new Song("music/electus-forever.mp3", "Electus", "Forever", "https://soundcloud.com/dreeamyy", 257),
                        new Song("music/electus-anotherworld.mp3", "Electus", "Another World", "https://soundcloud.com/thechillbay", 390),
                        new Song("music/electus-templeoflight.mp3", "Electus", "Temple Of Light", "https://soundcloud.com/thechillbay", 279),
                        new Song("music/electus-imaginaryfriend.mp3", "Electus", "Imaginary Friend", "https://soundcloud.com/thechillbay", 279),
                        new Song("music/electus-herewithyou.mp3", "Electus", "Here With You", "https://soundcloud.com/liquisense", 279),
                        new Song("music/electus-yourworld.mp3", "Electus ft Bella", "Your World", "https://soundcloud.com/remissum-records", 304),
                        new Song("music/electus-frozen.mp3", "Electus", "Frozen Tides", "https://soundcloud.com/remissum-records", 268),
                        new Song("music/jabricon-lightattheend.mp3", "Jabricon", "Light At The End", "https://soundcloud.com/thechillbay", 246),
                        new Song("music/jabricon-daydreaming.mp3", "Jabricon", "Daydreaming", "https://soundcloud.com/thechillbay", 241),
                        new Song("music/juliankruse-bloosom.mp3", "Julian Kruse", "Bloosom", "https://soundcloud.com/juliankruse", 279),
                        new Song("music/juliankruse-memories.mp3", "Julian Kruse", "Memories", "https://soundcloud.com/juliankruse", 248),
                        new Song("music/crail-whathaveilost.mp3", "Crail", "What Have I Lost?", "https://soundcloud.com/lee-366", 236),
                        new Song("music/You-Subconscious.mp3", "You", "Subconscious", "https://soundcloud.com/youdnb", 340),
                        new Song("music/Elryk-sorrow.mp3", "Elryk", "Sorrow", "https://soundcloud.com/elryk", 288),
                        new Song("music/jacoo-ifyouonlyknew.mp3", "Jacoo", "If You Only Knew", "https://soundcloud.com/thechillbay", 211),
                        new Song("music/jacoo-withering.mp3", "Jacoo", "Withering", "https://soundcloud.com/thechillbay", 268),
                        new Song("music/jacoo-waitingforyou.mp3", "Jacoo", "Waiting For You", "https://soundcloud.com/remissum-records", 221),
                        new Song("music/andreasb-flaoting.mp3", "Andreas B", "Floating", "https://soundcloud.com/thechillbay", 260),


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
