function [ oobErr,ct_all] = BaggedTrees( X, Y, numBags )
%BAGGEDTREES Returns out-of-bag classification error of an ensemble of
%numBags CART decision trees on the input dataset, and also plots the error
%as a function of the number of bags from 1 to numBags
%   Inputs:
%       X : Matrix of training data
%       Y : Vector of classes of the training examples
%       numBags : Number of trees to learn in the ensemble
%
%   You may use "fitctree" but do not use "TreeBagger" or any other inbuilt
%   bagging function
n=size(X,1);
X_bag_all=zeros(size(X,1),size(X,2),numBags);
rnd_index_all=[];
% create bagged trees
for i=1:numBags
    rnd_index=randsample(n,n,true);
    rnd_index_all=[rnd_index_all rnd_index];
    X_bag=X(rnd_index,:);
    X_bag_all(:,:,i)=X_bag;
    ct = fitctree(X_bag,Y(rnd_index));
    ct_all{i}=ct;
end
% calculate OOB error
oobErr=0;
num_no_xi=0;
bar_t_tot=zeros(n,numBags);
for i=1:n
    pred_t=[];
    for k=1:numBags
        if ~ismember(i,rnd_index_all(:,k))
            ct_i=ct_all{k};
            pred_t_bag=predict(ct_i,X(i,:));
            pred_t=[pred_t pred_t_bag];
            bar_t_tot(i,k)=pred_t_bag;
        end
    end
    if isempty(pred_t)
        num_no_xi=num_no_xi+1;
    else
        bar_t=mode(pred_t(:));
        if Y(i)~=bar_t
            oobErr=oobErr+1;
        end        
    end
end

if n==num_no_xi
    oobErr=0;
else
    oobErr=oobErr/(n-num_no_xi);
end

% number of bags from 1 to numBags
oobErr_tot=zeros(1,numBags);
pred_yy=zeros(n,1);
for k=1:numBags
    temp_t=bar_t_tot(:,1:k);
    for j=1:n
        a=temp_t(j,:);
        b=a(a~=0);
        pred_yy(j)=mode(b);
    end
    nan_num=sum(isnan(pred_yy));
    oobErr_tot(k)=(sum(pred_yy~=Y,'all')-nan_num)/(n-nan_num);
end


figure
plot(oobErr_tot,'*-r','Linewidth',1,'Markersize',3,'MarkerIndices',1:200);
title('OOB error as a function of the number of bags','fontsize',12)
xlabel('Number of bags','fontsize',14)
ylabel('OOB (out-of-bag) error','fontsize',14)
legend('BaggedTrees','fontsize',14)



end
