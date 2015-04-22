(ns app.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [cheshire.core :refer :all]
            [net.cgrand.enlive-html :as html]
            [clojure.string :as string]
            [app.distance :as dist]
            [app.test :refer :all])
  (:use [net.cgrand.enlive-html])
  (:import [java.net.URL]
           [java.net.URI]
           [java.lang.String]))


(defn is-apple-app [link]
  (.contains link "itunes.apple.com"))

(defn is-google-app [link]
  (.contains link "play.google.com"))

(defn is-winphone-app [link]
  (.contains link "windowsphone.com"))

(defn get-host [link]
  (let [host (-> link java.net.URL. .getHost)]
    (if (.startsWith host "www.")
      (subs host 4)
      host)))

(defn load-body [link]
  (client/get link
            {:headers {"Accept" "text/html,application/xhtml+xml,application/xml"
                       "Accept-Encoding" "gzip, deflate"
                       "User-Agent" "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:19.0) Gecko/20100101 Firefox/19.0"
                       "Accept-Charset" "ISO-8859-1"}}))

(defn parse-body [data]
  (html-resource (java.io.StringReader. (:body data))))

(defn parse-winphone [data]
  {:name (-> data (select [:div.app :h1] ) first :content first)
   :author (-> data (select [:a.title5]) first :content first)
   :desc  (-> data (select [:div.description :pre]) first :content first)})

(defn parse-google [data]
  {:name (-> data (select [:div.document-title :div] ) first :content first)
   :author (-> data (select [:a.document-subtitle :span]) first :content first)
   :desc (-> data (select [:div.id-app-orig-desc]) first :content first)})

(defn parse-apple [data]
  {:name (-> data (select [:div.intro :h1] ) first :content first)
   :author (-> data (select [:ul.list :span :span]) first :content first)
   :desc (-> data (select [:div.product-review :p]) first :content first )})

(defn parse [link host]
  (let [data (-> link load-body parse-body)] ;(-> link URL. html-resource)]
   (assoc
     (cond
       (is-apple-app link)
       (parse-apple data)
       (is-google-app link)
       (parse-google data)
       (is-winphone-app link)
       (parse-winphone data)
       :else {})
     :link link :host host)))

(defn prepare-name [name]
  (let [lower-name (string/lower-case name)]
    (string/trim
     (string/replace lower-name #"for android|for ios|for ipad|for ipod|for iphone" ""))))

(defn authors-equal [name1 name2]
  (let [min-count (min (count name1) (count name2) 30)
        short-name1 (subs (string/lower-case name1) 0 min-count)
        short-name2 (subs (string/lower-case name2) 0 min-count)
        distance (dist/levenshtein short-name1 short-name2)
        possible-distance (quot min-count 4)]
    ;(println [short-name1 short-name2 distance possible-distance])
    (<= distance possible-distance)))

(defn names-equal [name1 name2]
  (let [prep-name1 (prepare-name name1)
        prep-name2 (prepare-name name2)
        distance (dist/levenshtein prep-name1 prep-name2)
        possible-distance 1]
    ;(println [prep-name1 prep-name2 distance possible-distance])
    (= prep-name1 prep-name2)))

(defn desc-equal [name1 name2]
  (authors-equal name1 name2))

(defn equal-apps [app1 app2]
  (let [equals [(authors-equal (:author app1) (:author app2))
                (names-equal (:name app1) (:name app2))
                (desc-equal (:desc app1) (:desc app2))]]
    (>= (count (filter true? equals)) 2)))

(defn equal-list-app-all [list-app app]
  (let [equals [(some #(authors-equal (:author app) (:author %)) list-app)
                (some #(names-equal (:name app) (:name %)) list-app)
                (some #(desc-equal (:desc app) (:desc %)) list-app)]]
        ;same-host (some #(= (:host app) (:host %)) list-app)
        ;can-add-by-host (or (not same-host)
        ;                    (and same-host
        ;                         (= (:host app) "itunes.apple.com")))]
    ;(and
     (>= (count (filter true? equals)) 2)))
         ;can-add-by-host)))

(defn equal-list-app [list-app app]
  (some #(equal-apps app %) list-app))

(defn equal-list-apps [list-app list-app2]
  (some #(equal-list-app-all list-app %) list-app2))

(defn add-app [list-list-app list-app]
  (let [add-flag (atom false)

        try-add (fn [list-app list-app2]
                  (if (equal-list-apps list-app list-app2)
                    (do (reset! add-flag true)
                      (concat list-app list-app2))
                    list-app))

        after-try-add-list-list-app (doall (map #(try-add % list-app) list-list-app))]
    (if @add-flag
      after-try-add-list-list-app
      (conj list-list-app list-app))))

(defn compound [list1 list2]
  (reduce add-app list1 list2))

(defn divide-links [links]
  (let [divides (group-by get-host links)
        itunes-links (get divides "itunes.apple.com")
        google-links (get divides "play.google.com")
        winphone-links (get divides "windowsphone.com")

        itunes-apps (doall (map #(conj [] (parse % "itunes.apple.com")) itunes-links))
        google-apps (doall (map #(conj [] (parse % "play.google.com")) google-links))
        winphone-apps (doall (map #(conj [] (parse % "windowsphone.com")) winphone-links))

        itunes-compound (compound [] itunes-apps)
        google-itunes-apps (compound google-apps itunes-compound)
        all-apps (compound google-itunes-apps winphone-apps)]
    all-apps))

(defn process-links [links]
  (let [apps (vec (doall (map #(conj [] (parse % (get-host %))) links)))
        compounded-apps (compound [] (compound [] apps))]
    compounded-apps))

 (defn read-links [file-name]
  (with-open [rdr (clojure.java.io/reader file-name)]
    (doall (line-seq rdr))))


(defn get-short-name [list-app]
  (let [sorted-by-name (sort #(compare (count (:name %1)) (count (:name %2))) list-app)]
    (:name (first sorted-by-name))))

(defn print-list-app [list-app]
  (println "\n" (get-short-name list-app))
  (doseq [app list-app]
    (println (:link app))))

(defn print-apps [apps]
  (doseq [list-app (reverse apps)]
    (print-list-app list-app)))

(defn -main [& args]
  (try
    (let [[file] args
          links (read-links file)
          apps (process-links links)]
      (print-apps apps))
    (catch Exception e
      (println "Ошибка. Возможно, неправильно указано имя файла или допущена ошибка в описании ссылок: "
               (.getMessage e)))))

;--- test ----
;(-main "links.txt")
;(def c-apps (divide-links links))
;(def c-apps (process-links links))



