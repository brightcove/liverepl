(defproject com.brightcove.liverepl/liverepl-agent "0.1.0-SNAPSHOT"
  :description "Brightcove local version of liverepl-agent"
  :license {:name "Copyright (C) 2009 David J. Powell.  See GitHub for license."}
  :url "https://github.com/djpowell/liverepl"
  :dependencies []
  :plugins [[lein-jdk-tools "0.1.0"]]
  :java-source-paths ["src"]
  :lein-release {:scm :git                   ;; I guess you have to say it explicitly
                 :deploy-via :lein-install}  ;; don't attempt to do real deploy, we can't
  :manifest {"Agent-Class" "net.djpowell.liverepl.agent.Agent"}
  :main net.djpowell.liverepl.client.Main)
