package com.harel.practise.inteviews.islandscounter;

public class Counter {

    public int countIslands(Integer[][] sea) {
        int islandsCount = 0;
        int n = sea.length;
        int m = sea[0].length;

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < m; column++) {
                if (landAt(row, column, sea)) {
                    markIsland(row, column, sea);
                    islandsCount++;
                }
            }
        }

        return islandsCount;
    }

    private void markIsland(int row, int column, Integer[][] sea) {
        if (atSeaBoundaries(row, column, sea)) {
            if (landAt(row, column, sea)) {
                markAsVisited(row, column, sea);
                markIsland(row + 1, column, sea);
                markIsland(row + 1, column + 1, sea);
                markIsland(row + 1, column - 1, sea);
                markIsland(row, column + 1, sea);
                markIsland(row, column - 1, sea);
            }
        }
    }

    private boolean atSeaBoundaries(int row, int column, Integer[][] sea) {
        return (row >= 0) &&
                (row < sea.length) &&
                (column >= 0) &&
                (column < sea[0].length);
    }

    private void markAsVisited(int row, int column, Integer[][] sea) {
        sea[row][column] = 2;
    }

    private boolean landAt(int row, int column, Integer[][] sea) {
        return sea[row][column] == 1;
    }
}
