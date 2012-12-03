(defproject com.brightcove.liverepl/liverepl-server "0.1.0-SNAPSHOT"
  :description "Brightcove local version of liverepl-server"
  :license {:name "Copyright (C) 2009 David J. Powell.  See GitHub for license."}
  :url "https://github.com/djpowell/liverepl"
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :lein-release {:scm :git                   ;; I guess you have to say it explicitly
                 :deploy-via :lein-install}) ;; don't attempt to do real deploy, we can't

