(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  ;;Source code paths
  :source-paths ["src/clj" "src/cljs" "src/cljc"]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [compojure "1.6.1"]
                 [domina "1.0.3"]
                 [hiccups "0.3.0"]
                 [org.clojars.magomimmo/shoreleave-remote-ring "0.3.3"]
                 [org.clojars.magomimmo/shoreleave-remote "0.3.1"]
                 [org.clojars.magomimmo/valip "0.4.0-SNAPSHOT"]
                 [proto-repl "0.3.1"]
                 [enlive "1.1.6"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ring "0.12.5"]
            [lein-clean-m2 "0.1.2"]]

  :ring {:handler modern-cljs.core/app}
;;cljsbuild options config
  :cljsbuild {:builds
              {:dev
               {;; CLJS source path
                :source-paths ["src/brepl" "src/cljs" "src/cljc"]
                ;;Closure compiler config
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/modern_dbg.js"
                           :optimizations :whitespace
                           ;; pretty print generated JS code
                           :pretty-print true}}
               :pre-prod
               {;; CLJS source path
                :source-paths ["src/brepl" "src/cljs" "src/cljc"]
                ;;Closure compiler config
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/modern_pre.js"
                           :optimizations :simple
                           ;; pretty print generated JS code
                           :pretty-print false}}
               :prod
               {;; clojurescript source code path
                :source-paths ["src/cljs" "src/cljc"]

                ;; Google Closure Compiler options
                :compiler {;; the name of emitted JS script file
                           :output-to "resources/public/js/modern.js"

                           ;; advanced optimization
                           :optimizations :advanced

                           ;; no need prettyfication
                           :pretty-print false}}}}

;; clean JS files generated during build
  :clean-targets ^{:protect false} [:target-path "resources/public/js"]

  :repl-options {:init-ns modern-cljs.core})
