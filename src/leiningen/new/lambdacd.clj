(ns leiningen.new.lambdacd
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "lambdacd"))

(defn lambdacd
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' lambdacd project.")
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
