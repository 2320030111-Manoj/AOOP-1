package Six;

import java.util.LinkedList;
import java.util.ListIterator;

class MusicPlaylist {
    private LinkedList<String> playlist;

    public MusicPlaylist() {
        playlist = new LinkedList<>();
    }

    // Add a song to the playlist
    public void addSong(String song) {
        playlist.add(song);
        System.out.println("Added: " + song);
    }

    // Remove a song by name
    public void removeSong(String song) {
        if (playlist.remove(song)) {
            System.out.println("Removed: " + song);
        } else {
            System.out.println("Song not found: " + song);
        }
    }

    // Move a song to a different position in the playlist
    public void moveSong(String song, int newPosition) {
        if (!playlist.contains(song)) {
            System.out.println("Song not found: " + song);
            return;
        }

        if (newPosition < 1 || newPosition > playlist.size()) {
            System.out.println("Invalid position.");
            return;
        }

        // Remove the song from its current position
        playlist.remove(song);
        // Re-insert the song at the new position (adjust for 0-based indexing)
        playlist.add(newPosition - 1, song);

        System.out.println("Moved: " + song + " to position " + newPosition);
    }

    // Display the current playlist
    public void displayPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("The playlist is empty.");
            return;
        }

        System.out.println("Current Playlist:");
        ListIterator<String> iterator = playlist.listIterator();
        int position = 1;
        while (iterator.hasNext()) {
            System.out.println(position + ". " + iterator.next());
            position++;
        }
    }

    public static void main(String[] args) {
        MusicPlaylist myPlaylist = new MusicPlaylist();

        // Add songs to the playlist
        myPlaylist.addSong("Song A");
        myPlaylist.addSong("Song B");
        myPlaylist.addSong("Song C");

        // Display the playlist
        myPlaylist.displayPlaylist();

        // Move a song to a different position
        myPlaylist.moveSong("Song B", 1);

        // Display the updated playlist
        myPlaylist.displayPlaylist();

        // Remove a song from the playlist
        myPlaylist.removeSong("Song A");

        // Display the playlist after removal
        myPlaylist.displayPlaylist();
    }
}

