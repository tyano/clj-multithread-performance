(ns clj-multithread-performance.locking-with-hashmap
  (:import [java.util Random Map HashMap])
  (:gen-class))

(def _keyRange 100)
(def _threadCount 300)
(def _repeat 100000)
(def mapref1 (volatile! (HashMap.)))

(defn fn1 []
  (let [_random (new Random)
        _key (str "key" (.nextInt _random _keyRange))]
    (locking mapref1
      (vswap! mapref1
              (fn [^Map m]
                (let [old (.get m _key)]
                  (if (nil? old)
                    (do (.put m _key 1) m)
                    (do (.put m _key (+ 1 old)) m))))))))

(defn fn2 []
  (dotimes [_index _repeat]
    (fn1)))

(defn fn3 [_start]
  (println (- (System/currentTimeMillis) _start))
  (println @mapref1))

(defn -main [& args]
  (let [_start (System/currentTimeMillis)]
    (dorun (apply pcalls (repeat _threadCount fn2)))
    (fn3 _start))
  (shutdown-agents)
  (System/exit 0))