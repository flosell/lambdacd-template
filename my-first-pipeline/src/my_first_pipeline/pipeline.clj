(ns my-first-pipeline.pipeline
  (:use [lambdacd.steps.control-flow]
        [my-first-pipeline.steps])
  (:require
        [lambdacd.steps.manualtrigger :as manualtrigger]))

(def pipeline-def
  `(
    manualtrigger/wait-for-manual-trigger
    some-step-that-does-nothing
    (in-parallel
      some-step-that-echos-foo
      some-step-that-echos-bar)
    manualtrigger/wait-for-manual-trigger
    some-failing-step))
