(defproject com.brightcove.liverepl/client "0.1.1-SNAPSHOT"
  :description "LiveREPL client"
  :license {:name "MIT License"
            :distribution :repo
            :comments "Copyright (C) 2009 David J. Powell and others. See README for contributors."}
  :url "https://github.com/djpowell/liverepl"
  :min-lein-version "2.0.0"
  :dependencies []
  :source-paths []
  :java-source-paths ["src"]
  :plugins [[lein-jdk-tools "0.1.1"]]
  ;; Mention :skip-aot to suppress warning
  :main ^:skip-aot net.djpowell.liverepl.client.Main
  :lein-release {:scm :git, :deploy-via :lein-install}
  :uberjar-exclusions [#"^.*\.jar$"]
  :uberjar-name "liverepl-client-standalone.jar")
