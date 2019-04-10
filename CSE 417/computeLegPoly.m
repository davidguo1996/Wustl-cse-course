function [ z ] = computeLegPoly( x, Q )
%COMPUTELEGPOLY Return the Qth order Legendre polynomial of x
%   Inputs:
%       x: vector (or scalar) of reals in [-1, 1]
%       Q: order of the Legendre polynomial to compute
%   Output:
%       z: matrix where each column is the Legendre polynomials of order 0 
%          to Q, evaluated atthe corresponding x value in the input
[n,m] = size(x);
m = Q+1;
z = zeros(n,m);

for i = 1:n
 q = 0;   
 while q <= Q
    if q == 0
        z(i,1) = 1;
    elseif q == 1
        z(i,q+1) = x(i);
    else
        z(i,q+1) = (2*q-1)/q * x(i) * z(i,q) - (q-1)/q * z(i,q-1);
    end
    q = q + 1;
end
end
end