(defproject lambdacd/lein-template "0.3.14"
  :description "leiningen template for lambdacd"
  :url "http://github.com/flosell/lambdacd-template"
  :license {:name "Apache License, version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :deploy-repositories [["clojars" {:creds :gpg}]
                        ["releases" :clojars]]
  :eval-in-leiningen true)
