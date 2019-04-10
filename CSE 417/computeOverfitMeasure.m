function [ overfit_m ] = computeOverfitMeasure( true_Q_f, N_train, N_test, var, num_expts )
%COMPUTEOVERFITMEASURE Compute how much worse H_10 is compared with H_2 in
%terms of test error. Negative number means it's better.
%   Inputs
%       true_Q_f: order of the true hypothesis
%       N_train: number of training examples
%       N_test: number of test examples
%       var: variance of the stochastic noise
%       num_expts: number of times to run the experiment
%   Output
%       overfit_m: vector of length num_expts, reporting each of the
%                  differences in error between H_10 and H_2
%w = zeros(num_expts, true_Q_f+1);

overfit_m = zeros(num_expts, 1);
for num = 1 : num_expts
    [train_set, test_set] = generate_dataset(true_Q_f, N_train, N_test, sqrt(var));
    
    % Using H2
    train_z_2 = computeLegPoly(train_set(:,1), 2);
    test_z_2 = computeLegPoly(test_set(:,1), 2);
    w = glmfit(train_z_2,train_set(:,2),'normal', 'constant', 'off');
    eg_2 = mean((test_z_2 * w - test_set(:,2)).^2);
    
    % Using H10
    train_z_10 = computeLegPoly(train_set(:,1), 10);
    test_z_10 = computeLegPoly(test_set(:,1), 10);
    w = glmfit(train_z_10,train_set(:,2),'normal', 'constant', 'off');
    eg_10 = mean((test_z_10 * w - test_set(:,2)).^2);
    overfit_m(num) = eg_10-eg_2;
end
end