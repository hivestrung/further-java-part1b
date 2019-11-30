```java

public void transferTo(BankAccount b, int amount) {
      BankAccount smaller;
      BankAccount bigger;

      if (this.acc < b.acc) {
        smaller = this;
        bigger = b;
      }
      else {
        smaller = b;
        bigger = this;
      }

      synchronized (smaller) {
        synchronized (bigger) {
          this.balance -= amount;
          b.balance += amount;
        }
      }
    }
```
