import sys
from sympy import *

farg = sys.argv[1].replace("^", "**")

variavel = sys.argv[2] if len(sys.argv) >= 3 else 'x'
f = eval("lambda " + variavel + ": " + farg)
t = Symbol(variavel)

print( float(integrate( f(t), (t, -1, 1) )) )
