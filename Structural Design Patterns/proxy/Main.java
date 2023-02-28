package proxy;

public class Main {

    public static void main(String[] args) {
        IVideoDownloader videoDownloader = new VideoDownloaderProxy();
        videoDownloader.downloadVideo("banned.com");
        videoDownloader.downloadVideo("youtube.com");
        videoDownloader.downloadVideo("youtube.com");
    }
}
