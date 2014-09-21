(ns {{name}}.pipeline
  (:require [lambdacd.server :as server])
  (:use [lambdacd.execution]
        [lambdacd.core]
        [lambdacd.control-flow]
        [{{name}}.steps]))



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

(def pipeline (mk-pipeline pipeline-def))

(def app (:ring-handler pipeline))
(def start-pipeline-thread (:init pipeline))