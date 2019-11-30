
# Further Java Supo 2 - 2.5(D)

## 2010 P3 Q9

### (a)

```java
class Fork {
    boolean pickedUp = false;
    public synchronised void pickUp() {
        while (pickedUp) {
            this.wait();
        }
        pickedUp = true;
        return;
    }
    public synchronised void putDown() {
        pickedUp = false;
        this.notifyAll();
        return;
    }
}
```

### (b)

```java
class Fellow extends Thread {
    Fork left;
    Fork right;
    public Fellow(Fork left, Fork right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public void run() {
        // think for 10s
        sleep(10000);
        // eat for 10s
        left.pickUp();
        right.pickUp();
        sleep(10000);
        // think for 10s
        left.putDown();
        right.putDown();
    }
}
Fork r = new Fork();
Fork l = new Fork();
Fellow fellowThread = new Fellow(Fork l, Fork r);
fellowThread.start()
```

### (c)

Deadlock = state where every thread is blocked, waiting for a resource by a thread that is also waiting for a resource.

As they are sitting at a circular table, this case if every `Fellow` begins by picking up the `Fork` on the left at the same time, before any of them manage to pick up the right `Fork` all of them will not be able to pick up the `Fork` on the right (as it is currently `pickedUp`),indefinitely.

### (d)

I am not sure what modifications would violate the conditions. In theory, wouldn't all Fellows start at the same time and thus encounter deadlock?

We can have Fellows randomly sleep a few milliseconds more when thinking before picking up the fork, which should prevent deadlock from occurring in practice. But this means they are thinking more than specified.

Similarly, we can have one Fellow start just a little later than the rest.

## 2011 P3 Q7

(a)

```java
public Integer maxf(Interator<Record> it) {
    Integer res = null;
    Integer tmp = null;
    while (it.hasNext()) {
        tmp = this.f(it.next())
        // can we use Eval.f(), given that it is a static function?
        res = (res == null || res < tmp) ? tmp : res;
    }
    return res;
}
```

(b)

```java
abstract class Joinable implements Runnable {
    boolean running = false;
    abstract void exec();
    
    @Override
    final public synchronised void run() {
        // ... call the exec() method ...
        running = true;
        exec();
        running = false;
        this.notifyAll();
    }
    synchronised void join() throws InterruptedException {
        // block the calling thread until exec() completes in run()
        while (running) {
            this.wait();
        }
    }
}
```

(c)

```java
class ParmaxfSubroutine extends Joinable {
    private Iterator<Record> it;
    Integer res;
    
    ParmaxfSubroutine(java.util.Iterator<Record> it) {
        this.it = it;
    }
    void exec() {
        res = Eval.maxf(it);
    }
}


public Integer parmaxf(Interator<Record> it, int n) {
    
    ArrayList<ParmaxfSubroutine> parmaxfSubroutines = new ArrayList<>();
    
    for (int i = 0; i < n; i++) {
        parmaxfSubroutines.add(new ParmaxfSubroutine(it));
        new Thread(parmaxfSubroutines.get(i)).start();
        parmaxfSubroutines.get(i).join();
    }
    
    Integer res = null;
    for (ParmaxfSubroutine p : parmaxfSubroutines) {
        res = (res == null || p.res > this.res) ? p.res : this.res;
    }
    return res;
}
```

## 2013 P3 Q7

### (a)

Class loader loads Java classes into the JVM dynamically during runtime. If a developer wants to load classes from elsewhere, such as from the network, then they would need to implement their own class loader.

### (b)

```java
public class ClassFileSender {
    
    public static void main(String[] args) {
        final String portStr = args[0];
        final String path = args[1];
        
        final Integer port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input, need integer for port!")
        }
        
        
        
        
    }
    
    ClassFileSender(String server, int port) {
        
    }
    
}


```
