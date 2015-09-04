(ns leiningen.new.lambdacd
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [clojure.string :as s]
            [leiningen.core.main :as main]))

(def render (renderer "lambdacd"))

(defn lambdacd
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :pipeline-name (s/replace name #"[-_]" " ")}]
    (main/info "Generating fresh 'lein new' lambdacd project.")
    (->files data
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/pipeline.clj" (render "pipeline.clj" data)]
             ["src/{{sanitized}}/steps.clj" (render "steps.clj" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             [".gitignore" (render "gitignore" data)]
             )))
