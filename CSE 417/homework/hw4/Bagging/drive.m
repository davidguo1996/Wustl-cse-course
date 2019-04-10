
n =200;
%% 1_vs_3
  % load, sparse data and fit tree
load zip.train;
fprintf('Working on the one-vs-three problem...\n\n');
subsample = zip(find(zip(:,1)==1 | zip(:,1) == 3),:);
Y = subsample(:,1);
X = subsample(:,2:257);
ct = fitctree(X,Y,'CrossVal','on');
fprintf('The cross-validation error of decision trees is %.4f\n', ct.kfoldLoss);
  % call loop function to get obb while increase numBags on the fly
[oob_err] = loop(X,Y,n);
bee=oob_err(n,1)
fprintf('The OOB error of 200 bagged decision trees is %.4f\n', bee);
  % plot oob
x_axis = 1:1:n;
plot(x_axis,oob_err,'LineWidth',1);
xlabel('numBags');
ylabel('oob');
title('1_vs_3');

%% 
  % comment out to experiment 3_vs_5
% 
% %% 3_vs_5
%   % load, sparse data and fit tree
% load zip.train;
% fprintf('Working on the one-vs-three problem...\n\n');
% subsample = zip(find(zip(:,1)==3 | zip(:,1) == 5),:);
% Y = subsample(:,1);
% X = subsample(:,2:257);
% ct = fitctree(X,Y,'CrossVal','on');
% fprintf('The cross-validation error of decision trees is %.4f\n', ct.kfoldLoss);
%   % call loop function to get obb while increase numBags on the fly
% [oob_err] = loop(X,Y,n);
% bee=oob_err(n,1)
% fprintf('The OOB error of 200 bagged decision trees is %.4f\n', bee);
%   % plot oob
% x_axis = 1:1:n;
% plot(x_axis,oob_err,'LineWidth',1);
% xlabel('numBags');
% ylabel('oob');
% title('3_vs_5');

