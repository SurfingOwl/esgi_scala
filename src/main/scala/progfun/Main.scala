package fr.esgi.al.funprog

import progfun.{Lawnmower, Orientation}

object Main extends App {
  println("Ici le programme principal")
//   Le code suivant ne compilera pas.
//   var tmp = null;
//   var tmp2 = if (tmp == 1) "yes" else 1
//
//   println(s"tmp: $tmp, tmp2: $tmp2")

  new Lawnmower(0, 0, Orientation("N"))
}
