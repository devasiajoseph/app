(ns app.base.validators
  (:use noir.validation)
  (:require [noir.cookies :as cookies]
            [noir.session :as session])
  )
(defn form-errors [errors] 
  {:code "form_error" :errors errors})

(defn is-url? [value] 
  (re-matches #"^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$" value))

(defn is-nil? [value] 
  (if (or (= nil value) (= "" value)) true false))

(defn map-or-nil? [value] 
  (if (or (is-nil? value) (map? value)) true false))

(defn failed-message [default-message message]
  (if (= nil message) {:message default-message } {:message (first message)}))

(defn required-failed [message]
  (failed-message "This field is required" message))

(defn email-failed [message]
  (failed-message "Enter a valid email" message))

(defn greater-than-zero-failed [message]
  (failed-message "Enter a number greater than zero" message))

(defn unique-data-failed [message] 
  (failed-message "This value already exists please enter another" [message]))

(defn data-exists-failed [message] 
  (failed-message "This value does not exist" [message]))

(defn url-failed [message]
  (failed-message "This does not seem like a valid url" message))

(defn csrf-failed [message]
  (failed-message "This is not a valid post request" message))

(defn user-key-failed [message]
  (failed-message "This key might have expired or not valid" message))

(defn url? [value & message] 
  (if (map-or-nil? value)
    value
    (if (empty? (is-url? value)) (url-failed message) value) ))

(defn required? [value & message]
  (if (is-nil? value) (required-failed message) value))

(defn email? [value & message]
  (if (map-or-nil? value) value (if (is-email? value) value (email-failed message))))

(defn greater-than-zero? [value & message]
  (if (map-or-nil? value) value (if (< value 0) (greater-than-zero-failed message) value)))

(defn csrf? [value & message]
  (if (or (nil? value) (not= (session/get :csrftoken) value )) (csrf-failed message)))


(defn collect-error [value]
  (if (map? (second value)) {(keyword (first value)) (:message (second value))}))

(defn validate-all [list-values]
  (filter (complement nil?) (map collect-error list-values)))