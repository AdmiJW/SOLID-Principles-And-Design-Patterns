# ✏️ Design Challenges ✏️

Here, I record my thoughts on the design challenges I encounter and what I think is the best design solution for scalability and maintainability.

---

## 1 - __Type of Enemies__ (Interface Segregation Principle + Strategy)

Imagine we are designing a game where it consist of a base `Enemy` class. We subclassed the `Enemy` class into different types such as `FlyingEnemy`, which have to implement `fly()` method, and `SwimmingEnemy`, which have to implement `swim()` method.

```java
public class Enemy {
    public void attack() {
        // attack logic
    }
}

public class FlyingEnemy extends Enemy {
    public void fly() {
        // fly logic
    }
}

public class SwimmingEnemy extends Enemy {
    public void swim() {
        // swim logic
    }
}
```

Soon enough, you come up with a new type of enemy, `FlyingSwimmingEnemy`, which have to implement both `fly()` and `swim()` methods. The problem is, how do we implement this new type of enemy? What should `FlyingSwimmingEnemy` extend from? Should it extend from `FlyingEnemy` or `SwimmingEnemy`? Do we move the `fly()` and `swim()` methods into the base `Enemy` class?

---

Obviously, we should not move the `fly()` and `swim()` methods into the base `Enemy` class, as it is not applicable to all enemies and would violate the __Interface Segregation Principle__.

Instead, what __Interface Segregation Principle__ suggests is that we should create a new interface `IFlyable` and `ISwimmable` which enforce the `fly()` and `swim()` methods respectively. 

Then, we can make `FlyingEnemy` and `SwimmingEnemy` implement `IFlyable` and `ISwimmable` respectively. As for `FlyingSwimmingEnemy`, it will implement both `IFlyable` and `ISwimmable`. With this, we might've just eliminate the complexity of the inheritance hierarchy to just `Enemy` and its direct subclasses `FlyingEnemy`, `SwimmingEnemy` and `FlyingSwimmingEnemy`.

```java
public class Enemy {
    public void attack() {
        // attack logic
    }
}

public class FlyingEnemy extends Enemy implements IFlyable {
    public void fly() {
        // fly logic
    }
}

public class SwimmingEnemy extends Enemy implements ISwimmable {
    public void swim() {
        // swim logic
    }
}

public class FlyingSwimmingEnemy extends Enemy implements IFlyable, ISwimmable {
    public void fly() {
        // fly logic
    }

    public void swim() {
        // swim logic
    }
}
```

However still, if you have a lot of enemy types that implements the same logic of `fly()` and `swim()`, you will end up with a lot of duplicated code. In that case, you can consider using the __Strategy Design Pattern__ which encapsulates the `fly()` and `swim()` logic into a separate class and allow you to inject and reuse it in the enemy classes.

```java
public interface IFlyStrategy {
    void fly();
}

public interface IFlyBehaviour {
    void setFlyStrategy(IFlyStrategy flyStrategy);
    void fly();
}

public class FlyingEnemy extends Enemy implements IFlyBehaviour {
    private IFlyStrategy flyStrategy;

    public void setFlyStrategy(IFlyStrategy flyStrategy) {
        this.flyStrategy = flyStrategy;
    }

    public void fly() {
        flyStrategy.fly();
    }
}
```

In addition to reusability improvement, now the `Enemy`'s behaviour are dynamic and swappable! The enemy can change its `fly()` and `swim()` behaviour at runtime by changing its `flyStrategy` and `swimStrategy`, which allows you to do more interesting things!


---

## 2 - __Human Simulation__ (Composition over Inheritance + Bridge)

Imagining we are designing a world simulation consisting of base `Human` class. Later, we created subclasses `Male` and `Female` which extends from `Human`.

Later, you decided to add occupation variants too, like `Doctor` and `Teacher`. The issue here is that, we already had `Male` and `Female` classes which extends from `Human`. Do we have to create variants like `MaleDoctor`, `MaleTeacher`, `FemaleDoctor` and `FemaleTeacher`? 

If you recall, this is very similar to the example given in the __Bridge Design Pattern__. This is the case where __Composition over Inheritance__ comes in. Instead of creating a new class for every sub-variant of `Human` introduced which results in cartesian-product growing rate inheritance hierarchy, we can create a new class `Occupation` which has a `Human` instance composition as a field. Then, we can create `Doctor` and `Teacher` classes which extends from `Occupation`. The very same can be applied to `Gender` classes like `Male` and `Female`.

```java
public class Human {
    public void doSomething() {}
}


// Gender Composition
public abstract class Gender {
    private Human human;

    public Gender(Human human) {
        this.human = human;
    }

    public abstract void doGenderSpecific();
}

public class Male extends Gender {
    public Male(Human human) {
        super(human);
    }

    @Override
    public void doGenderSpecific() {...}
}


// Occupation Composition
public abstract class Occupation {
    private Human human;

    public Occupation(Human human) {
        this.human = human;
    }

    public abstract void doOccupationSpecific();
}

public class Doctor extends Occupation {
    public Doctor(Human human) {
        super(human);
    }

    @Override
    public void doOccupationSpecific() {...}
}
```
