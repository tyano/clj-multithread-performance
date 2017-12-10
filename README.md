# clj-multithread-performance

A Clojure library designed to ... well, that part is up to you.

## BUILD

```
lein do clean, uberjar
```

## RUN

### FOR the sample of atom and Associative

```
java -cp target/clj-multithread-performance-0.1.0-SNAPSHOT-standalone.jar clj_multithread_performance.atom_with_associative
```

### FOR the sample of locking and HashMap

```
java -cp target/clj-multithread-performance-0.1.0-SNAPSHOT-standalone.jar clj_multithread_performance.locking_with_hashmap
```
