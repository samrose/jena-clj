(defproject jena-clj "0.1.0"
  :description "An idiomatic Clojure wrapper to the Jena API"
  :url "https://github.com/setzer22/jena-clj"
  :repositories {"mvnrepository" {:url "https://mvnrepository.com/"}
                 "jenarepo" {:url "https://repository.apache.org/content/repositories/releases/"}} 
  :license {:name "GNU Lesser General Public License v3.0"
            :url "https://www.gnu.org/licenses/lgpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.apache.jena/jena-core "3.15.0"]
                 [org.apache.jena/jena-arq "3.15.0"]
                 [org.apache.jena/jena-iri "3.15.0"]
                 [org.apache.jena/jena-tdb "3.15.0"]]
  :main ^:skip-aot sparql.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
