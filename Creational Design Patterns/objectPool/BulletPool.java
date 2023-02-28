package objectPool;

import java.util.ArrayDeque;
import java.util.Queue;

public class BulletPool {
    
    // Singleton Pattern here
    private static BulletPool instance = null;

    private BulletPool() {}

    public static BulletPool getInstance() {
        if (instance == null) {
            instance = new BulletPool();
        }
        return instance;
    }

    // Here, I use a Queue as the container
    private Queue<Bullet> bullets = new ArrayDeque<Bullet>();

    public Bullet getBullet() {
        if (bullets.isEmpty()) return new Bullet();
        return bullets.poll();
    }

    public void returnBullet(Bullet bullet) {
        bullets.add(bullet);
    }
}
