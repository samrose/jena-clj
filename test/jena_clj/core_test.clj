(ns jena-clj.core-test
  (:use clojure.test))

(require '[jena-clj.triplestore :as ts])
(import '[org.apache.jena.query ReadWrite])
(defonce db (ts/init-database "data.db"))

(testing
  "jena-clj with local data"
  (ts/with-transaction
    db ReadWrite/WRITE
    (ts/insert-rdf db "sample_news.n3"))

  (println (ts/with-transaction
             db ReadWrite/READ
             (ts/select-query
               db
               "SELECT ?s ?p ?o
                  WHERE {?s ?p ?o} LIMIT 10"))))
  
