package com.harel.practise.inteviews.islandscounter;

public class IslandsMaker {
    public Integer[][] make(int n, int m) {
        Integer[][] sea = new Integer[n][m];
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                sea[row][column] = drawLandOrSea();
            }
        }

        return sea;
    }

    private int drawLandOrSea() {
        return new Long(Math.round(Math.random())).intValue();
    }
}
