package Three;
interface AudioSource {
    void playAudio();
    void stopAudio();
}

// Abstract MediaPlayer Class (Bridge Abstraction)
abstract class MediaPlayer {
    protected AudioSource audioSource;

    public MediaPlayer(AudioSource audioSource) {
        this.audioSource = audioSource;
    }

    public abstract void play();
    public abstract void stop();
}

// Local Audio Adapter (Adapter Pattern)
class LocalAudioPlayer {
    public void playLocalAudio(String fileName) {
        System.out.println("Playing local audio file: " + fileName);
    }

    public void stopLocalAudio() {
        System.out.println("Stopped local audio");
    }
}

class LocalAudioAdapter implements AudioSource {
    private LocalAudioPlayer localAudioPlayer;
    private String fileName;

    public LocalAudioAdapter(String fileName) {
        this.localAudioPlayer = new LocalAudioPlayer();
        this.fileName = fileName;
    }

    @Override
    public void playAudio() {
        localAudioPlayer.playLocalAudio(fileName);
    }

    @Override
    public void stopAudio() {
        localAudioPlayer.stopLocalAudio();
    }
}

// Online Streaming Adapter (Adapter Pattern)
class StreamingService {
    public void streamTrack(String trackName) {
        System.out.println("Streaming track: " + trackName);
    }

    public void stopStreaming() {
        System.out.println("Stopped streaming");
    }
}

class StreamingServiceAdapter implements AudioSource {
    private StreamingService streamingService;
    private String trackName;

    public StreamingServiceAdapter(String trackName) {
        this.streamingService = new StreamingService();
        this.trackName = trackName;
    }

    @Override
    public void playAudio() {
        streamingService.streamTrack(trackName);
    }

    @Override
    public void stopAudio() {
        streamingService.stopStreaming();
    }
}

// Radio Station Adapter (Adapter Pattern)
class RadioChannel {
    public void tuneIn(String channelName) {
        System.out.println("Tuned into: " + channelName);
    }

    public void stopRadio() {
        System.out.println("Stopped radio");
    }
}

class RadioChannelAdapter implements AudioSource {
    private RadioChannel radioChannel;
    private String channelName;

    public RadioChannelAdapter(String channelName) {
        this.radioChannel = new RadioChannel();
        this.channelName = channelName;
    }

    @Override
    public void playAudio() {
        radioChannel.tuneIn(channelName);
    }

    @Override
    public void stopAudio() {
        radioChannel.stopRadio();
    }
}

// SimpleMediaPlayer Class (Concrete Bridge Abstraction)
class SimpleMediaPlayer extends MediaPlayer {

    public SimpleMediaPlayer(AudioSource audioSource) {
        super(audioSource);
    }

    @Override
    public void play() {
        System.out.println("Simple Media Player: Playing");
        audioSource.playAudio();
    }

    @Override
    public void stop() {
        System.out.println("Simple Media Player: Stopping");
        audioSource.stopAudio();
    }
}

// VolumeAdjuster Decorator (Decorator Pattern)
class VolumeAdjusterDecorator extends MediaPlayer {
    private int volumeLevel;

    public VolumeAdjusterDecorator(MediaPlayer player, int volumeLevel) {
        super(player.audioSource);
        this.volumeLevel = volumeLevel;
    }

    @Override
    public void play() {
        System.out.println("Adjusting volume to: " + volumeLevel);
        audioSource.playAudio();
    }

    @Override
    public void stop() {
        audioSource.stopAudio();
    }
}

// SoundEnhancer Decorator (Decorator Pattern)
class SoundEnhancerDecorator extends MediaPlayer {

    public SoundEnhancerDecorator(MediaPlayer player) {
        super(player.audioSource);
    }

    @Override
    public void play() {
        System.out.println("Enhancing sound with equalizer");
        audioSource.playAudio();
    }

    @Override
    public void stop() {
        audioSource.stopAudio();
    }
}

// Main Application Class
class music_1 {
    public static void main(String[] args) {
        // Example 1: Playing a local audio file with a simple player
        AudioSource localAudioSource = new LocalAudioAdapter("audiofile.mp3");
        MediaPlayer simpleLocalPlayer = new SimpleMediaPlayer(localAudioSource);
        simpleLocalPlayer.play();
        simpleLocalPlayer.stop();

        // Example 2: Streaming audio with volume adjustment
        AudioSource streamingAudioSource = new StreamingServiceAdapter("hit_track");
        MediaPlayer streamingPlayer = new VolumeAdjusterDecorator(new SimpleMediaPlayer(streamingAudioSource), 60);
        streamingPlayer.play();
        streamingPlayer.stop();

        // Example 3: Listening to a radio channel with sound enhancer and volume control
        AudioSource radioSource = new RadioChannelAdapter("Rock FM");
        MediaPlayer advancedRadioPlayer = new SoundEnhancerDecorator(new VolumeAdjusterDecorator(new SimpleMediaPlayer(radioSource), 85));
        advancedRadioPlayer.play();
        advancedRadioPlayer.stop();
    }
}
