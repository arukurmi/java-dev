package Interview.ScratchSample;

import java.util.LinkedList;
import java.util.List;

public class UnionFind {
    int n;
    int[] parent, rank;
    public UnionFind(int n){
        this.n = n;
        rank = new int[n];
        parent = new int[n];
        for(int i=0; i<n; i++){
            parent[i] = i;
        }
        List<Integer> dis = new LinkedList<>();
        dis.add
    }

    public int find(int x){
        int root = parent[x];
        if(root != parent[root]){
            return parent[x] = find(root);
        }

        return root;
    }

    public void union(int x, int y){
        int xPar = find(x);
        int yPar = find(y);

        if(xPar == yPar){
            return;
        }
        if(rank[xPar] < rank[yPar]){
            parent[xPar] = yPar;
        } else if(rank[yPar] < rank[xPar]) {
            parent[yPar] = xPar;
        } else {
            parent[yPar] = xPar;
            rank[xPar] = rank[xPar] + 1;
        }

        return;
    }
}
