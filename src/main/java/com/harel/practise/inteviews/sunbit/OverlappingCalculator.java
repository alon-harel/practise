package com.harel.practise.inteviews.sunbit;

/*
Given a list of meeting times, checks if any of them overlaps.
The follow-up question is to return the minimum number of rooms required to accommodate all the meetings.
Let’s start with an example.
Suppose we use interval (start, end) to denote the start and end time of the meeting, we have the following meeting times: (1, 4), (5, 6), (8, 9), (2, 6).
In the above example, apparently we should return true for the first question since (1, 4) and (2, 6) have overlaps.

(1, 4), (2, 6) (5, 6), (8, 9),
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OverlappingCalculator {
    public static class Schedule {
        int start;
        int end;

        public Schedule(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean isOverlap(Schedule other) {
            return this.end > other.start;
        }
    }

    public boolean isOverlap(List<Schedule> schedules) {
        boolean retVal = false;
        sort(schedules);
        for (int index = 0; index < schedules.size() - 1; index++) {
            if (schedules.get(index).isOverlap(schedules.get(index + 1))) {
                retVal = true;
                break;
            }
        }

        return retVal;
    }

    public int numberOfRooms(List<Schedule> schedules) {
        int numberOfRooms = 1;
        sort(schedules);
        for (int index = 0; index < schedules.size() - 1; index++) {
            if (schedules.get(index).isOverlap(schedules.get(index + 1))) {
                numberOfRooms++;
            }
        }

        return numberOfRooms;
    }

    private void sort(List<Schedule> durations) {
        durations.sort(Comparator.comparingInt(o -> o.start));
    }

    public static void main(String[] args) {
        Schedule d1 = new Schedule(1, 4);
        Schedule d2 = new Schedule(5, 6);
        Schedule d3 = new Schedule(8, 9);
        Schedule d4 = new Schedule(2, 6);

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(d1);
        schedules.add(d2);
        schedules.add(d3);
        schedules.add(d4);

        System.out.println(new OverlappingCalculator().isOverlap(schedules));
        System.out.println(new OverlappingCalculator().numberOfRooms(schedules));
    }
}