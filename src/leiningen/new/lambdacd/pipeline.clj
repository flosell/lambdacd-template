(ns {{name}}.pipeline
  (:use [lambdacd.execution]
        [lambdacd.core]
        [lambdacd.control-flow]
        [{{name}}.steps])
  (:require
        [lambdacd.util :as util]
        [clojure.tools.logging :as log]))



(def pipeline-def
  `(
    lambdacd.manualtrigger/wait-for-manual-trigger
    some-step-that-does-nothing
    (in-parallel
      some-step-that-echos-foo
      some-step-that-echos-bar)
    lambdacd.manualtrigger/wait-for-manual-trigger
    some-failing-step
  ))


(def home-dir (util/create-temp-dir))
(log/info "LambdaCD Home Directory is " home-dir)
(def config { :home-dir home-dir})

(def pipeline (mk-pipeline pipeline-def config))

(def app (:ring-handler pipeline))
(def start-pipeline-thread (:init pipeline))