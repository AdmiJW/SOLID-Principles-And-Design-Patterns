package flyweight;

import java.util.HashMap;
import java.util.Map;

public class ParticleFlyweightFactory {
    private static ParticleFlyweightFactory instance;
    Map<ParticleType, ParticleFlyweight> flyweights = new HashMap<>();
    

    private ParticleFlyweightFactory() {
        flyweights.put(ParticleType.BULLET, new ParticleFlyweight(ParticleType.BULLET, null, "red"));
        flyweights.put(ParticleType.EXPLOSION, new ParticleFlyweight(ParticleType.EXPLOSION, null, "blue"));
    }

    public static ParticleFlyweightFactory getInstance() {
        if (instance == null)
            instance = new ParticleFlyweightFactory();
        return instance;
    }


    public ParticleFlyweight getFlyweight(ParticleType type) {
        return getInstance().flyweights.get(type);
    }
}
