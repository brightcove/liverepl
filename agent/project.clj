(defproject com.brightcove.liverepl/agent "0.1.1-SNAPSHOT"
  :description "LiveREPL agent"
  :license {:name "MIT License"
            :distribution :repo
            :comments "Copyright (C) 2009 David J. Powell and others. See README for contributors."}
  :url "https://github.com/djpowell/liverepl"
  :min-lein-version "2.0.0"
  :dependencies []
  :source-paths []
  :java-source-paths ["src"]
  :manifest {"Agent-Class" "net.djpowell.liverepl.agent.Agent"}
  :lein-release {:scm :git, :deploy-via :lein-install}
  :jar-name "liverepl-agent.jar")
