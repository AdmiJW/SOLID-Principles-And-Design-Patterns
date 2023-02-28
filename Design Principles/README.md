# ✏️ Design Principles - SOLID ✏️

References:

[The S.O.L.I.D Principles in Pictures](https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898)

[SOLID Principles](https://faun.pub/solid-principles-a5c650fcf30)

[SOLID Principles — explained with examples](https://medium.com/mindorks/solid-principles-explained-with-examples-79d1ce114ace)

[SOLID Principles — The ‘O’](https://faun.pub/solid-principles-the-o-2083f3f1aa87)

[SOLID Principles — The ‘L’](https://faun.pub/solid-principles-the-l-6eec477e3cf7)

---
## 1.0 - Introduction


In large projects, the complexity of the codebase can get out of hand quickly if the team working on it does not follow a common design principle - Everyone codes in their own way, and when the time to merge comes, incompatibility issues and bugs may arise. Even if the project successfully made it into production, it may become a headache to maintain in the future.

__SOLID__ is an acronym in software design principles (Especially in Object Oriented Programming) made popular by the software engineer [Robert C. Martin](https://en.wikipedia.org/wiki/Robert_C._Martin). By following the following five design principles, the overall code quality, scalability and readibility can be improved 

The five principles are:

* __S - Single Responsibility Principle__
* __O - Open-Closed Principle__
* __L - Liskov Substitution Principle__
* __I - Interface Segregation Principle__
* __D - Dependency Inversion Principle__


<br><br>

---


## 1.1 - S - Single Responsibility Principle

> Every class/function should have one and only one responsibility

> Every software component should have only one reason to change.

As if the definition isn't clear enough, Single responsibility principle encourages us to:

* __Split the responsibilities to achieve a task into individual pieces, such that each of the classes is only responsible for one and only one specific task.__

* __Whenever changes should be made to the code, it should be always for one and only one fixed reason - the reason will not change in the future.__

Some reason behind this principle is to minimize the possibility of bugs showing up, or even if it does, minimize the impact whenever we try to modify one of the responsibilities. Without Single Responsibility Principle, a developer easily end up accidentally making distinct responsibilities depend on each other, which makes it harder and more expensive to change the behavior of a single responsibility during the next development cycle: instead of a small piece of code gets changed, a larger, more vital part of the system is modified!

---

For example, suppose we are developing our web application, and created a class `CommentSection` which does the following:

* Opening connection to our database
* Retrieving data from the database
* Rendering dynamic webpage content based on the retrieved comments

Based on the Single Responsibility Principle, the `CommentSection` is doing too much by itself. Instead, we could've separated the responsibilities by creating 3 classes:

* `DatabaseConnectionManager` - To open and retrieve the connection to the database
* `CommentDataAccess` - To retrieve data of the comments from the database
* `CommentSectionView` - To render the dynamic webpage content based on the retrieved comments

Ideally, those classes should implement an __interface__ to enforce compatibility to each other, For example, the interface `ICommentDataAccess` can have multiple concrete classes, like `IMySQLCommentDataAccess` or `IMongoDBCommentDataAccess`, and would come in useful if say one day, we want to switch from MySQL to MongoDB.

How does these classes will interact with each other? This would be perfectly explained by with the __Facade__ design pattern, which is a structural design pattern that provides a simplified interface to a library. The facade `CommentSection` will still be there, but under the hood, it will be using the 3 classes mentioned above to do the job instead of having all the logic in a single class!

With Single Responsibility Principle, when we have to make changes like:
* Migrating to a new database
* Switching a ORM (or ODM) which handles queries differently
* Change the way comments are rendered

It is much easier to have make these changes compared to doing it in a single class like `CommentSection`, as stated earlier, it is easy to accidentally make distinct responsibilities depend on each other and mess up the whole system just by making a small change.

---


<br><br>

---
## 1.2 - O - Open-Closed Principle


> “Any class/function should be open for extension, but closed for modification”

Even though the word "extension" and "modification" looks similar, they bear different meaning here:

* Modification means to modify the old code by changing the flow and design of existing classes and functions
* Extension means nothing but inheriting and extending the properties and functionalities of a base class in a child class.

This principle is mainly applicable when we want to implement a new feature into our system. To implement a new feature, do we have to always modify our old code to 'upgrade' it with new feature? __Not really__. Writing new code instead of modifying old code may save you a lot of debugging headache later.

The reason is simple - Changing an already-working code introduces bug easily. You should've experienced it yourself before when you accidentally introduced bugs whenever you try to add new functionailities in your working code. In a large system, introducing bug is expensive and nobody wants that, right?

From this [article](https://faun.pub/solid-principles-the-o-2083f3f1aa87), there are mainly two ways to implement Open-Closed principle - __Interfaces/Abstract Class + Inheritance__, or __Strategy Design Pattern__. 

From __Interfaces/Abstract Class + Inheritance__, we are free to implement/inherit from base class and extend their functionality (methods), thus the "*open for extension*" part.

As for __Strategy__, methods in a class are actually an object itself, and is easily swappable. This allows us to create a new __Strategy__ for an already existing object which uses the same __Strategy Interface__. For more info, look into `Behavioral Design Pattern > Strategy`.

---

In our website, we had several user memberships - __Regular, Bronze, Silver, Gold__. Different memberships will have access to different types of content on our website as well as different privillages. 

There will soon be headaches if such situation occurred to you:

* Add a new membership. Eg: __Platinum__. 
* Add a new functionality in the system that requires checking for user membership types.

```java
Content accessExclusiveContent(Member member) {
    if (member.type == 'Regular') // Throw 403 unauthorized
    else if (member.type == 'Bronze') 
    else if (member.type == 'Silver') 
    else if (member.type == 'Gold') 
    // This goes on and on...
}
```

For adding the __Platinum__ membership, you'll have to modify __EVERY__ `if else` branch to include the new membership type - perhaps inside `accessExclusiveContent()`, inside `checkPrivillages()`..., have I overlooked any other methods? Imagine doing this in a large codebase!

Or if I am adding a new functionality that requires checking for user membership types, I'll have write this messy `if else` statement again, and again, and again. 


Instead, we could define an abstract `User` that is inherited by `RegularUser`, `BronzeUser`... and have the functionalities implemented inside each of these classes (Using Strategy, the Strategy would be decided in the constructor). Now instead of modifying legacy code (nightmare), we are writing new code to implement our new feature - Create a new concrete class `PlatinumUser`, or define a new abstract method of the new functionality that must be implemented by every subclasses. If bug ever arises, we have no need to look into the old code, as it is highly likely that the bug occurs from our newly written code.


<br><br>

---
## 1.3 - L - Liskov Substitution Principle

> Let Φ(t) be a property provable about objects t of type T. Then Φ(s) should be true for objects s of type S where S is a subtype of T.

Wait, what's this mathematical complex stuff? Here is more understandable version:

> Objects of a superclass shall be replaceable with objects of its subclasses without breaking the application (& behave in the same way).

Say we have class `A` which acts as a base class, and class `B` which inherits from class `A`, and perhaps overridden some of its methods.

Basically, 

```java
class A {...}
class B extends A {...}
```

Now, Liskov Substitution Principle tells us, if one day we suddenly decided that all the places in our code which uses `A`'s object, will be swapped with objects of `B`, the system should not crash, as `B` is supposedly to act the same as `A` like parent and son, just with additional functionalities.

```java
A a = new A();
a.getData();    // Work as it is

a = new B();
b.getData();    // According to Liskov Substitution Principle, this should work perfectly fine!
```

This brings us a reminder: We are not free to do whatever we want even if we possess the power to override the parent classes' method. If `A.getData()` returns data in JSON form, `B.getData()` should also return JSON data. The meaning of the method inherited from the parent class should never change.

Commonly, a Liskov Substitution Principle is violated when:

* When a method from an interface is not implemented in the subclass. Eg: you write `throw new NotImplementedException()` in overridden method.

* An exception in the subclass for behaviour from the parent class which is not fulfilled.

* The subclass method overrides the base class to give a new meaning. 

---

We are developing a chat application. Each `User` should have a `sendMessage()` method which is expected to return a `message` string which will be displayed on the client's screen locally. 

Soon, you want to implement `MutedClients`, which the client has no privillage to send messages. Therefore, do we override the `sendMessage()` to throw exception? or to return empty string? or add if-checks in the displaying logic? This clearly violated the LSP.

Instead, this problem would probably be fixed from the hierarchy. Instead of `User`, we would have interfaces like `MessageSender` that defines `sendMessage()`, which `MutedClients` should not implement, but `User` does.

<br>

---
## 1.4 - I - Interface Segregation

> Clients should not be forced to depend on methods that they do not use.

When writing a class and its methods, you do not write methods that is never used by the class do you? However, this is not the case for interfaces. 

The problem is, you might have decided to create an interface that seems plausible for your use case, but is actually too general (too many methods declared). Later as you create more and more specific concrete classes, only then you'll realize that some of the methods are actually meaningless to that specific concrete class, yet you are still forced to implement it due to inheriting from that interface. We shouldn’t force a class to execute an action which is not needed!

To allevate this problem, Interface Segregation suggests to avoid interfaces that are too general, and to break it down into smaller interfaces that enforces smaller amount of methods to override.

---

In your chat application, you have defined an interface `Client` in which the concrete classes is forced to implement `sendMessage()` and `retrieveMessage()` method. You are okay with the interface until one day, you decided to implement a `MutedClient` concrete class which is unable to send messages (and supposedly do not even have `sendMessaage()` in the first place). What should you do with the `sendMessage()`? Do you simply leave the implementation blank? Do you throw exceptions?

As previously stated, perhaps the `Client` interface can be further broken down into smaller interfaces like `MessageSender` and `MessageRetriever`. This solves the problem as `MutedClient` will simply not implement the `MessageSender` interface.

<br>

---
## 1.5 - D - Dependency Inversion Principle

> “High-level modules should not depend on low-level modules. Both should depend on the abstraction.”

> Abstractions should not depend on details. Details should depend on abstractions

Let's clarify some technical terms here:

* __High-level Module__ - Classes that uses an low-level modules (like tools) to perform a task. Eg: `Customer` has a `paymentMethod` property (the tool)

* __Low-level Module__ - The 'tool' used by high-level module to execute the action. Eg: `CashPayment`, `DebitCardPayment`, `OnlineBankingPayment`

* __Abstraction__ - Interface that connect the high-level module with the low-level module. Eg: We have `PaymentMethod` interface in which its concretions must implement the `pay()` method.

* __Details__ - How the low-level module actually works. Eg: `OnlineBankingPayment` will ask for login details to the bank, while `DebitCardPayment` will ask for card number and the number on the back.

As you might've guessed, the principle states that a class should not be fuse with the specific tool it uses to perform an action. Instead, both the tool (low-level-module) and the class (high-level module) should obey the interface defined. This ensures that the high-level module does not need to know how the tool works, yet still able to perform the action by simply knowing the interface - How to tell the tool to perform its specific action. This is beneficial, as we are able to make changes to the tool without affecting the high-level module, as long as the interface remains the same!