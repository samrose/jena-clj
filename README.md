# jena-clj

An idiomatic clojure wrapper to the Jena ontology management library. 

### Cloned by Mark Watson 10/10/2020. Thanks to the original author, and thanks for changing the license to LGPL!

## Mark Watson's notes for project fork:

- added remote SPARQL query (with test against DBPedia SPARQL endpoint)
- minor changes to original triplestore code, updating library versions
- added a concrete example for triplestore code

To install this library locally on your laptop or server (binaries in ~/.m2) run:

    lein install

You can use the locally installed library by adding this to your project.clj file for :dependencies:

    [jena-clj "0.2.1"]

And to try it out:

```clj
(require '[jena-clj.remote-query :as rq])

;; ...
  (let [r (rq/query-remote "https://dbpedia.org/sparql"
                           "select ?p ?o where { <http://dbpedia.org/resource/Bill_Gates> ?p ?o . } limit 12")]
        (println "\nresults:\n")
        (println r))
```

## Install

### Leiningen / Boot:

```clj
[jena-clj "0.1.0"]
```

### Gradle 

``` gradle
compile "jena-clj:jena-clj:0.1.0"
```

### Maven
``` xml
<dependency>
  <groupId>jena-clj</groupId>
  <artifactId>jena-clj</artifactId>
  <version>0.1.0</version>
</dependency>
```


## Usage

Some usage examples:

``` clj
(require '[jena-clj.triplestore :as ts])
(import '[org.apache.jena.query ReadWrite])

(require '[jena-clj.remote-query :as rq])

(let [r (rq/query-remote "https://dbpedia.org/sparql"
                        "select ?p ?o where { <http://dbpedia.org/resource/Bill_Gates> ?p ?o . } limit 12")]
    (println "\nresults:\n")
    (pprint r))

(defonce db (ts/init-database "data.db")) ; If it doesn't exist, it creates one

(ts/with-transaction db ReadWrite/WRITE
  (ts/insert-rdf db "sample_news.n3")) ; Loads a whole RDF file into triplestore
  
(ts/with-transaction db ReadWrite/READ
  (ts/select-query db
    "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> 
     SELECT ?uri ?name
     WHERE {?uri rdf:name ?name}")) ; Returns a lazy sequence with all results.

  
(ts/with-transaction db ReadWrite/READ
  (ts/select-query db
    "SELECT ?s ?p ?o
     WHERE {?s ?p ?o} LIMIT 10")) ; Returns a lazy sequence with all results.

```
Take a look at triplestore.clj source for more!

### Features

- TDB Triplestore management
- SPARQL queries

## License

Copyright © 2017 setzer22

Copyright © 2017 Mark Watson (https://markwatson.com)

Distributed under the GNU Lesser General Public License v3.0

