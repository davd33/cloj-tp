(ns cloj-tp.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [cloj-tp.core-test]))

(doo-tests 'cloj-tp.core-test)
