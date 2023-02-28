package proxy;

public class LibVideoDownloader implements IVideoDownloader {
    @Override
    public void downloadVideo(String url) {
        System.out.println("Downloading video from " + url);
        System.out.println("Video downloaded");
    }
}
