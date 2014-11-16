(ns command-line-args.core)

(defn parse-args [args]
  (into{} (map (fn [[k v]] [(keyword (.replace k "--" "")) v])
               (partition 2 args))))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


