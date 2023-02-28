# ✏️ Design Principles and Design Patterns ✏️


> Before diving deep into Design Patterns, I suggest to understand the __SOLID Design Principles__ first, as they are the foundation of Design Patterns.



## 1.0 - Introduction


In software development, you may observe certain recurring problems occuring. The same goes for the the Gang of Four (GoF) - Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides, when they observed that there are certain recurring problems that they have to solve. They decided to document these problems and their solutions, and thus, the __Design Patterns__ were born.


Have it ever occurred on you, where you are confused on how should your code be structured and organized? Is your class constructor taking in too much parameters? Having too much if-else statements in your method? These are example of signs your code are getting unmaintainable and you should start considering design patterns!


__Design Patterns__ are well-known solutions to recurring problems in Object-Oriented Programming, which are widely accepted by the software development community.


__Motivations/Reasons to study Design Patterns:__
* No need to reinvent the wheel and waste time, as the design patterns are already well-known and proven to be effective
* Reuse of design ideas - best practices which lowers the cost and increases quality and maintainability

__Characteristics of Design Patterns:__
* __Language Neutral__ - Applicable to all languages *(Particularly OOP languages)*
* __Dynamic__ - Customizable, revisable patterns
* __Incomplete__ by design - promoting customization


<br><br>

---

## 1.1 - Categorization of Design Patterns


__Categorization of Design Patterns:__

|Pattern|Definition|Benefit| Primary OOP Concepts | 
|-|-|-|-|
|__Creational__| Used to create objects in a systematic way | Flexibility (Eg: We can create different subtype of objects from same class at runtime) | Polymorphism |
|__Structural__| Establishes useful relationships between software components in certain configurations | To accomplish a goal, either functional or nonfunctional | Inheritance |
|__Behavioral__| Best practices of objects interaction (Eg: protocols) | | Methods and Method Signatures |


<br><br>

---
## 1.2 - Pattern Context


Do we always use design patterns everywhere after we've learnt it? __NO__. You only use it when it is needed:

To better understand and know when to use a design pattern, we must understand its __Pattern Context__, consisting of the following characteristics:

|Characteristics|Description|
|-|-|
|__Participants__| Classes involved to form a design pattern, and their roles |
|__Quality Attributes__| Nonfunctional requirements - Design Pattern's *Usability, Modifiability, Reliability, Performance etc*|
|__Forces__| Factors or trade-offs to consider when choosing this design pattern |
|__Consequences__| Side effect of using this design pattern *(Eg: better security but worsen performance)* |


<br><br>

---

## 1.3 - Pattern Language


A Design pattern is describled using __Pattern Language__, which consists of the following:

| Characteristic | Description |
|-|-|
|__Name__| The name of design pattern. It should capture the gist of the pattern and be meaningful and memorable. (Eg: Singleton)|
|__Context__| (Explained above) Provides a scenario in which a pattern may be used. Provides more insights when to use it |
|__Problem__| Describes a design challenge which the design pattern is meant to solve|
|__Solution__| Specifies the implementation details of a pattern | 
|__Related Patterns__| Lists out other patterns that is being used, or similar patterns so comparisons can be made |