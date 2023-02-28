package flyweight;

public class Main {
    public static void main(String[] args) {
        Particle particle = new Particle(ParticleType.BULLET, 0, 0, 1, 1);
        Particle particle1 = new Particle(ParticleType.BULLET, 0, 0, 1, 1);

        Particle particle2 = new Particle(ParticleType.EXPLOSION, 0, 0, 1, 1);
        Particle particle3 = new Particle(ParticleType.EXPLOSION, 0, 0, 1, 1);

        System.out.println(particle.getFlyweight() == particle1.getFlyweight());
        System.out.println(particle2.getFlyweight() == particle3.getFlyweight());
    }
}
