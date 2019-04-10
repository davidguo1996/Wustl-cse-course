function [ num_iters bounds] = perceptron_experiment ( N, d, num_samples )
%perceptron_experiment Code for running the perceptron experiment in HW1
%   Inputs: N is the number of training examples
%           d is the dimensionality of each example (before adding the 1)
%           num_samples is the number of times to repeat the experiment
%   Outputs: num_iters is the # of iterations PLA takes for each sample
%            bound_minus_ni is the difference between the theoretical bound
%               and the actual number of iterations
%      (both the outputs should be num_samples long)


num_iters = zeros(num_samples,1);
min_ywx = zeros(num_samples,1);
max_wt_xnorm_square = zeros(num_samples,1);
bounds = zeros(num_samples,1);
% for every experiments
for i = 1 : num_samples
    train_data = ones(N,d);
    % genrate w*
    w = zeros(d,1);
    for z = 1 : d
        if z == 1
            w(z) = 0;
        else
            w(z) = rand;
        end
    end
    % w* except for the first col which is threshold
    wt = w(2:end,1);
    % generate sample data
    for row = 1 : N
        for col = 1 : d
            % decide yn by w dot x
            if col == d
                train_data(row,col) = sign(dot(train_data(row,1:col-1),wt));
            else
                train_data(row,col) = unifrnd(-1,1);
            end
        end
    end
    % get the result of PLA
    [~,num_iters(i)] = perceptron_learn(train_data);
    
    % max x norm^2
    x_norm = norm(train_data(1:end,1:end-1),2);
    max_norm = max(x_norm);
    
    % w* norm^2
    wt_norm = norm(wt,2);
  
    % calculate min^2 ywx
    min = (dot(train_data(1,end) * wt,train_data(1,1:end-1)))^2;
    for j = 1 : N
        if (dot(train_data(j,end) * wt,train_data(j,1:end-1)))^2 < min
            min = (dot(train_data(j,end) * wt,train_data(j,1:end-1)))^2;
        end
    end
    
    % put each time value in vector based on which time of experiment it is
    min_ywx(i) = min;
    max_wt_xnorm_square(i) = max_norm * wt_norm;
    
    % caculate each time's therotical bounds
    bounds(i) = max_wt_xnorm_square(i)/min_ywx(i) - num_iters(i);
    
end


end


