function [ train_set, test_set ] = generate_dataset( Q_f, N_train, N_test, sigma )
%GENERATE_DATASET Generate training and test sets for the Legendre
%polynomials example
%   Inputs:
%       Q_f: order of the hypothesis
%       N_train: number of training examples
%       N_test: number of test examples
%       sigma: standard deviation of the stochastic noise
%   Outputs:
%       train_set and test_set are both 2-column matrices in which each row
%       represents an (x,y) pair

% generate x
train_x = -1 + 2 * rand(N_train,1);
test_x = -1 + 2 * rand(N_test,1);
% for i = 1:N_train
%     if train_x(i) <= 0
%         train_x(i) = -1;
%     else
%         train_x(i) = 1;
%     end
%     if test_x(i) <= 0
%         test_x(i) = -1;
%     else
%         test_x(i) = 1;
%     end
% end

% generate noise and z-space
train_noise = normrnd(0,1,[N_train,1]);
test_noise = normrnd(0,1,[N_test,1]);
train_z = computeLegPoly(train_x, Q_f);
test_z = computeLegPoly(test_x, Q_f);
train_a = normrnd(0,1,[Q_f+1,1]);

% scaling
scale = 0;
for i = 0:Q_f
    scale = scale + (1/(2*i+1));
end

train_set = [train_x, train_z*train_a/sqrt(scale) + sigma * train_noise];
test_set = [test_x, test_z*train_a/sqrt(scale) + sigma * test_noise];
end
