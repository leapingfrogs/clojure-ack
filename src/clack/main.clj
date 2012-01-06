(ns clack.main
  (:use [clack.core])
  (:use [clojure.java.io])
  (:gen-class ))

(defn -main [& args]
  (if args
    (println (apply str (interpose "\n" (pfind-in-files (as-file ".") (re-pattern (first args)) (rest args)))))
    (println "Usage:\n\tclack <regex> <extension1> <extension2> ...")))
