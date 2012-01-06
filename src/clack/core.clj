(ns clack.core)

(defn relative-path [root child]
  (str (.relativize (.toURI root) (.toURI child))))

(defn has-extension [filename & extensions]
  (not (nil? (some #(.endsWith filename %) (flatten extensions)))))

(defn list-folder [file]
  (map (partial relative-path file) (file-seq file)))

(defn list-files-with-extension [file & extensions]
  (filter #(has-extension % extensions) (list-folder file)))

(defn file-contains [pattern file]
  (re-find pattern (slurp file)))

(defn find-in-files [root-file pattern & extensions]
  (let [files (list-files-with-extension root-file extensions)]
    (filter (partial file-contains pattern) files)))

(defn pfind-in-files [root-file pattern & extensions]
  (filter #(not (nil? %))
    (pmap #(if (file-contains pattern %) % nil)
      (list-files-with-extension root-file extensions))))