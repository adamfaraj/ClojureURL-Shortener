(ns cljapp.app
  (:use (compojure handler
                   [core :only (GET POST defroutes)]))
  (:require [compojure.route]
            [net.cgrand.enlive-html :as en]
            [ring.adapter.jetty :as jetty]
            [ring.util.response :as response]
            [clojure.pprint :refer [pprint]]))

(defonce counter (atom 10000))

(def urls (atom{}))

(defn shorten
  [url]
  (let [id (swap! counter inc)
        id (Long/toString id 36)]
    (swap! urls assoc id url)
    id))

(en/deftemplate homepage
  (en/xml-resource "homepage.html")
  [request]
  [:#listing :li] (en/clone-for [[id url] @urls]
                                [:a] (comp
                                       (en/content (format "%s : %s" id url))
                                       (en/set-attr :href (str \/ id)))))

(defn redirect
  [id]
  (response/redirect (@urls id)))

(defroutes app*
  (compojure.route/resources "/")
  (GET "/" request (homepage request))
  (POST "/shorten" request
        (let [id (shorten (-> request :params :url))]
          (response/redirect "/")))
  (GET "/:id" [id] (redirect id)))

(def app (compojure.handler/site app*))
        
        