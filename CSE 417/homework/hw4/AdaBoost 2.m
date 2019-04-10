function [ train_err, test_err ] = AdaBoost( X_tr, y_tr, X_te, y_te, n_trees )
%AdaBoost: Implement AdaBoost using decision stumps learned
%   using information gain as the weak learners.
%   X_tr: Training set
%   y_tr: Training set labels
%   X_te: Testing set
%   y_te: Testing set labels
%   n_trees: The number of trees to use

%Inirialize
train_size  = size(X_tr, 1);
test_size = size(X_te, 1);
trainPool = zeros(n_trees, train_size);
testPool = zeros(n_trees, test_size);
w = ones(train_size,1)./train_size;
alpha = zeros(n_trees, 1);
%learning
for i = 1:n_trees
	MDl = fitctree(X_tr, y_tr, 'minparent', train_size, 'SplitCriterion', 'deviance', 'prune', 'off', 'mergeleaves', 'off', 'Weights', w);
	epsilon = sum(w .* (predict(MDl, X_tr) ~= y_tr));
	if epsilon > 0.5 
		print(i-1);
		break
	end 
    %results memo
	trainPool(i, :) = predict(MDl, X_tr);
	testPool(i,:) = predict(MDl, X_te);
	alpha(i) = 0.5 * log((1 - epsilon)/epsilon);
	Z = 2 * sqrt(epsilon * (1 - epsilon));
	w = w .* (exp(-alpha(i) .* y_tr .* transpose(trainPool(i, :)))) / Z;
end 
%classifier & error measures
train_tag = sign(sum(alpha .* trainPool,1));
test_tag = sign(sum(alpha .* testPool,1));
train_err = sum(train_tag ~= transpose(y_tr))/size(y_tr,1);
test_err = sum(test_tag ~= transpose(y_te))/size(y_te,1);

end

