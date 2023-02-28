# ⚙️ Structual Design Patterns ⚙️

---
## 1.0 - __Decorator__ 🎀

![Decorator](https://refactoring.guru/images/patterns/diagrams/decorator/structure.png)

---

__Reference [HERE](https://www.youtube.com/watch?v=GCraGHx6gso)__

__Decorator__ is a design pattern that is defined as:

> Decorator attachs a additional responsibility to an object __dynamically__ (without changing source code). It is an flexible alternative to subclassing for extending functionality

Think of it like this: You have a base class that contains a method `operation()`. With decorator, you can add additional functionality and logic to the `operation()`.

In pure OOP language, this is done by creating a new `Decorator` class that extends the base class, and have the `operation()` method overridden. The `Decorator` class will contain the wrapee instance, and the overridden `operation()` method will call the `operation()` method of the base class, and add additional logic before and after the execution.

```java
public class Wrapee {
    public void operation() {...}
}

public class Decorator extends Wrapee {
    private Wrapee wrapee;

    public Decorator(Wrapee wrapee) {
        this.wrapee = wrapee;
    }

    @Override
    public void operation() {
        // Additional logic before
        wrapee.operation();
        // Additional logic after
    }
}
```

---

### Problem:

In the book __Head First Design Pattern__, the example provided is that we have a Cafe shop that will have `Beverages` (abstract class) and concrete classes like `Espresso` and `Mocha`.

These `Beverages` will have methods like `getCost` and `description`.

 However, problem arises when we need a way to have optional toppings like `SoyMilk`, `Chocolate`, or `WhippedCream` for those beverages. Initially, we may have some ideas of the implementation:

 * Subclassing for the combinations. Say `ChocolateMocha` or `WhippedCreamEspresso`. However, what if toppings can be mixed? Like `SoyMilkChocolateMocha`? How many combinations will it result in? This is not scalable.

 * Implementing boolean/integer variables in the `Beverage` itself, like variables `hasSoy` or `chocolateCount`. However, this will pose a potential __Interface Segregation__ issue from SOLID principle. Say we started selling `Tea`. `Tea` should never use `hasChocolate` because it is a absurd combination!

 ---

 ### Solution:

 Instead, the idea is that we first start with two interfaces:

 * `Beverage` - Having `getCost` and `describe` method
 * `BeverageDecorator` - __Extends `Beverage` class__. Also, each `BeverageDecorator` should have an attribute `Beverage` acting as inner object which it decorates.

*(Over here, you can see the Decorator, `BeverageDecorator`, __is a Beverage__ and __has a Beverage__). A decorator should be the same type with the object it is decorating, and has the same type  with the object it is decorating*

Then we can go ahead and create concrete classes of `Beverage` like `Espresso` and `Mocha`, as well as concrete classes of `BeverageDecorator` like `SoyMilkDecorator` and `ChocolateDecorator`.

As you might see where this is going, when we want to add Chocolate to our Mocha, we simply create a `ChocolateDecorator` decorator and pass in our original `Mocha` inside, like:

```java
Beverage myBeverage = new Mocha();
myBeverage = new ChocolateDecorator(myBeverage);
myBeverage.getCost();
```

How about a Mocha with SoyMilk and Chocolate? We simply create a `ChocolateDecorator` and pass in a `SoyMilkDecorator` with `Mocha` inside, like:

```java
Beverage myBeverage = new Mocha();
myBeverage = new SoyMilkDecorator(myBeverage);
myBeverage = new ChocolateDecorator(myBeverage);
myBeverage.getCost();
```

As we are calling the `getCost()` method, the `ChocolateDecorator` will return the __base price__ of whatever the beverage it is having as attribute (In this case, `Mocha`), and add an additional price to it, if that is what your intention.


---

In Python, using decorator is fairly simple because it is already built in, using the `@` symbol followed by the method name to decorate before a function.

<br><br>


---
## 2.0 - __Proxy__ ⏱️

![Proxy](https://refactoring.guru/images/patterns/diagrams/proxy/structure.png)


---

__Reference [HERE](https://www.youtube.com/watch?v=NwaabHqPHeM)__

According to the defintion:

> __Proxy__ provides a surrogate/placeholder for another object to control access to it.

According to the book, __Proxy__ is split into 4 types:

* __Remote__ - Subject to access is from other server, or from other project
* __Virtual__ - Subject to access is potentially expensive, thus you may want to cache it or delay its instantiation until absolutely necessary
* __Protection__ - Subject to access hold credentials and you want to authorize client prior to giving access
* __Smart__ - Subject to access is a reference to another object, and you want to perform additional actions when the client access the subject.

The core __intent__ of using a __Proxy__, is to __intersect the direct access of a client to a targeted subject__, while the Proxy itself still is able to be used as the actual subject itself.

The 3 main idea of proxy is that:

* Proxy control access to the actual subject
* Proxy implements the same interface as the actual subject
* Proxy handles instantiation of the subject.

You may heard of proxy a lot from networking. Proxy servers are servers that act as a middleman between client and the actual server. This is done to provide additional functionality, like caching, or to provide additional security with firewall and filtering. The very same idea is the same for the proxy pattern.

Perhaps in a multithreaded program, a certain service is frequently accessed. Therefore, between client and the server, a proxy may be used to check whether the service is busy or occupied prior to giving access to a particular client to avoid race conditions or even deadlocks. *(Maybe you can change implementation of service itself to check occupied, but maybe you simply can't because you don't have access to the service itself)*

---

### Problem:

Considering we are designing a application targetted for kids. The application will have to play online videos, so a `VideoDownloader` that allows to download videos from the internet is required. You had found a third-party library that allows you to download videos from the internet. However, you have to extend the library to allow for:

1. Check if the video is suitable for kids, by checking the url of the video against a list of blacklisted urls.

1. Cache the downloaded video, so that if the same video is downloaded again, the cached version will be returned instead of downloading again.

In this case, we would still use the third-party `VideoDownloader` library, but we would create a `VideoDownloaderProxy` that extends the `VideoDownloader` class, implement the additional functionality, and have it act as a middleman between the client and the actual `VideoDownloader` class to intercept requests.


<br><br>

---
## 3.0 - __Adapter__ 🔌

![Adapter](https://refactoring.guru/images/patterns/diagrams/adapter/structure-object-adapter.png)

---

__Reference [HERE](https://www.youtube.com/watch?v=2PKQtcJjYvc)__

Definition:

> __Adapter__ converts the interface of a class to another interface the client would expect.
 
> __Adapter__ let classes work together that couldn't otherwise becasue of incompatible interfaces.

__Adapter__, exactly what you think it is. Adapter solves the problem of incompatible interfaces (Different expectation of method names or attributes).


There are two ways of implementing __Adapters__:
* Composition
* Multiple Inheritance

We'll mainly discuss __Composition Adapter__.

Say we have a `Client` (Usually the program that we are writing) that wants to call an `Adaptee`'s (Usually inaccessible, unmodifiable code, Eg: libraries) method. However, there are problem faced:

* We do not has access, or does not want to modify the `Adaptee`'s source code.
* The interface format coming from the `Client` is incompatible to that of `Adaptee`'s. Eg: `Client` expects a method `foo()` to be called, but `Adaptee` only has a method `bar()`.

For example, we are developing a Stock program that monitors stock movement, presenting different stocks in form of bar and line chart using some in-house developed charting library. So far, the stock data provided are of __JSON__ format, and is used in our charting library via `chart(data)` method.

Suddenly, you received a requirement to add candlestick charting to the program. You found a library that does candlestick charting, but it expects data in __XML__ format, and the method to call is `drawCandlestick(data)`. It is incompatible with how our current charting library works!

Instead of directly modifying the business logic to use the new library, like

```java
if (chartType == "candlestick") {
    XMLData xml = convertJSONToXML(data);
    CandleStickLib.drawCandlestick(xml);
} else {
    MyChartingLib.chart(data);
}
```

It would be better to introduce a `ChartingAdapter` class that will encapsulate the logic of converting the data from JSON to XML, and call the `drawCandlestick` method under the `chart` method that obeys the `ChartingInterface` interface of in-house charting library.

```java
class CandleStickChartingAdapter extends ChartingInterface {
    JSONData json;
    ...

    @Override
    void chart() {
        XMLData data = convertXMLToJSON(json);
        CandleStickLib.drawCandlestick(data);
    }
}
```

Imagine suddenly we changed our stock data provider and the new provider gives __Excel__ data or __CSV__ data. Without changing code from the business logic, we would still do fine, just implement a new `CSVDataChartingAdapter` or `ExcelDataChartingAdapter`! The same goes for introducing new third-party charting libraries that uses different interfaces.

In programming languages like __Python__ where functions are first class citizens, we can build an adapter that maps function calls directly to another function call by magic function `__dict__` and `__getattr__`. This particularly solves the issue of incompatible method names between adaptees.

---

### Problem:

Back to our World War themed game. We wrote a `Unit` interface that declares a `move()` and `attack()` method. You are doing fine when you created a `Infantry` class that implements the `Unit` interface.

After that, you are now tasked to add `Tank`s into the game. Tanks are hard to create, and so you found a third-party library that provides a `Tank` class that has a `drive()` and `fire()` method. However, the `Tank` class does not implement the `Unit` interface, and you should not modify the source code of the third-party library in risk of breaking the library.

Therefore, you need to create an `Adapter` class that implements the `Unit` interface, and delegates the `drive()` and `fire()` method to the `Tank` class.


```java
class TankAdapter implements Unit {
    Tank tank;

    public TankAdapter(Tank tank) {
        this.tank = tank;
    }

    @Override
    void move() {
        tank.drive();
    }

    @Override
    void attack() {
        tank.fire();
    }
}
```

Then in your client code:

```java
Tank tank = new Tank();
Unit tankAdapter = new TankAdapter(tank);

// Just like how you would use a Unit
tankAdapter.move();
tankAdapter.attack();
```



<br><br>

---
## 4.0 - __Facade__ 🗂

![Facade](https://refactoring.guru/images/patterns/diagrams/facade/structure.png)

---

__Reference [HERE](https://www.youtube.com/watch?v=K4FkHVO5iac)__

A __Facade__ design pattern, defined:

> __Facade__ pattern provides a unified interface to a set of interfaces in a subsystem.

> __Facade__ defines a higher level interface that makes the subsystem easier to use

__Facade__ isn't too hard to understand. What we are essentially doing is to combined multiple subsystems under one unified interface that is easier to use.

Your software may consist of multiple subsystems, and a business process `processA` may involve `SubsystemA`, `SubsystemB`, and `SubsystemC`. The facade pattern's idea is to have you create a `Facade` class that encapsulates the logic of interacting with the subsystems, and expose a single method `processA()` that the client can call, instead of having to call the subsystems directly from the client. One direct benefit from this is that, if the implementation of `processA()` changes (Eg: require to use a different subsystem), the client does not need to be modified as long as the interface of `Facade` remains the same.

Instead of:

```java
// Client.java

public performProcessA() {
    SubsystemA a = new SubsystemA();
    SubsystemB b = new SubsystemB();
    SubsystemC c = new SubsystemC();
    a.doSomething();
    b.doSomething();
    c.doSomething();
}
```

Abstract the process into a `Facade` class:

```java
// Client.java

public performProcessA() {
    Facade facade = new Facade();

    // Logic of subsystems are encapsulated inside the Facade class
    facade.processA(); 
}
```


The name of the design pattern does makes sense if you think about it. __Facade__ means the front facing part of the house, which hides away internal, complex implementations like water piping, electrical cabling, foundation etc. Clients doesn't even need to know these internal implementation at all to use the system.

Another important concept that should be introduced in system design, is [__Law of Demeter__](https://en.wikipedia.org/wiki/Law_of_Demeter) *(Aka __Principle of Least Knowledge__)*. It means:

* Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.
* Each unit should only talk to its friends; don't talk to strangers.
* Only talk to your immediate friends.

Conceptually, it discourages code pattern like so: `obj1.obj2.obj3` which involves objects that are not inside the current object.

Applying __Law of Demeter__ will inherently cause more classes, each class having a small responsibility rather than few classes that have large amount of responsibility. However it is cases like this that we are more supposed to use __Facade__ design pattern

---

### Problem:

Let's take an ATM machine as an example. Under the hood, it may have to interact with `SecurityChecker`, `CardReader`, `AccountBalanceChecker` etc. Instead of having to interact with each of these from the client, we should create a `ATM` class that provides an unified interface to perform a significant operation such as `withdraw`.

Similarly, if we are designing a application form system that the user are required to submit their information, identity card photo, and related documents. The software may involve subsystems such as `FormValidator`, `PhotoValidator`, `DocumentValidator`, `FileSystem`, etc. Instead of having to interact with each of these from the client code, we should create a Facade class like `ApplicationFormService` class that provides an unified interface to perform a significant operation such as `submitForm(form)` and call the relevant subsystems internally.


<br><br>

---
### 5.0 - Bridge 🌉

![Bridge](https://refactoring.guru/images/patterns/diagrams/bridge/structure-en.png)

<br>


__Reference [HERE](https://www.youtube.com/watch?v=F1YQ7YRjttI)__

Definition:

> The intent of the bridge pattern is to decouple an abstraction from its implementation so that the two can vary independently

The bridge pattern is called __Bridge__ because it is a bridge between two entities, the __abstraction__ and the __implementation__, which can be seen by looking at the UML diagram. 

You might see that is similar to the __Strategy__ pattern, but there is a subtle difference. The __Strategy__ pattern is about __encapsulating__ an algorithm inside a class, and the __Bridge__ pattern is about __decoupling__ an abstraction from its implementation so that the two can vary independently.

The problem that the bridge pattern solves is that, if we have an abstraction and an implementation, and we want to be able to switch between different implementations, we would have to create a new abstraction class for each implementation. This is bad because it violates the __Open-Closed Principle__.

Example: we have a `Shape` interface and concrete classes `Circle`, `Square`, `Rectangle`. Now, we want to also introduce color variants, such as `Red`, `Green`, `Blue`. However, we already had `Circle`, `Square`, `Rectangle` classes, and introducing color variants would mean we have to create subclasses like `RedCircle`, `GreenCircle`, `BlueCircle`, `RedSquare`, `GreenSquare`, `BlueSquare`, `RedRectangle`, `GreenRectangle`, `BlueRectangle`. You can see that introducing a new implementation would mean we have to create a new abstraction class for each implementation, which grows exponentially.

Instead of subclassing, __Bridge__ pattern uses composition. The `Shape` interface will have a `Color` interface as a field. This way, we can switch between different implementations by changing the `Color` field.

Recall the concept of __cartesian product__. The cartesian product of two sets, {A,B,C} and {1,2} would be {(A,1), (A,2), (B,1), (B,2), (C,1), (C,2)}. Now, imagine those are two set of classes which are related. Set A, will have composition of one of the classes in Set B. If one of these two sets are larger, we would have to implement as many classes as the size of the cartesian product, which is bad. In the above example, set A would be the `Shape` interface, and set B would be the `Color` interface. 

---

Say we are developing an application which has `View` class that controls how a list of contents are displayed. Also, we have `Resource` which holds the contents to be displayed. In this case, `View` is the abstraction, and `Resource` is the implementation.

`View` can be of type __Detailed__, __Simple__, and __Mobile__. As for `Resource` we have __Book__, __Movie__ and __Music__. We want to be able to display information about Books, Movies and Musics in our application, while allowing user to choose whatever type of view they want.

Do we make a class for every possible combinations, say `DetailedBookView`, `DetailedMovie`, `DetailedMusic`, `SimpleBook`, `SimpleMovie`, `SimpleMusic` and so on? That would be bad when we decided to suddenly add a new type of `View` or `Resource`!

Instead, using `Bridge` pattern, we decouple the `View` into concrete implementations like `DetailedView`, `SimpleView`, `MobileView`. Each of these views, will contain a resource like `BookResource`, `MovieResource` and `MusicResource`. Those `Resource` will implement a common interface that the `View` may use. For example, each of the `Resource` will implement `getTitle()`, `getAuthor()`, `getDescription()` according to the interface. This way, the classes we made are much less compared to subclassing / cartesian product method.

```java
public interface Resource {
    String getTitle();
    String getAuthor();
    String getDescription();
}

public abstract class View {
    // The abstraction (View) has a bridge to the implementation (Resource)
    protected Resource resource;

    public View(Resource resource) {
        this.resource = resource;
    }

    public abstract String show();
}


public class DetailedView extends View {
    public DetailedView(Resource resource) {
        super(resource);
    }

    @Override
    public String show() {
        return resource.getTitle() + " by " + resource.getAuthor() + " - " + resource.getDescription();
    }
}

public class SimpleView extends View {
    public SimpleView(Resource resource) {
        super(resource);
    }

    @Override
    public String show() {
        return resource.getTitle() + " by " + resource.getAuthor();
    }
}
```

<br><br>

---

### 6.0 - Composite 🗂

![Composite](https://refactoring.guru/images/patterns/diagrams/composite/structure-en.png)

__Reference [HERE](https://www.youtube.com/watch?v=EWDmWbJ4wRA)__

The definition:

> The __Composite__ pattern composes object into tree structure to represent part-whole hierarchies. Composite let client treat individual objects and composition of objects uniformly.

The idea is, no matter whether an object is an individual unit, or it is an composition of multiple objects, we are able to treat it uniformly/transparently under the same interface. 

__Composite__ pattern will consist of `Leaf` and `Composite` nodes. The `Composite` nodes are like a directory/group, responsible to keep track of its composed children (which can also be a `Composite` node itself), while at the same time behaving like its childrens (Having the same interface of `Component`). Because the __Composite__ pattern forms a tree structure, it is natural to also utilize __Recursion__ in computations. 

Thinking from a file system perspective, `Leaf` nodes are files that cannot be further decomposed, while `Composite` nodes are directories that can contain other files (`Leaf`) or directories (another `Composite`). Although the `Leaf` and the `Composite` nodes serve different purposes, they still can be sharing the same interface, like `listFiles()`, `delete()` and so on.

For example, if `delete()` is called on a `Composite` node, it will call `delete()` on all its children, and then delete itself. If `delete()` is called on a `Leaf` node, then it will simply delete itself. This eliminates `if-else` checks in the client code, like so:

```java
// Without Composite
if (file instanceof Directory) {
    for (File child : file.getFiles())
        child.delete();
    file.delete();
} else {
    file.delete();
}
```

to this:

```java
// With Composite
file.delete();  // No matter if it is a directory or a file, it will delete itself and its children
```

### Solution:

The __Composite__ pattern is very commonly used in frameworks that are __Component__ based, like `React.js`. If we create a `Navbar`, `Footer` or `Post` in React, all of them actually inherits from `React.Component`. 

* These concrete components can be either leaf node (Not composed of any other component), or a composition of other smaller components.
* However, leaf node or intermediate node, we are able to treat them uniformly under a common interface. For example, all of the components require a `render()` method to draw the UI on the screen.

Another example is simply the file structure. Say we are developing a program that involves file structure, say we have leaf nodes as `File` like `txt` or `exe`, and intermediate nodes as `Directory` that may contain other files or directories, all of them should have a `listFiles()` method implemented. For simple `File`, `listFiles()` should just return the name of the file, while `listFile()` on `Directory` will combine all the results of `listFile()` of its children by recursion and return it.


<br><br>

---


### 7.0 - Flyweight 🦅

![Flyweight](https://refactoring.guru/images/patterns/diagrams/flyweight/structure.png)

---

__Flyweight__ is a __Structural__ pattern that allows us to use less memory by sharing common parts of state between multiple objects instead of keeping all of the data in each object. It is more of a __Memory Optimization__ pattern.

We start with our system having to instantiate a lot of `Context` objects that contain some state. However, the state of the `Context` objects can actually be divided into two parts: __Intrinsic__ and __Extrinsic__.

* __Intrinsic__ state is the state that is commonly shared among all the `Context` objects. For example, the `Context` object may be a `Car` object, and the `Intrinsic` state may be the `Car`'s `model`, `color`, and `engine`.

* __Extrinsic__ state is the state that is unique to each `Context` object. For example, the `Extrinsic` state may be the `Car`'s `licensePlate`, `owner`, `position`, and `velocity`.

Instead of keeping all the state in each `Context` object, __Flyweight__ pattern suggests to extract the `Intrinsic` state into a separate object called `Flyweight`. This way, we can share the `Flyweight` object among multiple `Context` objects, and only keep the `Extrinsic` state in each `Context` object. This significantly reduces the memory usage if we have a lot of `Context` objects.

Considering that the `Flyweight` object has to be shared, it should be __immutable__. Additionally, the `Flyweight` object should be cached and reused in some container. Conventionally, we would create a `FlyweightFactory` to manage the `Flyweight` objects. If a `Flyweight` object is requested, the `FlyweightFactory` will check if it already exists in the cache, if not, it will create a new one, cache it and return.

```java
public class Flyweight {
    private String model;
    private String color;
    private String engine;

    public Flyweight(String model, String color, String engine) {
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getEngine() {
        return engine;
    }
}



public class FlyweightFactory {
    private Map<String, Flyweight> cache = new HashMap<>();

    // We use car's model as the key
    public Flyweight getFlyweight(String model, String color, String engine) {
        if (cache.containsKey(model))
            return cache.get(model);

        Flyweight flyweight = new Flyweight(model, color, engine);
        cache.put(model, flyweight);
        return flyweight;
    }
}
```

---

Considering we are building a World-War-Themed game (again), the game will create a lot of `Particle` instances. Each `Particle` would have a `position`, `velocity`, `sprite`, `color` properties. However, you soon run into `OutOfMemoryError` because there are too many `Particle` objects created in the scene. 

Upon inspection, the most memory consuming properties are the `sprite` and `color` properties. However, the `sprite` and `color` are actually __Intrinsic__ state, because they are shared by most of the `Particle` objects. For example, `Bullets` and `Explosions` will always have the same `sprite` and `color` value, only the `position` and `velocity` are different.

Therefore, we can use __Flyweight__ pattern to optimize the memory usage. We will create a `ParticleFlyweight` class that contains the `sprite` and `color` properties, and a `Particle` class that contains the `position` and `velocity` properties. The `Particle` class will have a reference to the `ParticleFlyweight` object, and the `ParticleFlyweight` object will be shared among multiple `Particle` objects.

Now whenever a `Particle` object is created, we will first check if the `ParticleFlyweight` object already exists in the `ParticleFlyweightFactory` cache. If it does, we will reuse it, otherwise, we will create a new one and cache it. This way, we can reduce the memory usage significantly.


<br><br>

---


### 8.0 - General Comparison of Structural Patterns 🗂

__Comparison [HERE](https://www.youtube.com/watch?v=lPsSL6_7NBg)__


<br><br>