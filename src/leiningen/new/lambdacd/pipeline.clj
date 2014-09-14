(ns {{name}}.pipeline
  (:require [lambdacd.server :as server])
  (:use [lambdacd.execution]
        [lambdacd.control-flow]
        [{{name}}.steps]))



(def pipeline
  `(
    lambdacd.manualtrigger/wait-for-manual-trigger
    some-step-that-does-nothing
    (in-parallel
      some-step-that-echos-foo
      some-step-that-echos-bar)
    lambdacd.manualtrigger/wait-for-manual-trigger
    some-failing-step
  ))


(def app (server/ui-for pipeline))
(defn start-pipeline-thread [] (server/start-pipeline-thread pipeline))