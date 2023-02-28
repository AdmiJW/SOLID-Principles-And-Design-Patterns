package flyweight;


public class Particle {
    private float x;
    private float y;
    private float velocityX;
    private float velocityY;

    private ParticleFlyweight flyweight;

    public Particle(ParticleType type, float x, float y, float velocityX, float velocityY) {
        flyweight = ParticleFlyweightFactory.getInstance().getFlyweight(type);
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    
    public void update() {
        x += velocityX;
        y += velocityY;
    }

    public ParticleFlyweight getFlyweight() {
        return flyweight;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }
}
