function [ oobErr ] = BaggedTrees( X, Y, numBags )
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
oob = zeros(1,numBags);
[numData,~] = size(X);
trees = cell(numBags,1);
indexData = zeros(numData,numBags);

for i = 1 : numBags
    
    %for tree_num = 1 : i
        % resampling
        index = randi([1,numData],numData,1);
        feature = X(index,:);
        label = Y(index,:);
    
        % fit tree
        tree = fitctree(feature, label);
    
        % store this tree
        trees{i} = tree;
        indexData(:,i) = index;
    %end
    
    oob_res = zeros(1,numData);
    % calculate out_of_bag error
    for j = 1 : numData    
        % find trees don't contain data j
        isin = ismember(indexData(:,1:i),j);
        no0_col = find(sum(isin)==0);
        % if there are trees don't contain data j
        if(~isempty(no0_col))
            [size_no0_col,~] = size(no0_col);
            no0_oobVote = zeros(1,size_no0_col);
            % predict
            for k = 1:size_no0_col
                no0_oobVote(k) = predict(trees{no0_col(k)}, X(j,:));
            end
            % vote
            vote_res = mode(no0_oobVote);
            if(vote_res ~= Y(j))
                oob_res(j) = -1;
            elseif(vote_res == Y(j))
                oob_res(j) = 1;
            end
        end      
    end
    
    oob(i) = sum(oob_res == -1)/(sum(oob_res==1)+sum(oob_res==-1));
    
end
x_axis = 1:1:numBags;
plot(x_axis,oob,'LineWidth',1);
%legend()
xlabel('numBags');
ylabel('oob');
title('oob under numBags');
oobErr = oob(numBags);
end
