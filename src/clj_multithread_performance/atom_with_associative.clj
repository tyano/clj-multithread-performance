(ns clj-multithread-performance.atom-with-associative
  (:import (java.util Random))
  (:gen-class))

(def _keyRange 100)
(def _threadCount 300)
(def _repeat 100000)
(def mapref1 (atom {}))

(defn fn1 []
  (let [_random (new Random)
        _key (str "key" (.nextInt _random _keyRange))]
    (swap! mapref1 update _key (fnil inc 0))))

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
  (System/exit 0))
