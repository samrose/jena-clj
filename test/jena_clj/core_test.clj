(ns jena-clj.core-test
  (:use clojure.test)
  (:use clojure.pprint))

(require '[jena-clj.triplestore :as ts])
(require '[jena-clj.remote-query :as rq])
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

(testing
  "remote SPARQL query to DBPedia"
  (let [r (rq/query-remote "https://dbpedia.org/sparql"
                        "select ?p ?o where { <http://dbpedia.org/resource/Bill_Gates> ?p ?o . } limit 12")]
    (println "\nresults:\n")
    (pprint r)))
