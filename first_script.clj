;This is the simplest Hello World Program
(println "hello world")

;This can take a name as input
(defn hello [name] (str "Hello, " name))
;Input the name
(hello "Jed")
(hello "Jamie")
(hello "Chuck")


;Interesting that the output here loads them into
;variables in reverse order: Jed is 3
;Push pop style
(str *1 ", " *2 " and " *3)

;if we do this twice in a row, it takes the output of the above string
;and makes it *1
(str *1 ", " *2 " and " *3)


;So if we build an empty set, and then put a guy in it, that makes a new set
;but does that leave the empty set floating?
#{}
(conj #{} "Jed")


;so if def can define functions or data, why do we need defn?
;Here we define a data set visitors to return an initial
;value of a null set! 
(def visitors (ref #{}))

; crh:
; defn is just a shortcut!
; user=> (def def-time (fn [] (System/currentTimeMillis)))
; user=> (def-time)
; 1416110991763
; user=> (defn defn-time [] (System/currentTimeMillis))
; user=> (defn-time)
; 1416111008754


;Alter we change what is stored in visitors, or really 
;we are creating a new data set and having the reference
;point to that instead!
(alter visitors conj "Bob")
;This doesn't work alone because it needs something called
;a transaction? I'm a little fuzzy on this part
;I guess the idea is that we can't just go around altering
;what shit points at. Instead we let Clojure sync the alter
;requests from different users who may be asking for stuff to
;point places
(dosync (alter visitors conj "Tom"))

;We can look inside stuff 
(deref visitors)
;or
@visitors

(defn hello
  "Writes hello message to *out*. Calls you by username.
   Knows if you have been here before."
   [username]
   (dosync
    (let [past-visitor (@visitors username)]
      (if past-visitor
        (str "Welcome back, " username)
        (do
          (alter visitors conj username)
          (str "Hello, " username))))))

;easily the coolest part of this function
;is the part right here (@visitors username), which
;checks to see whether username is a member of the current value of visitors
;oddly (@visitors username) returns nil if the username is not in
;visitors, and the name if not, not true or false... so I guess
;nil evaluates to false in clojure, and the presence of an object
;evaluates to true. Dats pretty strange, but convenient here!

;LOADING PACKAGES
;this quoting thing is fucking weird
(require 'clojure.contrib.str-utils)






