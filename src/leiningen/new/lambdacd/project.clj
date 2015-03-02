(defproject {{name}} "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :dependencies [[lambdacd "0.1.0-alpha11"]
                           [org.clojure/clojure "1.5.1"]
                           [org.clojure/tools.logging "0.3.0"]
                           [org.slf4j/slf4j-api "1.7.5"]
                           [ch.qos.logback/logback-core "1.0.13"]
                           [ch.qos.logback/logback-classic "1.0.13"]]
            :ring {:handler {{name}}.pipeline/app
                   :init {{name}}.pipeline/start-pipeline-thread }
            :profiles {:uberjar {:aot [{{name}}.pipeline.main]}}

            :plugins [[lein-ring "0.8.13"]])