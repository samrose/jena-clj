(ns jena-clj.remote-query)

(import org.apache.jena.query.QueryExecutionFactory)

(use '[jena-clj.triplestore :only (resultset->seq)])

(defn query-remote [sparql-endpoint sparql-query]
  (let [qexec (QueryExecutionFactory/sparqlService sparql-endpoint sparql-query)
        results (.execSelect qexec)
        vars-str (.getResultVars results)
        vars (first (take 1 (for [bhm (map (fn [b] (iterator-seq (.vars1 (.getBinding b)))) (resultset->seq results))] bhm)))
        vars-seq (resultset->seq results)]
    [vars-str
     (for [bhm vars-seq]
        (for [v vars]
            (.get1 (.getBinding bhm) v)))]))
