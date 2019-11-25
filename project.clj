(defproject modern-cljs "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  ;;Source code paths
  :source-paths ["src/clj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 [compojure "1.6.1"]
                 [domina "1.0.3"]]
  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ring "0.12.5"]]

  :ring {:handler modern-cljs.core/handler}
;;cljsbuild options config
  :cljsbuild {:builds
              {:dev
               {;; CLJS source path
                :source-paths ["src/brepl" "src/cljs"]
                ;;Closure compiler config
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/modern_dbg.js"
                           :optimizations :whitespace
                           ;; pretty print generated JS code
                           :pretty-print true}}
               :pre-prod
               {;; CLJS source path
                :source-paths ["src/brepl" "src/cljs"]
                ;;Closure compiler config
                :compiler {;; CLS generated JS script filename
                           :output-to "resources/public/js/modern_pre.js"
                           :optimizations :simple
                           ;; pretty print generated JS code
                           :pretty-print false}}
               :prod
               {;; clojurescript source code path
                :source-paths ["src/cljs"]

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
