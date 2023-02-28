# ⚙️ Creational Design Patterns ⚙️

> Some of the sesign Pattern here, will be accompanied by a video reference by [Christopher Okhravi](https://www.youtube.com/@ChristopherOkhravi). I very recommend to watch the video

---
## 1.0 - __Factory Method__ 🏭


__Video Reference [HERE](https://www.youtube.com/watch?v=EcFVTgRHJLM)__


![Factory Method](https://refactoring.guru/images/patterns/diagrams/factory-method/structure-2x.png)

<br>

For the formal definition,

> __Factory__ design pattern defines an interface for creating an object, but let subclasses (factories) decide which and how each class will be instantiated. Factory method let client classes defer instantiation to subclasses (factories)

To put simply, __Factory__ encapsulates object creation logic: Factory is an object specializing in creating other objects.

In Layman's term, a factory can be thought as a class that contains a method that help you create and return objects, optionally based on user input, or internal program state. Depending the type of factory used, It can return object of different classes. Eg: a `AnimalFactory` can create instances of both `Dog` and `Cat` that are both subclasses of `Animal` class.

From the book, there is 3 types of factories stated:

* __Simple factory__- Only one single concrete factory is implemented from the factory interface. Think of it as a function that encapsulates instantiation logic. 
* __Factory method__ - A family of factories, able to create different objects based on which concrete factory is used.
* __Abstract factory__ - Discussed as a separate design pattern in the next section.

This section discusses __Factory Method__ design pattern. __Factory Method__ is mainly used when we want to create objects that are related to each other, but we don't want to expose the instantiation logic to the client. In __Factory Method__, we will have a `Factory` interface that have several concretions that will return different subclasses of the `Product` interface. 

We can say __Factory__ design pattern can assist in implementing the __Strategy__ design pattern. In the example of __Strategy__, we only have a single concrete `Duck` class, but yet there should be multiple types of `Duck` like `CityDuck` and `WildDuck`.Therefore, instead of passing the methods like `quack()` and `fly()` into `Duck`'s constructor, we could use __Factory__ to help us do just that.

---

### Problem:
* Uncertainties in types of objects
* Decisions to be made at runtime regarding which classes to use, depending on parameter or even program state
* Instantiation logic is complex and deserves to be separated


Say we are designing a war game. The game provides the user to select either `Easy`, `Normal` or `Hard` game mode. When implementing the game, you created a `Infantry` class, which has stats like `hp`, `attack`, `defense` etc. You decided that each game mode will have different stats for the `Infantry`. 

Without Factory Method, You write your code like this:

```java
// Notice here we have to put in those stats in the client code. The instantiation logic is not encapsulated.

if (gameMode == "Easy") {
    Infantry enemy = new Infantry(100, 10, 5);
} else if (gameMode == "Normal") {
    Infantry enemy = new Infantry(200, 20, 10);
} else if (gameMode == "Hard") {
    Infantry enemy = new Infantry(300, 30, 15);
}
```

This is already quite ugly and messy (`if-else` are bad!). Problems arise when you:

* Add a new game mode, such as `Insane` mode. You would have to add another if-else statement to handle the new game mode: `else if (gameMode == "Insane") ...`

* Add a new stats for `Infantry`, such as `speed`. You would have to modify the code (in this case, the constructor call) in all 3 if-else statements

---


### Solution:

Instead of having an if-else statement checking the game mode and instantiating enemies using `new` statement, we could use __Factory Method__ here.

First, create an __interface__ like `InfantryFactory` that consist of a `createInfantry()` method that could return an instance of `EnemyInfantry`.

Then, we would create concrete factories like `NormalModeInfantryFactory` and `EasyModeInfantryFactory`. This is because the instantiating algorithm of different game modes shall be different: 

* In `NormalModeInfantryFactory`, we would have a `createInfantry()` method that returns an instance of `Infantry` with stats like `hp = 200`, `attack = 20` and `defense = 10`. 

* In `EasyModeInfantryFactory`, we would have a `createInfantry()` method that returns an instance of `Infantry` with stats like `hp = 100`, `attack = 10` and `defense = 5`.

Now, in our main program, we would have something like this:

```java
// Farewell to instantiating logic in the client code!

InfantryFactory factory;

if (gameMode == "Easy") 
    factory = new EasyModeInfantryFactory();
else if (gameMode == "Normal")
    factory = new NormalModeInfantryFactory();
else if (gameMode == "Hard")
    factory = new HardModeInfantryFactory();

Infantry enemy = factory.createInfantry();
```

or get fancy with ternary operator:

```java
InfantryFactory factory = 
    (gameMode == "Easy") ? new EasyModeInfantryFactory(): 
    (gameMode == "Normal") ? new NormalModeInfantryFactory(): 
    new HardModeInfantryFactory();
```

You can see this provides much flexibility for our program to grow. Say if I suddenly want the normal mode infantries to have much higher HP and damage, I can simply modify the instantiating logic in `NormalModeInfantryFactory` without changing code at other place!


<br><br>


---
## 2.0 - __Abstract Factory__ 🏭


__Video Reference [HERE](https://www.youtube.com/watch?v=hUE_j6q0LTQ)__

![Abstract Factory](https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure.png)

<br>

__Abstract Factory__ is defined as:

> Interface for creating __families of related/dependent objects__ without specifying their concrete classes.

In contrast of __Factory Method__ design pattern, which a factory only responsible for constructing one type of object, an __Abstract Factory__ is capable of constructing multiple related objects, __same type or different type__. Think of it as a multitasking factory!

In fact, one can argue that the difference between a __Factory__ and a __Abstract Factory__ is simply that Abstract factory is capable of constructing multiple objects of related families.

If that's still not clear, a factory from __Factory Method__ may have only one method to create products, like `createProduct()` that returns `Product`, while an __Abstract Factory__ may have multiple methods to create a family of products, like `createProductA()`, `createProductB()`, `createProductC()` of type `ProductA`, `ProductB` and `ProductC`, provided that these products are related to each other based on the context. 

As an analogy, a factory that only produces `Product` is like a factory that only produces `Car`, while an __Abstract Factory__ is like a factory that produces `Car`, `Truck` and `Motorcycle`, while concretions of the Abstract Factory, such as `ToyotaFactory`, `HondaFactory` and `BMWFactory` are responsible for producing `ToyotaCar`, `HondaCar`, `BMWCar`, `ToyotaTruck`, `HondaTruck`, `BMWTruck`, `ToyotaMotorcycle`, `HondaMotorcycle`, `BMWMotorcycle` respectively.

---

### Problem:

Time to extend our previous game in __Factory Method__ section. In addition to the `Infantry` class, we also have `Tank` and `Calvary` classes. Each of these classes have their own stats, and we want to have different stats for each game mode.

Without both __Factory Method__ and __Abstract Factory__, we would have to write code like this:

```java
// Still, instantiating logic is not encapsulated and exposed nakedly in the client code.

Infantry createInfantry() {
    if (gameMode == "Easy") {
        return new Infantry(100, 10, 5);
    } else if (gameMode == "Normal") {
        return new Infantry(200, 20, 10);
    } else if (gameMode == "Hard") {
        return new Infantry(300, 30, 15);
    }
}

Tank createTank() {
    if (gameMode == "Easy") {
        return new Tank(100, 10, 5);
    } else if (gameMode == "Normal") {
        return new Tank(200, 20, 10);
    } else if (gameMode == "Hard") {
        return new Tank(300, 30, 15);
    }
}

// createCalvary() 
```

---

### Solution:

To apply __Abstract Factory__, we could have an interface `GameModeArmyFactory` that declares methods like `createInfantry()`, `createTank()` and `createCalvary()`. Then, we could have concrete factories like `EasyModeArmyFactory` and `NormalModeArmyFactory`. The implementation of these methods will vary by factory, creating normal mode and hard mode variations of `Infantry`, `Tank` and `Calvary`!


```java
public interface GameModeArmyFactory {
    Infantry createInfantry();
    Tank createTank();
    Calvary createCalvary();
}

public class EasyModeArmyFactory implements GameModeArmyFactory {
    public Infantry createInfantry() {
        return new Infantry(100, 10, 5);
    }

    public Tank createTank() {
        return new Tank(100, 10, 5);
    }

    public Calvary createCalvary() {
        return new Calvary(100, 10, 5);
    }
}

public class NormalModeArmyFactory implements GameModeArmyFactory {
    public Infantry createInfantry() {
        return new Infantry(200, 20, 10);
    }

    public Tank createTank() {
        return new Tank(200, 20, 10);
    }

    public Calvary createCalvary() {
        return new Calvary(200, 20, 10);
    }
}

// HardModeArmyFactory
// InsaneModeArmyFactory
```

<br><br>


---
## 3.0 - __Singleton__ 🦠

---

__Video Reference [HERE](https://www.youtube.com/watch?v=hUE_j6q0LTQ)__

![Singleton](https://refactoring.guru/images/patterns/diagrams/singleton/structure-en.png)

Notice that:

1. `- Singleton()` - The constructor of the __Singleton__ class is __private__.
1. `- instance: Singleton` - The __Singleton__ class has a __private static field__ that holds the single instance of the class itself.
1. `+ getInstance(): Singleton` - The __Singleton__ class has a __public static method__ that returns the single instance of the class itself.


<br>

The definition of __Singleton__:

> __Singleton__ ensures that the class has only one instance, and provides a global point of access to it.

A __Singleton__ is commonly referred as "global variables" across multiple modules/scripts. 

Essentially, a singleton is simply the __ONLY INSTANCE__ of a class will ever be created in the program lifecycle. The class will only create THAT SINGLE INSTANCE, and provides a way for other modules to access it. 

You may question: Why Singleton over static variables? You may want to read [__this__](https://medium.com/volosoft/why-you-should-prefer-singleton-pattern-over-a-static-class-a37731edb34f#:~:text=Singletons%20are%20well%20testable%20while,not%20be%20an%20easy%20option.).

When do you use a singleton? It is commonly used when you want to access a resource (or lower-level, a instance/object) that you want to be shared across multiple modules. 

---

A silly example, you designed a `BestLogger` class that prints the best `System.out.println()` with fancy header, footer and decorations. You want to use this logger in other scripts, but you don't want to create multiple instances of the logger, like so:

```java
// Inside CodeA.java

BestLogger logger = new BestLogger();
logger.log("Hello World!");
```

```java
// Inside CodeB.java

BestLogger logger = new BestLogger();
logger.log("Hello World!");
```

You can see, total of 2 instances of `BestLogger` is instantiated, but ideally, we should only use one instance of `BestLogger` across multiple modules. 

Using singleton:

```java
// Inside CodeA.java

BestLogger logger = BestLogger.getInstance();
logger.log("Hello World!");
```

This may not seem much of a difference to you, but proves to be especially useful if let's say:

* The instiantiation of the class is complex (Maybe the constructor require many parameters? - `new BestLogger(arg1, arg2, arg3...)` )
* The instiantiation of the class is resource intensive (Maybe our `BestLogger` prints to a cloud console, and instantiating it will involve establishing a connection first, which is slow if done repeatedly 🤔)

Real use cases include database connections. If you've ever used `mongoose` (or other database modules), which is Javascript's library for working with `MongoDB` database, once the connection to the database is established via the singleton object itself, the connection remains when the module is required in other scripts. We wouldn't want to create more than one connection to the database do we?

If you are required to read data from a file and access it in multiple scripts, instead of reading the file over and over again in every script, you may use a singleton so that the file is only __READ ONCE__ and cached after that.

An additional property of a singleton is that it can be __lazy loaded__. This means that the singleton instance is only created when it is needed. From the above example, if the `BestLogger` singleton is lazy loaded, the `BestLogger` instance is not created at program start, but only when `BestLogger.getInstance()` is called for the very first time. We will see this in the code example below.

---

### Controversy:

The __Singleton__ pattern has its controversials among developers that it is a bad practice. Here's why:

* We should avoid globals as much as possible.
* We can never sure that the program will ever need a single instance of the class. Maybe we need a new instance as we add functionailies to our program?
* Add challenges to unit testing, as codes depend on this "global variable"

---

In Python, __Module__ itself is already a singleton. If we have:
* `test1.py` - which have `print("Hello World")`
* `test2.py` - which have `import test1`
* `test3.py` - which have `import test1` and `import test2`

By running `test3.py` you will only see `Hello World` printed once only. This is because modules in Python are loaded like a singleton. The files are only loaded once, and the same instance is shared across all modules.

---

In pure OOP languages like Java, creating a Singleton may require the following steps:

* Creating a __private & static__ variable which holds an instance of itself, which is to be shared
* Making the constructor __private__ to prevent instantiating of the class in other modules
* Creating a public method to retrieve the singleton object, like `getInstance()`

```java
class Singleton {
    // Singleton
    static Singleton obj = new Singleton();

    // Make constructor private
    private Singleton() {}

    // Method to retrieve singleton
    public static Singleton retrieve() {
        // Lazy loading
        if (obj == null) obj = new Singleton();

        return obj;
    }
}
```


<br><br>


---
## 4.0 - __Builder__ 🏗

![Builder](https://refactoring.guru/images/patterns/diagrams/builder/structure.png)

---

__Builder__ is a solution to a anti-pattern called *Telescoping constructor*. 

A *Telescoping constructor* is essentially a constructor of a class that is bloated: maybe it has 10 required parameters to initialize all the fields of the object, which is a lot. Maybe you will work around with having separate setters, but still that isn't very elegant.

A __Builder__'s job is to construct the complex object, but hiding away all the pesty details away from the user and providing the option to the client for building whatever part is necessary. Imagine the original object requires 10 parameters to construct, but using builders, you can choose 3 of them to build while leaving the rest to be default values.

Let's build a phone:

```java
Phone p = new Phone("Nokia", "3310", 100, "blue", "2G", "2MP", "2.4 inch", "1000mAh", "Nokia OS");
```

Very unelegant. What does each parameter mean? Let's try to use a builder:

```java
Phone p = new PhoneBuilder()
    .setBrand("Nokia")
    .setModel("3310")
    .setPrice(100)
    .setColor("blue")
    .setNetwork("2G")
    .setCamera("2MP")
    .setScreen("2.4 inch")
    .setBattery("1000mAh")
    .setOS("Nokia OS")
    .getPhone();
```

Now that we can actually separate the parameters, The code is more elegant, but having all this code in the client logic is not good. Instead, we could introduce `Director` classes!

A `Director` class is a class that is in charge of actually building the object via Builders. The director class defines the order in which to execute the building steps, while the builder provides the implementation for those steps.

```java
class PhoneDirector {
    public Phone buildNokia3310() {
        return new PhoneBuilder()
            .setBrand("Nokia")
            .setModel("3310")
            .setPrice(100)
            .setColor("blue")
            .setNetwork("2G")
            .setCamera("2MP")
            .setScreen("2.4 inch")
            .setBattery("1000mAh")
            .setOS("Nokia OS")
            .getPhone();
    }
}
```

Now, inside our client code, we can just do:

```java
Phone p = new PhoneDirector().buildNokia3310();
```


The __Builder__ design pattern usually consist of these main components:

|Components|Description|
|-|-|
|__Director__| In charge of actually building the object via Builders; The class using the builders |
|__Abstract Builder__|The base builder class which will be inherited by __Concrete Builders__ (Eg: `PhoneBuilder`)|
|__Concrete Builders__|The actual builders which will construct the object, making some default assumptions (Eg: `NokiaBuilder`, `SamsungBuilder`)|
|__Product__| The actual object being built (Eg: `Nokia`)|

One way to implement traditionally, is to have a __Abstract Builder__ that has a constructor, that builds the object with required parameters only (or inferred). Through providing setters, other optional fields can be set. Eg:

```java
Phone phone = new NokiaPhoneBuilder("3310")
    .setPrice(100)
    .setColor('blue')
    .getPhone();

// The Abstract Builder will specify constructor, setPrice, setColor, getPhone etc and must be inherited by concrete builders
```

For `Javascript`, things can be done via Javascript Objects to set optional parameters.

```javascript
class Phone {
    constructor(name, { price, color } = {}) {
        ...
    }
}

const phone = new Phone('3310', {
    price: 100,
    color: 'blue'
})
```

<br><br>


---
## 5.0 - __Prototype__ 🧬

![Prototype](https://refactoring.guru/images/patterns/diagrams/prototype/structure.png)


__Prototype__ clone objects according to a prototypical instance. It is useful when dealing with creating many identical objects individually, where we would prefer cloning instead.

For a more direct explaination, each object will basically have a `clone()` method, which will return an exact copy of itself - including all the fields. This can be enforced by defining an interface `Cloneable` or `Prototype` which defines that every subclass must implement the `clone()` method.

```java
interface Cloneable {
    public Cloneable clone();
}

class Phone implements Cloneable {
    public Cloneable clone() {
        // Return a copy of itself
    }
}
```

For example, let's say in our game, we have a `Player` class which contains a sprite and a lot of game stats to. When the `Player` is instantiated, in addition to having to set the player stats, the constructor logic will also have to download the sprite from the internet. 

However, in cases where we have to create many identical `Player` objects, we would prefer to just clone the original `Player` object instead of repeatedly providing the player stats and downloading the sprite again and again. This is where the __Prototype__ pattern comes in handy - we can just clone the original `Player` object via a extremely simplified `clone()` method.

---

In our previously war game, we have a new unit - `Ninja` that can duplicate itself. Without prototype pattern, we would write into the game logic itself:

```java
Ninja n1 = new Ninja(100, 10, 5);
spawnIntoGame(n1);

// ... Some time later

if (n1.timeToClone()) {
    Ninja n2 = new Ninja(
        n1.getHealth(), 
        n1.getAttack(), 
        n1.getDefense(),
        // Imagine if we added more fields
    );
    spawnIntoGame(n2);
}
```

Wouldn't it be better if we can just do:

```java
Ninja n1 = new Ninja(100, 10, 5);
spawnIntoGame(n1);

if (n1.timeToClone()) {
    spawnIntoGame(n1.clone());
}
```

Much cleaner, right?

The implementation may be ambiguous, but the point remains
* Create a prototypical instance
* Clone it whenever we need a replica via `clone()` method


<br><br>


---
## 6.0 - __Object Pool__ 🏊‍♂️

![Object Pool](https://sourcemaking.com/files/v2/content/patterns/Object_pool1.png)


__Object Pool__ is a design pattern that allows us to reuse objects, `ReusableObj` instead of having to instantiate new ones. Said design pattern is useful if our system may use multiple identical objects at the same time, and we would prefer to reuse them instead of creating new ones by object instantiation and destroying them after use.

> Object pools (otherwise known as resource pools) are used to manage the object caching. A client with access to a Object pool can avoid creating a new Objects by simply asking the pool for one that has already been instantiated instead. Generally the pool will be a growing pool, i.e. the pool itself will create new objects if the pool is empty, or we can have a pool, which restricts the number of objects created.

Using an object pool is beneficial in the following cases:

* Object instantiation can be costly (Eg: the constructor downloads resources from the network). Reusing objects avoid this process by instantiating the object once and reusing it afterwards.

* If the rate of object creation is high (Eg: if we have to instantate 100 objects per second). 

To implement an object pool is simple:

Start by having a `Pool` class which is generally also a __Singleton__ class. Think of this `Pool` class as a container and manager for all the `ReusableObj` instances in the pool. At its most basic, it will have the following methods:

* `get()` - Get an instance of `ReusableObj` from the pool. If the pool is empty, instantiate a new `ReusableObj` and return it. 

* `release(obj)` - Release an instance of `ReusableObj` back to the pool. This method will be called when the `ReusableObj` is no longer needed. The `Pool` class will cache the `ReusableObj` and reuse it when `get()` is called again.


---

We are designing a WW2-themed where a lot of `Bullet` objects are created and destroyed every second. Each `Bullet` has its own properties: position (X,Y,Z), velocity etc, and is created when the player or enemies shoot. Imagine if each player is using guns that can shoot 100 bullets per second. That's a lot of `Bullet` objects being created and destroyed every second. Without an object pool, maybe 1000 `new Bullet()` will be called every second, and that may cause performance issues.

We would prefer to reuse the `Bullet` objects instead of creating new ones. 

```java
class Bullet {
    // ...
}

class Pool {
    private static Pool instance;
    private List<Bullet> bullets;

    private Pool() {
        bullets = new ArrayList<>();
    }

    // Singleton
    public static Pool getInstance() {
        if (instance == null)
            instance = new Pool();

        return instance;
    }

    public Bullet get() {
        if (bullets.isEmpty()) {
            return new Bullet();
        } else {
            return bullets.remove(0);
        }
    }

    public void release(Bullet b) {
        bullets.add(b);
    }
}
```

You are able to customize the `Pool` class to your needs. For example, you can add a `maxSize` field to restrict the number of `Bullet` objects that can be created. You can also add a `timeout` field to destroy `Bullet` objects that have been unused for a certain amount of time.