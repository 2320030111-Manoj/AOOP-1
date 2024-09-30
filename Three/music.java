package Three;
interface MusicSource {
    void play();
    void stop();
}

// Local File Adapter
class LocalFilePlayer {
    public void playLocalFile(String fileName) {
        System.out.println("Playing local file: " + fileName);
    }

    public void stopLocalFile() {
        System.out.println("Stopped local file");
    }
}

class LocalFileAdapter implements MusicSource {
    private LocalFilePlayer localFilePlayer;
    private String fileName;

    public LocalFileAdapter(String fileName) {
        this.localFilePlayer = new LocalFilePlayer();
        this.fileName = fileName;
    }

    public void play() {
        localFilePlayer.playLocalFile(fileName);
    }

    public void stop() {
        localFilePlayer.stopLocalFile();
    }
}

// Online Streaming Adapter
class OnlineStreamingService {
    public void streamSong(String song) {
        System.out.println("Streaming song: " + song);
    }

    public void stopStream() {
        System.out.println("Stopped streaming");
    }
}

class OnlineStreamingAdapter implements MusicSource {
    private OnlineStreamingService streamingService;
    private String song;

    public OnlineStreamingAdapter(String song) {
        this.streamingService = new OnlineStreamingService();
        this.song = song;
    }

    public void play() {
        streamingService.streamSong(song);
    }

    public void stop() {
        streamingService.stopStream();
    }
}

// Radio Station Adapter
class RadioStation {
    public void tuneIn(String stationName) {
        System.out.println("Tuned in to: " + stationName);
    }

    public void stopRadio() {
        System.out.println("Stopped radio");
    }
}

class RadioStationAdapter implements MusicSource {
    private RadioStation radioStation;
    private String stationName;

    public RadioStationAdapter(String stationName) {
        this.radioStation = new RadioStation();
        this.stationName = stationName;
    }

    public void play() {
        radioStation.tuneIn(stationName);
    }

    public void stop() {
        radioStation.stopRadio();
    }
}

// Volume Control Decorator
class VolumeControlDecorator implements MusicSource {
    private MusicSource musicSource;
    private int volumeLevel;

    public VolumeControlDecorator(MusicSource musicSource, int volumeLevel) {
        this.musicSource = musicSource;
        this.volumeLevel = volumeLevel;
    }

    public void play() {
        System.out.println("Setting volume to: " + volumeLevel);
        musicSource.play();
    }

    public void stop() {
        musicSource.stop();
    }
}

// Equalizer Decorator
class EqualizerDecorator implements MusicSource {
    private MusicSource musicSource;

    public EqualizerDecorator(MusicSource musicSource) {
        this.musicSource = musicSource;
    }

    public void play() {
        System.out.println("Applying equalizer settings");
        musicSource.play();
    }

    public void stop() {
        musicSource.stop();
    }
}

// Main application class
public class music {
    public static void main(String[] args) {
        // Example 1: Playing a local file with volume control
        MusicSource localFile = new LocalFileAdapter("song.mp3");
        MusicSource localFileWithVolume = new VolumeControlDecorator(localFile, 50);
        localFileWithVolume.play();
        localFileWithVolume.stop();

        // Example 2: Streaming an online song with equalizer and volume control
        MusicSource streaming = new OnlineStreamingAdapter("pop_song");
        MusicSource streamingWithFeatures = new VolumeControlDecorator(new EqualizerDecorator(streaming), 70);
        streamingWithFeatures.play();
        streamingWithFeatures.stop();

        // Example 3: Tuning into a radio station with equalizer
        MusicSource radio = new RadioStationAdapter("Jazz FM");
        MusicSource radioWithEqualizer = new EqualizerDecorator(radio);
        radioWithEqualizer.play();
        radioWithEqualizer.stop();
    }
}
