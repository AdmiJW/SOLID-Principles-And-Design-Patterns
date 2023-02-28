# 🤹 Behavioral Design Patterns 🤹

---
## 1.0 - __Strategy__ 🗺

![Strategy](https://refactoring.guru/images/patterns/diagrams/strategy/structure.png)

---

> __Reference [__HERE__](https://www.youtube.com/watch?v=v9ejT8FO-7I)__

__Strategy__ design pattern, by formal definition, goes as follows:

> Strategy design pattern defines a family of algorithm, encapsulates them and make them interchangable.

What the __Strategy__ pattern is saying is that the family of algorithms (methods) within a class can be extracted out into a separate class called `Strategy`. This allows the class to be more flexible, as the `Strategy` can be changed at runtime.

Let's say in our client program, we have an method of `operation()` that looks like so:

```java
void operation() {
    if (condition1) doOperationA();
    else if (condition2) doOperationB();
    else if (condition3) doOperationC();
    else doOperationD();
    //...
}

void doOperationA() {...}
void doOperationB() {...}
void doOperationC() {...}
void doOperationD() {...}
```

The problem with this is that, if we want to add a new operation, we will have to modify the client code by adding a new `doOperationE()` method as well as modifying the `if-else` branch. This is not ideal according to the __Open-Closed Principle__.

Instead, we could extract the logic of `doOperationA()`, `doOperationB()`, `doOperationC()` and `doOperationD()` into a separate class called `Strategy`:

```java
public interface Strategy {
    void doOperation();
}

class StrategyA implements Strategy {
    public void doOperation() { 
        // A's logic    
    }
}

class StrategyB implements Strategy {
    public void doOperation() { 
        // B's logic    
    }
}

// so on...
```

and the client would compose the `Strategy` object into the class:

```java
class Client {
    Strategy strategy;

    public Client(Strategy strategy) {
        this.strategy = strategy;
    }

    void operation() {
        strategy.doOperation();
    }
}
```

---

### Problem:

We have learnt about inheritances. Let's say we have a `Duck` abstract class, which will have 2 methods: `quack()` and `fly()`.

Now, we will have 2 child classes inheriting from this `Duck` class: `CityDuck` and `WildDuck`.

Let's imagine some problem here:

* What if both ducks share the same `quack()` behavior? We shouldn't implement the function into the base class `Duck` because maybe there are more type of `Duck` that doesn't share the same `quack()` behavior.

* Let's say that `CityDuck` doesn't know how to fly. We are still having to implement the `fly()` method regardless due to inheriting from the `Duck` class, breaking the __Interface Segregation Principle__.

The inheritance way of solving this problem, especially case 1, is to __use more inheritance__. For the `Ducks` that will have certain `quack()` behavior, we may make a `NormalQuackingDuck` class, that will be in turn inherited by the `CityDuck` and `WildDuck`.

However, as the program grows and more ducks are introduced, the inheritance tree quickly expands, and complexity goes out of hand (Inheritance forms a tree, and common behaviors cannot be shared __Horizontally__ - Like how `CityDuck` and `WildDuck` cannot share the same `quack`)

---

### Solution:

You may describe __Strategy__ pattern as *"using composition instead of inheritance"*. What if each of these methods (Eg: `quack`), can be an object and assigned to a `Duck` instance? That quickly solves the sharing problem faced earlier!

Instead of making deep, inheritance tree like introducing `NormalQuackingDuck` and whatsoever, let's instead extract those behaviors out and make them objects! (For __Java__, use abstract classes):

* `QuackBehavior` - must define `quack()` for those inheriting it
* `FlyBehavior` - must define `fly()` for those inheriting it

Now, since both ducks is able to quack and fly, the class definition would look something like:

```java
class Duck {
    QuackBehavior quackingBehavior;
    FlyBehavior flyingBehavior;
    ...

    void quack() {
        this.quackingBehavior.quack();
    }
    ...
}
```

Since both `CityDuck` and `WildDuck` share the same quacking behavior, we would define a __concrete__ `QuackBehavior` instance that can be used by both classes:

```java
class NormalQuackBehavior extends QuackBehavior {
    public void quack() {...}
}
```

---

Now, we can get rid of the inheritance tree in its entirety! All the behaviors of `Duck`s are __extracted__ away and implemented in the form of composition rather than inheritance!

It become a lot easier to extend the functionality, whether we want to add a `RubberDuck`, or add a `swim()` method

<br><br>



---
## 2.0 - __Observer__ 👀

![Observer](https://refactoring.guru/images/patterns/diagrams/observer/structure.png)

---

__References [HERE](https://www.youtube.com/watch?v=_BpmfnqjgzQ)__

__Observer__ pattern, official defined as:

> A one to many dependency, where when the core dependency changes its state, all the other dependencies are notified

Considering your application has a data source or a state source, and there are multiple clients that rely on this data source. You want to make sure that whenever the data source changes, all the clients are notified of this change and update themselves accordingly.

Think of this as a website that shows live data and updates in real time. Whenever the data in the database changes, the website that is displayed on every client's web browser is automatically updated without the need of refreshing the page. This is the __Observer__ pattern in action.

The main issue is: How do the clients know when the data source changes? Do they have to keep checking on the data source at fixed intervals (Eg: query the data source every 1 second)? This is not ideal, as it is a __poll__ process that is taxing on the CPU cycle, especially if the data source does not change often (If data source only change every 24 hours, polling every 1 second is extremely redundant and wasteful, but you don't know how often the data changes!).

In the __Observer__ pattern, the data source is called the `Observable`/`Subject`/`Publisher` and the clients are called the `Observer`. Here's how the __Observer__ pattern works:

1. The `Observer` can subscribe themselves to the `Observable`.
1. The `Observable` will keep track of all the `Observer`s that are subscribed to it in a data structure.
1. Whenever the `Observable` changes its state, it will notify all the `Observer`s that are subscribed to it by referring to the list of subscribed `Observer`s.

With this, the responsibility of checking on the `Observable` is shifted from the `Observer` to the `Observable`. The `Observable` will notify the `Observer` whenever there is a change in state.


```java
// Observable
interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Observable observable);
}

// Observer
interface Observer {
    void onUpdate(Observable observable);
}

// Concrete Observable
class WeatherStation implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private int temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.onUpdate(this);
        }
    }

    // When temperature changes, notify all observers
    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyObservers(this);
    }

    public int getTemperature() {
        return temperature;
    }
}

// Concrete Observer
class Phone implements Observer {
    public void onUpdate(Observable observable) {
        System.out.println("Phone: " + ((WeatherStation) observable).getTemperature());
    }
}

class Website implements Observer {
    public void onUpdate(Observable observable) {
        System.out.println("Website: " + ((WeatherStation) observable).getTemperature());
    }
}
```

---

### Problem:

Think of this as a Weather System, where when the Station changes the weather state, all the subscribed clients (your phone, other weather websites) needs to be notified in real time.

This design pattern essentially helps to change from __poll__ process to __push__ process. In __polling__, the observers has to keep checking on the observable at fixed intervals. However, this process may be taxing on the observable (server), and still possibly causes delay in information (Say weather updates in 1s but observer polling interval is 10s). Instead in __push__, the server notifies all the observers about the update.

---

### Solution:

Conceptually, __Observer__ design pattern is implemented by first having two __interfaces__:

* `Observer` - Need to implement `addObserver`, `removeObserver` and `notify` method on inherited classes
* `Observable` - Need to implement `onUpdate` method on inherited classes

The concrete `Observable` classes, like `WeatherStation`, should keep track of a list of `Observable`s and notify them whenever there is an update.

Also, it is suggested that concrete `Observer`s should keep a reference to the `Observable` they are subscribed to, to get the state whenever they want, say through `weatherStation.getWeather()`


<br><br>



---
## 3.0 - __Command__ 🗯

![Command](https://refactoring.guru/images/patterns/diagrams/command/structure.png)

__Reference [HERE](https://www.youtube.com/watch?v=9qA5kw8dcSU)__

The definition:

> __Command__ pattern encapsulates a request as object, thereby letting us parameterize other object with different request queue or by require and support undoable requests.

In essence, __Command__ design pattern is similar to __Strategy__ pattern. 

* In __Strategy__, algorithms of a class are encapsulated in the form of objects, and can be swapped out at runtime.

* In __Command__, an `Invoker` object can be given a `Command` object, and the `Invoker` can invoke the `Command` object at any time (Eg: `onClick`). The `Invoker` does not need to know the details of the `Command` object, and vice-versa.

The __Command__ pattern consists of `Invoker` (the one containing the `Command`), `Receiver` (the object that the `Command` will manipulate), and `Command` object themselves. The `Invoker` would contain a dynamic, swappable `Command` field which can be invoked via method like `executeCommand()`. An abstract `Command` would hold a reference to the `Receiver` object, and commonly have an `execute()` and `unexecute()` method that performs action on the `Receiver` object. It will be extended by concrete `Command`s like `TurnOnCommand` and `ChangeSpeedCommand`

---

Imagine you are designing a `Button` GUI element in Java. Without __Command__ pattern, you have to create subclasses of `Button`s, like `SubmitButton`, `OkButton`, `CancelButton`, etc. Each of these subclasses would have their own `onClick()` method, which would perform different actions. This is not ideal, as you risk breaking things if you change the base `Button` class, and the `onClick()` logic may not be limited to just button clicks (Eg: Submit is also done via pressing ENTER on the keyboard), resulting in code duplication.

Instead, with __Command__ pattern, the `Button` is essentially the `Invoker` class that contains a `Command` field, and a `onClick()` method that invokes the `Command`'s `execute()` method. This way, you can dynamically swap out the `Command` field, and the `Button` class is not coupled to any specific `Command` class and can be swapped out easily via the `setCommand()` method.


---

### Problem:

Think of this: You are programming for a reprogrammable/configurable remote control, where each button can be configured to perform different actions. How would you program such system?

Maybe you suggest to have a internal state, such as a Enum Map keeping track of what each button does:

```java
public enum ButtonAction {
    TURN_ON,
    TURN_OFF,
    CHANGE_SPEED
}

public class MyRemoteControl {
    private ButtonAction button1Action = ButtonAction.TURN_ON;
    private ButtonAction button2Action = ButtonAction.CHANGE_SPEED;

    // Configurable button actions
    public void setButton1Action(ButtonAction action) {
        button1Action = action;
    }

    public void onButton1Pressed() {
        onPerformAction(button1Action);
    }

    // This is already looking bad with a lot of if-else
    public void onPerformAction(ButtonAction action) {
        if (action == ButtonAction.TURN_ON) ...
        else if (action == ButtonAction.TURN_OFF) ...
        else if (action == ButtonAction.CHANGE_SPEED) ...
    }
}
```

---

### Solution:

However, if you learnt __Command__ pattern, where methods themselves are already objects, suddenly we can just have our code like so:

```java
class RemoteControl {
    Command button1Command = new TurnOnCommand(receiver);
    Command button2Command = new ChangeSpeedCommand(receiver);

    public void setButton1Command(Command command) {
        button1Command = command;
    }

    public void onButton1Pressed(args) {
        button1Command.execute(args);
    }
}
```

The actual implementation of the command is now encapsulated inside the concrete `Command` itself. For example in the `TurnOnCommand`, `exeucte()` will contain `receiver.turnOn()`.

Now, since the `Command` itself had became class attribute, the `Command` suddenly become very easily swappable in the `Invoker`!

---

### Furthermore...

That's not all the benefits of __Command__ pattern! Think: functions themselves cannot possibly be saved in data structure (Unless you are using a language where functions are first class citizens, like JavaScript), but `Command` objects can! This opens up the possibility of:

1. Storing the `Command`s inside a data structure, for logging and history purposes

1. Each `Command` may have an `unexecute()` method, which makes functionality like __Undoing__ extremely easy to implement with a __Stack__ data structure!

1. Each `Command` can be invoked by a `Scheduler` object, which can be used to implement __Delays__ and __Repeating__ functionality!

<br><br>

---


## 4.0 - Iterator ⛓

![Iterator](https://refactoring.guru/images/patterns/diagrams/iterator/structure.png)

__Reference [HERE](https://www.youtube.com/watch?v=uNTNEfwYXhI)__

The definition of __Iterator__ pattern is:

> The __Iterator__ pattern provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

The __Iterator__ pattern provides an interface for iterating over and enumerating all the items in the collection while encapsulating the iteration logic. This is applicable if you have an aggregate object that contains a collection, and you have to iterate over the collection without letting the client code know the underlying data structure of the collection (array, tree, set, stack etc).

Wouldn't it be nice if instead of:

```java
// Notice here that the collection is not necessary List!
// If it is a more complex data structure like a tree, you'll have to use a different method to iterate over it.
for (int i = 0; i < collection.getList().size(); i++) {
    Element e = collection.get(i);
    doSomething(e);
}
```

You can do:

```java
for (Element e : collection)
    doSomething(e);
```

We shall be quite familiar with iterators, especially those coming from `Python`. `range()`, `for ... in ...` all utilizes range (And __Generators__). 

The __Iterator__ pattern has certain advantages:

* Abstract away underlying data structure (Set, List, Tree..., all are iterable!)
* Do not expose the whole data structure at once - You'll only get the next element when you call `next()`.
* Lazy evaluation, especially effective on infinite collection like Fibonacci number generator.
* Avoiding mutation of the underlying data
* Ability to pause iteration and resume it whenever possible.
* Ability to have multiple iterators on the same collection (Eg: a tree can have multiple iterators: preorder, postorder, inorder etc)

With iterator, we are able to abstract away on how to iterate over a data structure. No matter we have arrays (which iterate by index), linked list (iterate by pointer), tree (preorder, postorder, inorder, level order), or set etc, we will always use the common interface of __Iterator__.

---

### Solution:

The common way of implementing an __Iterator__ is by first defining two interfaces, `Iterable` and `Iterator` 

* The `Iterable` will only require to have the method `getIterator()`. In Python, this is done by `__iter__()`.
* The `Iterator` will have several methods, like `hasNext()`, `next()`, and `current()` (maybe?)

With these two classes, __Iterator__ pattern is very easily implemented. 

<br><br>

---

## 5.0 - State 🎞️

![State](https://refactoring.guru/images/patterns/diagrams/state/structure-en.png)

__Reference [HERE](https://www.youtube.com/watch?v=N12L5D78MAA)__

The __State__ pattern states that:

> The __State__ pattern allows object to alter its behavior when its internal state changes. The object will appear to change its class.

The __State__ pattern, essentially is the design pattern's way of implementing a __State Machine__, involving states, connected by transitions, and producing outputs. With the __State__ pattern, we are able to perform different actions from `operation()`, based on the state that we are currently in.

Without knowing the __State__ pattern, it is common to write a finite state machine using `if-else` statements, somewhat like this:

```java
void operation() {
    if (state == State.STATE1) {
        // Do something
    } else if (state == State.STATE2) {
        // Do something else
    } else if (state == State.STATE3) {
        // Do something else
    }
}

void setState(State state) {
    this.state = state;
}
```

Again, not very maintainable, and not very scalable. The __State__ pattern encapsulates each state's logic into a single class, handles transition of states, therefore making it easier for the program to maintain and scale.

The __State__ pattern consist of, first, the `Context` interface, which we can think as the entire of the __State Machine__. For example, a `Game` that will have different states, is considered a `Context`. The `Game`'s `render()`, `update()`, and `input()` method will have different behavior depending on the `State` that it is currently holding. Then of course, the `Context` will contain a single concrete `State` field that represents the current state of the `Context`. The `State` object holds a reference to the `Context` object, so that it can make a transition and change the `State` of the `Context` when necessary.

The __State__ pattern is very commonly used in games or anything that can be represented in different states. It is much more easier to use compared to, say using `if-else` statements to check for individual boolean values to determine the next action.

---

### Example:

The Gate/Validators that we see in Metro stations which require scanning of tickets or Identification Card(IC), can be a good example where we will use the __State__ pattern.

The Metro Gate may have different states such as `GateOpenState`, `GateCloseState`, and `GateProcessingState`. Each one of these states should implement the common interface `GateState`, which includes the output methods `getSensorLight()` and `getMessage()` as well as their own transition function `transition()` depending on the input. For example, within the `GateProcessingState`, if `transactionSuccess()` is invoked, then the state should be transitioned to `GateOpenState`.

<br><br>

---

## 6.0 - Null Object ⁉️

![Null Object](https://sourcemaking.com/files/v2/content/patterns/Null_Object2.png)

__Reference [HERE](https://www.youtube.com/watch?v=adGhT_-JbZI)__

The __Null Object__ design pattern is defined as 

> A __null object__ is an object with no referenced value or with defined neutral ("null") behavior. The null object design pattern describes the uses of such objects and their behavior (or lack thereof)

Does it ever occurred to you when writing code, that your method takes in a argument, and you realize that you need to __null check__ the argument before proceeding with your normal logic? Isn't it somehow better if we are able to treat `null` as if it is an expected object without __null checking__?

This is exactly what __Null Object__ pattern proposes. Instead of having to check for `null` value which introduces more branching and complicate our code, we will introduce a __Null Object__, which implements the same interface as the object we'll expect, yet the behavior is same as that of what we'll do in case of `null` value. Using this pattern eliminates the following code:

```java
if (obj == null)
    // Code that executes when obj is null
else
    // Code that executes if non-null
```

The __Null Object__ can be an extension of the __Strategy__ pattern, where concrete classes don't exactly have to own a particular method (Strategy), by defining a `NoMethod` class that will be used instead. 

Eg: for the `Duck` example before, we can have Strategies like `NormalQuackStrategy`, but for ducks that don't quack, we can have `NullQuackStrategy` using the __Null Object__ pattern.

---

### Situations:

* We are developing a forum website. In `PostList` we have a `addPost(post)` method. We realize that the `post` argument passed in may be null. In this case, instead of say:

    ```java
    if (post == null)
        return "<li></li>";
    else
        return "<li>" + post.getEscapedText() + "</li>";
    ```
    What we can do to eliminate the above snippet is to define a `EmptyPost` object, that when `getEscapedText()`, `getAuthor()` or `getDate()` is called, returns empty string.

* We are developing a game. Our character, `Avatar` has 4 methods `moveLeft()`, `moveRight()`, `moveUp()`, and `moveDown()`. We have decided to use __Strategy__ pattern so that each methods are actual object themselves, like `AvatarStrategy moveLeftStrategy = new MoveLeftStrategy(this)`. 

    Then, we decided that in some levels, the `Avatar` shouldn't be able to move up or down. In this case, we can define a `NullMoveStrategy` and assign it to the strategy responsible for moving up and down

* We are designing a GUI application. We have `OKButton` and `CancelButton` in our message box. We've decided to implement __Command__ strategy so that each button is tied to a single `Command`. Now, in some circumstances we may want to disable the `OKButton` normal behavior. Then using __Null Object__ pattern, we tie the `OKButton` with the `NothingCommand` that does nothing upon `execute()`.

* When `getIterator()` is called on a collection that is empty, an instance of `EmptyIterator` may be returned, which `hasNext()` is always set to false. (Or in __Python__, always raise `StopIteration`)

<br><br>

---

## 7.0 - Visitor 🏃🏼

![Visitor](https://refactoring.guru/images/patterns/diagrams/visitor/structure-en.png)

The __Visitor__ design pattern, definition:

> The __Visitor__ design pattern is a way of separating an algorithm from an object structure on which it operates. A practical result of this separation is the ability to add new operations to existing object structures without modifying the structures

This design pattern is exactly what the name is implying. We have a object, that will be "visited" by a __Visitor__, which will allow us to add new behaviours or modify existing behaviours of the object without modifying the underlying code structure of the visited object. 

In the __Visitor__ pattern, the `Element` that will be visited by the `Visitor`, will generally have an `accept(v: Visitor)` method, in which what it does is extremely simple: it calls the `Visitor`'s `visit(this)` method so that the `Visitor` can perform its own logic on the `Element`. `element.accept(visitor)` is called from the client code.

---

### Example:

You have set up a online store consisting of multiple `Product`s. Suddenly, your boss comes to you and asks you to add the functionality to export the `Product` into both JSON and XML format. 

Initially, you might thought to add a `exportToJSON()` and `exportToXML()` abstract method to the base `Product` class and have the subclasses to implement it. However, your boss denies this idea, saying that they can't risk breaking the existing code that are already in production. Also, not all `Product`s are needed to be exported to both JSON and XML format.

In this case, we can use the __Visitor__ pattern. We will create a `ProductDataExportVisitor` interface, which will have a `visit(ProductA)`, `visit(ProductB)` etc methods. Then, we will create a `JSONProductDataExportVisitor` and `XMLProductDataExportVisitor` class that implements the `ProductVisitor` interface. 

Then, we will introduce a new interface `VisitableProduct`, which will have a `accept(ProductVisitor)` method. Back to the problem statement, only the `Product`s that are needed to be exported will implement the `VisitableProduct` interface!

```java
public interface ProductVisitor {
    String visit(ProductA product);
    String visit(ProductB product);
    String visit(ProductC product);
}

public class JSONProductDataExportVisitor implements ProductVisitor {
    @Override
    public String visit(ProductA product) {}

    @Override
    public String visit(ProductB product) {}
}

public class XMLProductDataExportVisitor implements ProductVisitor {
    @Override
    public String visit(ProductA product) {}

    @Override
    public String visit(ProductB product) {}
}

public interface VisitableProduct {
    public abstract String accept(ProductVisitor visitor);
}

public class ProductA extends Product implements VisitableProduct {
    @Override
    public String accept(ProductVisitor visitor) {
        return visitor.visit(this);
    }
}

public class ProductB extends Product implements VisitableProduct {
    @Override
    public String accept(ProductVisitor visitor) {
        return visitor.visit(this);
    }
}
```

<br><br>

---

## 8.0 - Chain of Responsibility 🏃🏼

![Chain of Responsibility](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/structure.png)

The definition for __Chain of Responsibility__ is:

> The __Chain of Responsibility__ involves sending data to an object for processing, and that object can forward the request to other objects that may continue to use it. This decouples the request and its processing.

Usually, we want to process some input data. The data can either be __(1)__ processed under several operations, or there are __(2)__ several possible operations possible, but one and only one is able to process the data given.

Without __Chain of Responsibility__, you might implement it as so:

__(1)__
```java
if (data.satisfyCondition1)
    processData1(data);
if (data.satisfyCondition2)
    processData2(data);
if (data.satisfyCondition3)
    processData3(data);
```

__(2)__
```java
if (data.satisfyCondition1)
    processData1(data);
else if (data.satisfyCondition2)
    processData2(data);
else (data.satisfyCondition3)
    processData3(data);
```

This `if else` branching may bloat up as the condition grows. What __Chain of Responsibility__ suggests is to separate each condition into individual `Handler` classes, and the data is passed through the chain of `Handlers`, just like a water flowing through a pipe, with processing capability. You might know this as a __Pipeline__ or __Pipe and Filter__ architecture.

Within each `Handler`, it must contain a `handle(data)` method, which will either process the data, or pass it to the next `Handler` in the chain. The `Handler` may contain a field `next` which holds the reference to the next `Handler` in the chain, which is usually set in the constructor. The `handle(data)` may call `next.handle(data)` if it needs to pass the data to the next `Handler`.

This pattern is seen commonly in __Middlewares__, where data is processed before reaching the terminal. For example, in web frameworks like `Node.js` or `Spring`, HTTP requests are processed and modified by a series of __Middlewares__, such as `Authentication`, `Authorization`, `Logging`, `Caching`, `Body Parsing`, `Session Management`, `Error Handling` etc, before reaching the final `Controller` that handles the request.

---

### Problem Statement:

You are developing a online ordering system. Inside your `submitOrder()` method, you perform several responsibilities, such as:

* Check if the user is logged in and authorized to place order
* Check if the order is valid format
* Check if the user has sufficient balance to place the order
* Check if the ordered stock is available in the warehouse

You code all these checks inside the `submitOrder()` method, and it is getting bloated. You want to separate the checks into individual classes, and pass the order data through the chain of `Handlers`.

Instead of:

```java
// Not scalable
public void submitOrder(Order order) {
    if (!user.isLoggedIn())
        throw new Exception("User is not logged in");
    
    if (!user.isAuthorized())
        throw new Exception("User is not authorized");

    if (!order.isValid())
        throw new Exception("Order is not valid");

    if (!user.hasSufficientBalance(order))
        throw new Exception("User does not have sufficient balance");

    if (!warehouse.hasStock(order))
        throw new Exception("Warehouse does not have sufficient stock");

    // Place order...
}
```

Perform the checks using __Chain of Responsibility__:

```java
// Each responsibility is delegated into individual classes, modification is local to each Handlers
public void submitOrder(Order order) {
    Handler handler = new LoginHandler();
    handler
        .setNext(new AuthorizationHandler())
        .setNext(new OrderValidationHandler())
        .setNext(new BalanceHandler())
        .setNext(new StockHandler());

    handler.handle(order);
}
```


<br><br>

---

### 9.0 - Template Method 🔩

__Reference [HERE](https://www.youtube.com/watch?v=7ocpwK9uesw)__

The definition is as follows:

> The __Template Method__ pattern defines the skeleton of an algorithm in an larger operation, deferring the implementation of some steps to subclasses. __Template Method__ let subclasses define certain steps of the algorithm without changing the overall algorithm structure.

You may observe that in large enough operations, there may be some steps that are common among subclasses. 

* For example, in both `CSVFileParser` and `XMLFileParser`, although how they parse the file content is different, the part where they obtain the file handler and close the file is identical. 

* Another example using games, the `FlyingEnemy` and `GroundedEnemy`'s AI on how they perform attacks may be different, but the logic to, for example, check if the player is within range, and return to their spawn point when the player is out of range, is identical.

Without applying any patterns, this would result in code duplications.

__Template Method__ suggests us to break down the large operations into individual methods, which can be split into 2 groups + 1 template method:

* __Abstract steps__ - Logic that are distinct and specific to each subclass. These steps must be implemented by every subclass

* __Optional steps__ - Logic that might be common among subclasses. These steps already have some default implementation, but still can be overridden by the subclasses if needed

* __Template method__ - The method that combines all the abstract steps and optional steps to complete the operation.

For the `CSVFileParser` and `XMLFileParser` example, we can create an abstract class `FileParser` with these method groups:

* __Abstract steps__ - `parseContent()`
* __Optional steps__ - `openFile()`, `closeFile()`
* __Template method__ - `parse()`

As you can see, this prevents duplicating core algorithm that does not vary among subclasses, yet providing freedom for the logic that should vary by subclasses.

---

For example, we are creating a method for `pdf`, `txt` and `csv` files that reads the file, processes accordingly and output to the user screen. Perhaps we will implement 3 classes, `PdfParser`, `TxtParser`, and `CsvParser`. However, we soon realize that the `parse()` method of three classes have some duplicate codes, mainly on the reading file and printing part. In this case, we could utilize the __Template Method__ to create an abstract class `Parser` and have the template method inside.

This pattern can be applied to the following example situations:

* __Sorting algorithm with Custom comparator__ - The core sorting algorithm is the same, just that the comparator varies depending on use case and the datatype to sort. Eg: The core sorting algorithm is bubble sort, but the comparator may be descending order, ascending order, or even custom comparator for `String` or `Integer` etc.

* __Hooks__ - Before and after the data is processed, hooks must be called.

* __ORM__ - When we call `save()` on a `Model`, like `User`, there are same algorithm that are executed to save the data to the database. However, the logic to open the database connection, and close the database connection, is identical.

---

### Solution:

To implement __Template Method__, we would have to first have an abstract class which has the template method (incomplete algorithm) defined. In above example, the template method, `parse()` would look like:

```java
void parse() {
    ... // Code for reading the file
    process();  // This is the part that varies depending on concrete parsers
    ... // Code for output
}
```

With this, we will also make the `process()` method to be mandatory to be implemented in subclasses, namely `PdfParser`, `TxtParser`, and `CsvParser`. 


<br><br>

---

### 10.0 - Memento 📝

![Memento](https://refactoring.guru/images/patterns/diagrams/memento/structure1.png)

> Memento is a behavioral design pattern that lets you save and restore the previous state of an object without revealing the details of its implementation.

The __Memento__ design pattern is a more specialized deisgn pattern that deals with saving the state of the application and the functionality to restore the state of the application, such as when an `undo()` is performed.

Let's say you are designing the functionality to undo the last action performed by the user in your application. Every time the user performs an action, you have to extract the state of, let's say, `MyAppState` and store it in the history stack. However, that is exactly the part where design decisions has to be made:

In order to create a copy of `MyAppState`, you need to have full access to all the fields of `MyAppState`. However, if you make all of the fields of `MyAppState` public, you are exposing the internal implementation of `MyAppState` to the outside world (which is bad). On the other hand, if you don't make the fields public, you will not be able to create a copy of `MyAppState` from outside of the class.

The __Memento__ design pattern suggests that the copy of the state of `MyAppState` should be created by `MyAppState` itself - The `MyAppState` class is the `Originator`, and it has a method `save()` that produces a copy/snapshot of the state, called `Memento`. When the time comes to restore the state, the `MyAppState` class's `restore()` method will be invoked with the `Memento` object passed in.

Furthermore, the `Memento` object has to be stored somewhere, and according to the __Memento__ design pattern, the `Memento` object should be stored in the `Caretaker` class. The `Caretaker` class is responsible for storing the history of `Memento` objects created, and provides abstracted interface to perhaps `undo()` or read the history of `Memento` objects.

```java
class MyAppState {
    private String state;

    public Memento save() {
        return new Memento(this, state);
    }

    public void restore(Memento memento) {
        this.state = memento.getState();
    }
}

class Memento {
    private MyAppState originator;
    private String state;
    
    public Memento(MyAppState originator, String state) {
        this.originator = originator;
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

class Caretaker {
    private List<Memento> history = new ArrayList<>();

    public void save(MyAppState originator) {
        history.add(originator.save());
    }

    public void undo(MyAppState originator) {
        if (history.isEmpty()) return;

        Memento memento = history.remove(history.size() - 1);
        memonto.restore();
    }
}

```