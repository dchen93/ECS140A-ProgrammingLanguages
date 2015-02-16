(defun double-recursive(y)
  (if (null y)
      nil
  (if ( listp y)
    (append (list (car y)(car y)) (double-recursive(cdr y)) )
    nil))
)

(defun double-iterative(y)
  (if (null y)
      nil
  (if ( listp y)
    (do ((x y (cdr x))
        (z '() (append z (list (car x) (car x)))))
       ((null x) z))
      nil))
)

(defun double-mapcar(y)
  (if (null y)
      nil
  (if ( listp y)
    (apply #'append (mapcar #'list y y))
    nil))
)

(defun rdouble(y)
  (if(null y)
      nil
    (if ( listp y)
        (if ( listp (car y))
            (append ( list (rdouble(car y))) (rdouble(cdr y)))
          (append( list (car y)(car y)) (rdouble(cdr y))))
      nil))
)

(defun my-mapcar(fun l1 l2)
  (if (or (null l1) (null l2))
      nil
    (if (and (listp l1) (listp l2))
       (append (list(apply fun (list(car l1) (car l2)))) (my-mapcar fun  (cdr l1)  (cdr l2)))
      nil))
)

(defun rewrite(x)
  (if (or (null x) (not (listp x)))
      x
    (if (listp x)
      (if  (eq (car x) 'if )
           (if (eq (length x) 4)
               (append (list 'cond (list (rewrite(cadr x)) (rewrite(caddr x))) (list t (rewrite(cadddr x)) ) ))
          (append (list 'cond (rewrite(cdr x)))))
        (append (list (rewrite(car x))) (rewrite(cdr x))))
    x))
)

(defun check(x)
  (if (and (equal (eval x) (eval (rewrite x))))
      (list t (eval x) (eval (rewrite x)))
    (list nil (eval x) (eval (rewrite x)))
      )
)