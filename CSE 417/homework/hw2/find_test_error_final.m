function [ test_error ] = find_test_error( w, X, y )
%FIND_TEST_ERROR Find the test error of a linear separator
%   This function takes as inputs the weight vector representing a linear
%   separator (w), the test examples in matrix form with each row
%   representing an example (X), and the labels for the test data as a
%   column vector (y). X does not have a column of 1s as input, so that 
%   should be added. The labels are assumed to be plus or minus one. 
%   The function returns the error on the test examples as a fraction. The
%   hypothesis is assumed to be of the form (sign ( [1 x(n,:)] * w )
[row, ~] = size(X);
learn_res = zeros(row, 1);
test_error_num = 0;
for i = 1:row
    
    % use 0.5 as threshold
    learn_res(i) = sign(dot([1 X(i, :)], w));
     
    if learn_res(i) * y(i) <= 0
        test_error_num = test_error_num + 1;
    end
end
test_error = test_error_num / row;
end

