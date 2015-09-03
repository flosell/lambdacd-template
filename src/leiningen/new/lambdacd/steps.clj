(ns {{name}}.steps
  (:require [lambdacd.steps.shell :as shell]))

(defn some-step-that-does-nothing [arg ctx]
  {:status :success})

(defn some-step-that-echos-foo [arg ctx]
  (shell/bash ctx "/" "echo foo"))

(defn some-step-that-echos-bar [arg ctx]
  (shell/bash ctx "/" "echo bar"))

(defn some-failing-step [arg ctx]
  (shell/bash ctx "/" "echo \"i am going to fail now...\"" "exit 1"))
