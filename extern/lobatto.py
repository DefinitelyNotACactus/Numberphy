import sys
from math import *
from sympy.integrals.quadrature import gauss_lobatto

precisao = 9

farg = sys.argv[1].replace("^", "**")

variavel = sys.argv[3] if len(sys.argv) >= 4 else 'x'
f = eval("lambda " + variavel + ": " + farg)
n = int(sys.argv[2])

x, w = gauss_lobatto(n, precisao)
xw = zip(x, w)

fxi = [f(xi)*wi for xi, wi in xw]

print(sum(fxi))
