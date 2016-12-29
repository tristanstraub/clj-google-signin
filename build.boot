(set-env! :dependencies '[[boot-bundle "0.1.0-SNAPSHOT" :scope "test"]])
(require '[boot-bundle :refer [expand-keywords]])
(reset! boot-bundle/bundle-file-path "./boot.bundle.edn")

(set-env! :resource-paths #{"src"}
          :dependencies (expand-keywords [:bootlaces
                                          :google-api]))

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0-SNAPSHOT")

(bootlaces! +version+)

(task-options!
  pom {:project     'com.tristanstraub/clj-google-signin
       :version     +version+
       :description "Google signin button server side token validation"
       :url         "https://github.com/tristanstraub/clj-google-signin"
       :scm         {:url "https://github.com/tristanstraub/clj-google-signin"}
       :license     {"MIT" "https://opensource.org/licenses/MIT"}})

(deftask build []
  (comp (pom)
        (jar)
        (install)))

(deftask release []
  (comp (build-jar)
        (push-snapshot)))
