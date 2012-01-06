(ns clack.test.core
  (:use [clack.core])
  (:use [clojure.test])
  (:use [clojure.java.io]))

(deftest test-relative-path
  (is (= "clack/core.clj" (relative-path (as-file "./src") (as-file "./src/clack/core.clj")))))

(deftest test-has-extension
  (is (= true (has-extension "test.txt" ".txt")))
  (is (= false (has-extension "test.foo" ".txt")))
  (is (= true (has-extension "test.foo" ".txt" ".foo"))))

(deftest test-list-files
  (is (= '("project.clj" "src/clack/core.clj" "src/clack/main.clj" "test/clack/test/core.clj")
        (filter #(.endsWith % ".clj") (list-folder (as-file "."))))))

(deftest test-list-files-with-extension
  (is (= '("project.clj" "src/clack/core.clj" "src/clack/main.clj" "test/clack/test/core.clj" "lib/clojure-1.3.0.jar"))
    (list-files-with-extension (as-file ".") ".clj" ".jar")))

(deftest test-pfind-in-files
  (is (= '("test/clack/test/core.clj")
    (time (pfind-in-files (as-file ".") #"cheesecake" ".clj")))))

(deftest test-find-in-files
  (is (= '("test/clack/test/core.clj")
    (time (find-in-files (as-file ".") #"cheesecake" ".clj")))))