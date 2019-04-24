import sys
from math import *
from sympy.integrals.quadrature import gauss_lobatto

precisao = 6

f = eval("lambda t: " + sys.argv[1])
n = int(sys.argv[2])

x, w = gauss_lobatto(n, precisao)
xw = zip(x, w)

fxi = [f(xi)*wi for xi, wi in xw]

print(sum(fxi))
