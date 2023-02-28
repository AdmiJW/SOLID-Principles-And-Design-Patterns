package flyweight;

enum ParticleType {
    BULLET,
    EXPLOSION,
}


public class ParticleFlyweight {
    private ParticleType type;
    private Object sprite;    
    private String color;

    public ParticleFlyweight(ParticleType type, Object sprite, String color) {
        this.type = type;
        this.sprite = sprite;
        this.color = color;
    }

    public ParticleType getType() {
        return type;
    }

    public Object getSprite() {
        return sprite;
    }

    public String getColor() {
        return color;
    }
}
