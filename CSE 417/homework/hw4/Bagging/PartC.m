% load data from zip.train and compare the performance of plain
% decision trees (cross-validated) and bagged ensembles (OOB error)

% Problem 1c: Single decision tree and single ensmble of 200 Bagged trees

%% 1_vs_3 & 3_vs_5
  %load training set and fit a single decision tree  
n =200;
load zip.train;
fprintf('Working on the 1-vs-3 problem...\n\n');
subsample = zip(find(zip(:,1)==1 | zip(:,1) == 3),:);
Y_tr = subsample(:,1);
X_tr = subsample(:,2:257);
ct1 = fitctree(X_tr,Y_tr);
tb1 = TreeBagger(200, X_tr,Y_tr, 'Method','classification');

fprintf('Now, Working on the 3-vs-5 problem...\n\n');
subsample = zip(find(zip(:,1)==3 | zip(:,1) == 5),:);
Y_tr = subsample(:,1);
X_tr = subsample(:,2:257);
ct2 = fitctree(X_tr,Y_tr);
tb2 = TreeBagger(200, X_tr,Y_tr, 'Method','classification');

  %analyze the prediction performance based on test set
load zip.test;
fprintf('\Working on the 1-vs-3 testing data...\n\n');
subsample = zip(find(zip(:,1)==1 | zip(:,1) == 3),:);
X1 = subsample(:,2:257);
Y1 = subsample(:,1);
% p1 = predict(ct1, subsample(:,2:257));
L1 = loss (ct1,X1,Y1);
LT1 = mean(error(tb1, X1, Y1));

fprintf('\nNow working on the 3-vs-5 testing data...\n\n');
subsample = zip(find(zip(:,1)==3 | zip(:,1) == 5),:);
X2 = subsample(:,2:257);
Y2 = subsample(:,1);
% p2 = predict(ct2, subsample(:,2:257));
L2 = loss (ct2, X2, Y2);
LT2 = mean(error(tb2, X2,Y2));

