Introduction 
============================
How to run
===========================
Navigate into bin folder and please run 

java dataList <SeedFile> <output>
 
The algorithm 
===========================================
The K-means algorithm is a method for clustering a set of data
 points into several 
similar groups. Given an input K
 value, the algorithm starts by
 randomly selecting K 
centroids. Then it iterates between 
assigning cluster memberships
 and 
recalculating 
centroids
. A data point is assigned as a m
ember to the closest cluster. 
Use the Euclidean 
distance in this assignment. New
 centroids are calculated by ta
king average values of 
each feature.  The two steps a
re repeated until there is no mor
e change in membership or 
centroids.  
The Seeds dataset 
Measurements of geometrical prope
rties of kernels belonging to 
three different 
varieties of wheat. A
 soft X-ray technique and GRAINS package w
ere used to construct 
all seven, real-valued attributes.  
Attribute Information:  
1. area A,  
2. perimeter P,  
3. compactness C = 4*pi*A/P^2,  
4. length of kernel,  
5. width of kernel,  
6. asymmetry coefficient  
7. length of kernel groove.  
All of these parameters we
re real-valued continuous. 
