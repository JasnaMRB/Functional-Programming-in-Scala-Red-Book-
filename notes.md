# Functional Programming in Scala

## Chapter 1
* side effects limits testability, comparability (6)
* pure core, thin layer on outside for effects (8)
* **referential transparency** (RT): if all occurrence of expressions can be replaced with values. (9)
* **pure function**: when function is RT (9)
* **substitution model**: mode of reasoning based on RT (11)
* local reasoning enabled when no state (12)

## Chapter 2
* **tail call elimination** and `@annotation.tailrec` (20-21)
* monomorphic vs polymorphic (aka, "generic") functions (22-23)
* elided = omitted (25)
* function literals actually `Function0`, `Function1`, etc with `apply` method defined (25)
* implementations of polymorphic functions are often significantly constrained (25)
* **partial application**: when fn only has some (but not all) of its arguments supplied
* **currying**: convert fn of two arguments into partially applied fn of one argument (27)
* **function composition**: feeds output of one function as input into another (27)
* functional programming in the large has much the same flavor as programming in the small (28)

