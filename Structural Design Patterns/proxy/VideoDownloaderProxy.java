package proxy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class VideoDownloaderProxy implements IVideoDownloader {
    LibVideoDownloader libVideoDownloader;

    private Set<String> blacklisted;
    private Map<String, String> cache;


    public VideoDownloaderProxy() {
        libVideoDownloader = new LibVideoDownloader();
        blacklisted = new HashSet<>();
        cache = new HashMap<>();

        blacklisted.add("banned.com");
    }

    @Override
    public void downloadVideo(String url) {
        if (blacklisted.contains(url)) {
            System.out.println("Video is blacklisted");
            return;
        }

        if (cache.containsKey(url)) {
            System.out.println("Video is cached");
            return;
        }

        libVideoDownloader.downloadVideo(url);
        cache.put(url, "cached");
    }
}
