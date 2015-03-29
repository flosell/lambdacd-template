(ns {{name}}.pipeline
  (:use [lambdacd.internal.execution]
        [lambdacd.steps.control-flow]
        [ring.server.standalone :as ring-server]
        [{{name}}.steps])
  (:require
        [lambdacd.util :as util]
        [lambdacd.core :as lambdacd]
        [clojure.tools.logging :as log]))



(def pipeline-def
  `(
    lambdacd.steps.manualtrigger/wait-for-manual-trigger
    some-step-that-does-nothing
    (in-parallel
      some-step-that-echos-foo
      some-step-that-echos-bar)
    lambdacd.steps.manualtrigger/wait-for-manual-trigger
    some-failing-step
  ))


(defn -main [& args]
      (let [home-dir (util/create-temp-dir)
            config { :home-dir home-dir :dont-wait-for-completion false}
            pipeline (lambdacd/mk-pipeline pipeline-def config)
            app (:ring-handler pipeline)
            start-pipeline-thread (:init pipeline)]
           (log/info "LambdaCD Home Directory is " home-dir)
           (start-pipeline-thread)
           (ring-server/serve app {:open-browser? false
                                   :port 8080})))