(ns {{name}}.steps
  (:require [lambdacd.shell :as shell]))

(defn some-step-that-does-nothing [& _]
  {:status :success})

(defn some-step-that-echos-foo [& _]
  (shell/bash "/" "echo foo"))
(defn some-step-that-echos-bar [& _]
  (shell/bash "/" "echo bar"))

(defn some-failing-step [& _]
  (shell/bash "/" "echo \"i am going to fail now...\"" "exit 1"))