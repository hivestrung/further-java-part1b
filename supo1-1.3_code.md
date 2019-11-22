# Further Java Supo 1 - 1.3 (S)

There are two ways to create a new thread of execution. 

## Extend the Thread class
One is to declare a class to be a **subclass of Thread that overrides the run method** of class Thread.

```java
class PrimeThread extends Thread {
    long minPrime;
    PrimeThread(long minPrime) {
        this.minPrime = minPrime;
    }

    @Override
    public void run() {
        // compute primes larger than minPrime
        . . .
    }
}
```


An instance of the subclass can then be allocated and started.

```java
PrimeThread p = new PrimeThread(143);
p.start();
```

## Implement Runnable Interface

The other way to create a thread is to declare a class that **implements the Runnable interface**. That class then implements the run method.


```java
class PrimeRun implements Runnable {
    long minPrime;
    PrimeRun(long minPrime) {
        this.minPrime = minPrime;
    }
    
    @Override
    public void run() {
        // compute primes larger than minPrime
        . . .
    }
}
```

An instance of the class can then be allocated, passed as an argument when creating Thread, and started.

```java
PrimeRun p = new PrimeRun(143);
new Thread(p).start();
```

|Advantages of Extending|Disadvantages of Extending|
|---|---|


I don't see a major difference between the two, and couldn't find out online either.
