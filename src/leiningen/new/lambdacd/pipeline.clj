(ns {{name}}.pipeline
  (:use [lambdacd.steps.control-flow]
        [{{name}}.steps])
  (:require
        [ring.server.standalone :as ring-server]
        [lambdacd.steps.manualtrigger :as manualtrigger]
        [lambdacd.ui.ui-server :as ui]
        [lambdacd.runners :as runners]
        [lambdacd.util :as util]
        [lambdacd.core :as lambdacd]
        [clojure.tools.logging :as log])
  (:gen-class))



(def pipeline-def
  `(
    manualtrigger/wait-for-manual-trigger
    some-step-that-does-nothing
    (in-parallel
      some-step-that-echos-foo
      some-step-that-echos-bar)
    manualtrigger/wait-for-manual-trigger
    some-failing-step
  ))


(defn -main [& args]
      (let [home-dir (util/create-temp-dir)
            config { :home-dir home-dir :dont-wait-for-completion false}
            pipeline (lambdacd/assemble-pipeline pipeline-def config)
            app (ui/ui-for pipeline)]
           (log/info "LambdaCD Home Directory is " home-dir)
           (runners/start-one-run-after-another pipeline)
           (ring-server/serve app {:open-browser? false
                                   :port 8080})))