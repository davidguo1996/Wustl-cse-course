function[oob_err]=loop(X, y, numBags)
   
    oob_err=zeros(numBags,1);
    for j=1:numBags
        oob_err(j,1) = BaggedTrees(X,y,j);
%         if rem(j,10)==0 
%         fprintf('=')
%         end 
    end

end