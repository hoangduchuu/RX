### View Click
Instead of the verbose `setOnClickListener`:
```java
RxView.clicks(submitButton).subscribe(o -> log("submit button clicked!"));
```

### Filter even numbers
```java
Observable
    .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    .filter(integer -> integer % 2 == 0)
    .subscribe(System.out::println);

    // => 2, 4, 6, 8, 10nt
    
```

### Iterating with "forEach"
```java
Observable
    .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    .forEach(System.out::println);

    // => 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
```

### Group by
```java
Observable
    .just(1, 2, 3, 4, 5)chojn
    .groupBy(integer -> integer % 2 == 0).subscribe(grouped -> {
        grouped.toList().subscribe(integers -> {
            log(integers + " (Even: " + grouped.getKey() + ")");
        });
    });

    // [1, 3, 5] (Even: false)
    // [2, 4] (Even: true)
```

### Take only the first N values emitted
```java
Observable
    .just(1, 2, 3, 4, 5)
    .take(2)
    .subscribe(System.out::println);

    // => 1, 2
```


### First
```java
Observable
    .just(1, 2, 3, 4, 5)
    .first()
    .subscribe(System.out::println);

    // => 1
```

### Last

```java
Observable
    .just(1, 2, 3, 4, 5)
    .last()
    .subscribe(System.out::println);

    // => 5
```

### Distinct
```java
Observable
    .just(1, 2, 1, 3, 4, 2)
    .distinct()
    .subscribe(System.out::println);

    // => 1, 2, 3, 4
```
### Map()
Does not have to emit items of the same type as the source `Observable`
```java
Observable.just("Hello world!")
    .map(s -> s.hashCode())
    .subscribe(i -> log(Integer.toString(i)));

    // => 121287312
```

Another `map()` can convert it back to `String`
```java
Observable.just("Hello world!")
    .map(s -> s.hashCode())
    .map(i -> reverseHashCode(i))
    .subscribe(str -> log(str));

    // => Hello world!
```
### Huu hoang add operatotr
### count()
Another `count()`The simplest operator to consolidate emissions into a single one is `count()` . It will count
the number of emissions and emit through a Single once `onComplete()` is called, shown
as follows:
```java
Observable.just("Alpha", "Beta", "Gamma", "Delta","Epsilon")
    .count()
    .subscribe(s -> System.out.println("Received: " + s));

    // => Received: 5
```
### reduce() - 85
The `reduce()` operator is syntactically identical to `scan()` , but it only emits the final
accumulation when the source calls `onComplete()` . Depending on which overload you
use, it can yield Single or Maybe . If you want to emit the sum of all integer emissions, you
can take each one and add it to the rolling total. But it will only emit once it is finalized:
```java
Observable.just("Alpha", "Beta", "Gamma", "Delta","Epsilon")
    .reduce((total, next) -> total + next)
    .subscribe(s -> System.out.println("Received: " + s));

    // => Received: 41
```
### all() - 86
Check `all` data stream pass or not?
if pass all ---> true
if one not pass- ---> false imeditely
```java
Observable.just(5, 3, 7, 11, 2, 14)
        .all(i -> i < 10)
        .subscribe(s -> System.out.println("Received: " + s));

    // => Received: false 
    because: 14>10 --> 
```

### all() - 86
Check `one of all` data stream pass or not?
if one of stream data pass ---> true
if no item pass- ---> false 
```java
Observable.just(5, 3, 7, 11, 2, 14)
        .all(i -> i > 10)
        .subscribe(s -> System.out.println("Received: " + s));

    // => Received: true
    because: 14>10 and 11>11 --> passs conditional
```
### contains()() - 87
The 1contains()1 operator will check whether a specific element (based on the
1hashCode()1/1equals()1 implementation) ever emits from an Observable . It will return a
Single<Boolean> that will emit true if it is found and false if it is not.
```java
Observable.range(1,10000)
    .contains(9563)
    .subscribe(s -> System.out.println("Received: " + s));

    // => Received: true
    because:  9563 is available in the stream
```   
# Collection operators  --->(our stream data will be list or map)
### toList() - 89
A common collection operator is `toList()` . For a given `Observable<T> `, it will collect
incoming emissions into a `List<T>` and then push that entire `List<T>` as a single emission
(through `Single<List<T>>` ). In the following code snippet, we collect string emissions
into a `List<String>` . After the preceding Observable signals `onComplete()` , that list is
pushed forward to the observer to be printed:
```java
Observable.just("Alpha", "Beta", "Gamma", "Delta","Epsilon")
        .toList()
        .subscribe(s -> System.out.println("Received: " + s));

      // Received: [Alpha, Beta, Gamma, Delta, Epsilon]
    // this is a list ok?
```  
### Iterate an array list

```java
List<User> users = ArrayList<>();

users.add(new User("jon snow"));
users.add(new User("tyrion lannister"));

Observable
    .just(users)
    .concatMap(userList -> Observable.from(userList))
    .subscribe(user -> log(user.name));

    // concatMap: when applied to an item emitted by the source Observable, returns an Observable

    // => "jon snow", "tyrion lannister"
```

### Observe text changes on an EditText (RxBinding)
```java
 RxTextView.textChangeEvents(editText)
   .subscribe(e -> log(e.text().toString()));

    // => "s"
    // => "se"
    // => "sea"
    // => "sear"
    // => "searc"
    // => "search"
```


### Filter text changes on an EditText (RxBinding)
```java
 RxTextView.textChangeEvents(editText)
    .filter(e -> e.text.length() >= 3)
    .subscribe(e -> log(e.text().toString()));

    // => "sea"
    // => "sear"
    // => "searc"
    // => "search"
```

### Login form (RxBinding)

The submit button only gets enabled if username and password have a length>=3

```java

    emailChangeObservable = RxTextView.textChangeEvents(email);
    passwordChangeObservable = RxTextView.textChangeEvents(password);

    // force-disable the button
    submitButton.setEnabled(false);

    Observable.combineLatest(emailChangeObservable, passwordChangeObservable, 
                                            (emailObservable, passwordObservable) -> {
        boolean emailCheck = emailObservable.text().length() >= 3;
        boolean passwordCheck = passwordObservable.text().length() >= 3;
        return emailCheck && passwordCheck;
    }).subscribe(aBoolean -> {
        submitButton.setEnabled(aBoolean);
    });

    // submit button will only be clickable if both forms have more than 3 characters each
```

### Thread safety (RxJava + RxAndroid + Retrolambda)
```java

Observable
    .just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    .flatMap(this::heavyCalculations)
    // all the computation will happen in a background thread
    .subscribeOn(Schedulers.computation())
    // the result subscription will happen in the UI Thread
    .observeOn(AndroidSchedulers.mainThread())
    // do the calculations for each item and returns it to the subscribable observer
    .subscribe(number -> log(number));

```

### Persist data async to the database (RxJava + RxAndroid + Retrolambda)
```java
Observable
    .just(arrayOfUsers)
    .concatMap(users1 -> Observable.from(users1))
    .doOnNext(user -> saveToDataBase(user))
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe();

    // => saves all the users, one by one in the database, async
```


## Huu Hoang Addition



### Pro-tip
Don't know in which thread your code is being executed? Print this method:

> Thread.currentThread().getName()

and you'll find out.

### Note
All `subscribe()`s return a `Subscription` object that should be released with a `subscription.unsubscribe()` in the activity/fragment lifecycle to prevent memory leaks.

... or if you're lazy like me take a look here https://github.com/trello/RxLifecycle, the guys [@trello](https://twitter.com/trello) have created a library that provides automatic unsubscriptions to this kind of events.