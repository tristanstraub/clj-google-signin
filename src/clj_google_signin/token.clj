(ns clj-google-signin.token
  (:import [com.google.api.client.googleapis.auth.oauth2
            GoogleIdToken
            GoogleIdToken$Payload
            GoogleIdTokenVerifier$Builder
            GoogleIdTokenVerifier]
           [com.google.api.client.json.jackson2.JacksonFactory]
           [com.google.api.client.http.javanet.NetHttpTransport]))

(defn verify [client-id token]
  (let [jsonFactory (com.google.api.client.json.jackson2.JacksonFactory.)
        transport   (com.google.api.client.http.javanet.NetHttpTransport.)
        v           (.. (GoogleIdTokenVerifier$Builder. transport jsonFactory)
                        (setAudience (list client-id))
                        (build))]

    (.verify v token)))
