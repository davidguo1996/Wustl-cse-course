function [ w, e_in, break_iter ] = logistic_reg( X, y, w_init, eta )
%LOGISTIC_REG Learn logistic regression model using gradient descent
%   Inputs:
%       X : data matrix (without an initial column of 1s)
%       y : data labels (plus or minus 1)
%       w_init: initial value of the w vector (d+1 dimensional)
%       max_its: maximum number of iterations to run for
%       eta: learning rate
    
%   Outputs:
%       w : weight vector
%       e_in : in-sample error (as defined in LFD)
[N,~] = size(X);
w = w_init;
break_iter = 0;
i = 0;

while true
    i = i + 1;
    
    % calculate gradient
    gradient = 0;
    for j = 1:N
        term = -y(j)*[1 X(j,:)] / (1 + exp(y(j) * dot(w, [1 X(j, :)])));
        gradient = gradient + term;
    end
    gradient = gradient/N;
    
    % termination condition 
    if abs(gradient) < 1.0e-06
            break_iter = i;
            break;
    end
    
    % update w by gradient descent
    w = w - gradient * eta;
end

% calculate Ein
e_in = 0;
for i = 1 : N
    term = log(1 + exp(-y(i) * dot(w, [1 X(i,:)])));
    e_in = e_in + term;
end
    e_in = e_in / N;
end

