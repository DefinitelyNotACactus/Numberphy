import sys
from sympy import *

t = Symbol('t')
f = eval(sys.argv[1])

print( float(integrate( f, (t, -1, 1) )) )
