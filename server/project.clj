(defproject com.brightcove.liverepl/server "0.1.1-SNAPSHOT"
  :description "LiveREPL repl server"
  :license {:name "MIT License"
            :distribution :repo
            :comments "Copyright (C) 2009 David J. Powell and others. See README for contributors."}
  :url "https://github.com/djpowell/liverepl"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :lein-release {:scm :git, :deploy-via :lein-install}
  :uberjar-name "liverepl-server-standalone.jar")
