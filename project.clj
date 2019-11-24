(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  ;;Source code paths
  :source-paths ["src/clj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.597"]]
  :plugins [[lein-cljsbuild "1.1.7"]]

;;cljsbuild options config
  :cljsbuild {:builds
              [{;; CLJS source path
                :source-paths ["src/cljs"]
                ;;Closure compiler config
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/modern.js"
                           :optimizations :whitespace
                           ;; pretty print generated JS code
                           :pretty-print true}}]}

;; clean JS files generated during build
  :clean-targets ^{:protect false} [:target-path "resources/public/js"]

  :repl-options {:init-ns modern-cljs.core})
