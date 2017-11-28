(defproject cljapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-devel "1.6.3"]
                 [ring/ring-servlet "1.6.3"]
                 [ring "1.6.3"]
                 [hiccup "2.0.0-alpha1"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [compojure "1.6.0"]
                 [enlive "1.1.6"]]
  :main ^:skip-aot cljapp.app
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [
            [lein-ring "0.12.1"]
            [lein-elastic-beanstalk "0.2.8-SNAPSHOT"]]
  :ring {:handler cljapp.app/handler}
)


