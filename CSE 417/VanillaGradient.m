% Minimization of a two-dimensional function:
%
% f(x) = exp(x(1)+3*x(2)-0.1) + exp(x(1)-3*x(2)-0.1) + exp(-x(1)-0.1)
%
% U. S. Kamilov, CIG, WUSTL, 2019.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% prepare workspace
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clear; close all; home;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% definition of the objective
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% objective
func = @(x) exp(x(1)+3*x(2)-0.1) + exp(x(1)-3*x(2)-0.1) + exp(-x(1)-0.1);

% gradient
dx1 = @(x) exp(x(1)+3*x(2)-0.1) + exp(x(1)-3*x(2)-0.1) - exp(-x(1)-0.1);
dx2 = @(x) 3*exp(x(1)+3*x(2)-0.1) - 3*exp(x(1)-3*x(2)-0.1);
grad = @(x) [dx1(x); dx2(x)];

% minimimum of the function (used for plotting)
fast = 2.55926669665821560073;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% this block is used for plotting the contours
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

x1 = linspace(-1.5, 0.5, 101);
x2 = linspace(-0.4, 0.4, 101);

[X1, X2] = meshgrid(x1, x2);

F = exp(X1+3*X2-0.1) + exp(X1-3*X2-0.1) + exp(-X1-0.1);

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% iterative reconstruction
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

xinit = [-1; 0.3]; % initial value
step = 1; % step-size
tol = 1e-6; % termination tolerance on the gradient
numIter = 10; % maximum number of iterations to run
theta = 0.1; % theta 
beta = 0.7; % step shrinking rate

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%% optimize: gradient method
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% initialize
x = xinit;

% track difference to the minimum: fdif = f(xnext) - fast;
fdif = zeros(numIter, 1);

for indIter = 1 : numIter
    
    % objective and search direction at x
    f = func(x);
    g = - grad(x);
    
    % backtracking line_search
    while func(x + step * g) > f + theta * step * grad(x)' * g
        step = beta * step;
    end
    
    % tentative update
    xnext = x + step*g;
    
    % objective and upper bound at xnext
    fnext = func(xnext);
    
    % store the objective value
    fdif(indIter) = fnext-fast;
    
    % create a figure and show the signal
    figure(101);
    set(gcf, 'Color', 'w', 'Name', sprintf('objective = %.6e', fnext));
    subplot(2, 1, 1);
    contour(X1, X2, F);
    xlabel('x_1');
    ylabel('x_2');
    hold on;
    subplot(2, 1, 1);
    plot([x(1), xnext(1)], [x(2), xnext(2)], '*b-', 'LineWidth', 1.2);
    set(gca, 'FontSize', 16);
    subplot(2, 1, 2);
    semilogy(1:indIter, fdif(1:indIter), 'b-', 'LineWidth', 2);
    xlim([1 numIter]);
    ylim([1e-15, func(xinit)]);
    yticks([1e-15 1e-10 1e-5 1e0])
    grid on;
    xlabel('iteration');
    ylabel('f(x)-f^\ast');
    set(gca, 'FontSize', 16);
    drawnow;
    
    % print messages to the command line
    fprintf('[Gradient: %3.d/%3.d]', indIter, numIter);
    fprintf('[step = %.1e]', step);
    fprintf('[fdif = %.3e]', fdif(indIter));
    fprintf('[grad norm = %.1e]', norm(grad(xnext)));
    fprintf('[x = (%.4f, %.4f)]', x(1), x(2));
    fprintf('\n');
    
    % check termination tolerance
    if(norm(grad(x)) < tol)
        break;
    end
    
    % update the iterate
    x = xnext;
end

xgm = x;