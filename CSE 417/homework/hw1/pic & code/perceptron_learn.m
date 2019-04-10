function [ w iterations ] = perceptron_learn( data_in )
%perceptron_learn Run PLA on the input data
%   Inputs: data_in: Assumed to be a matrix with each row representing an
%                    (x,y) pair, with the x vector augmented with an
%                    initial 1, and the label (y) in the last column
%   Outputs: w: A weight vector (should linearly separate the data if it is
%               linearly separable)
%            iterations: The number of iterations the algorithm ran for

% get size of input data
[row, col] = size(data_in);
%initialize iteration number and w
iterations = 0;
w = [0,rand,rand,rand,rand,rand,rand,rand,rand,rand,rand];
wt = w(2:end);

while true
   % decide if there is nothing wrong or not
   change = false;
   % calculate wrong number
   wrong = 0;
   for j = 1 : row
       % set sign(0) to 1, and update wt
       if (sign(dot(wt,data_in(j,1:end-1)))==0 && 1 ~= data_in(j,end)) || (sign(dot(wt,data_in(j,1:end-1))) ~= data_in(j,end))
           wt = wt + data_in(j,end)*data_in(j,1:end-1);
           change = true;
           iterations = iterations + 1;
           wrong = wrong + 1;
       end
   end
   % if everthing is good then break while loop
   if ~change
       break;
   end
end
w = [0,wt];

end

