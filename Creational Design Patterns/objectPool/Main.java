package objectPool;



public class Main {
    public static void main(String[] args) {
        BulletPool pool = BulletPool.getInstance();

        Bullet bullet = pool.getBullet();
        bullet.setPos(1, 2, 3);
        bullet.setVelocity(4, 5, 6);
        pool.returnBullet(bullet);
        
        bullet = pool.getBullet();
        System.out.println(bullet.getX());
        System.out.println(bullet.getY());
        System.out.println(bullet.getZ());
        System.out.println(bullet.getVelocityX());
        System.out.println(bullet.getVelocityY());
        System.out.println(bullet.getVelocityZ());
    }    
}
