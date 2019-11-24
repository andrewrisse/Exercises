'''
01KnapSackDP.py 
creator: Andrew Risse
Solves the 0-1 knapsack problem with a dynamic programming solution.
'''

def knap(capacity, weights, values, n): # n is used to remember the max number of items in the values[] array
    items = [[0 for x in range(capacity+1)] for x in range(n+1)] #initialize 2d array for dp solution, +1 is for the 0,0 case
    
    # build bottom up
    for i in range(n+1):
        for w in range(capacity + 1):
            if i==0 or w == 0:
                items[i][w] = 0  # all zeroes for no items or no capacity cases
            elif weights[i-1] <= w: # current item weighs less than current weight capacity
                items[i][w] = max(values[i-1] + items[i-1][w - weights[i-1]], items[i-1][w]) # choose max value of choosing this item or not choosing it
            else:
                items[i][w] = items[i-1][w] # item doesn't fit, use best case without this item value
        
    return items[n][capacity]
        

weights = [10,20,30]
values = [60,100,120]
n = len(values)
print(knap(50,weights, values, n))
