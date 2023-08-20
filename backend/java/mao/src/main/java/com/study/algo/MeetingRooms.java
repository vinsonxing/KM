package com.study.algo;

import java.util.*;
/*
注意时间复杂度，O(N)，如果不可接受，要看是否有可能二分答案-谷歌排队等位
 */
//https://leetcode.ca/2022-10-21-2402-Meeting-Rooms-III/
public class MeetingRooms {
    static class Meeting {
        private int start;
        private int end;
        private int room;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public void setRoom(int room) {
            this.room = room;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getRoom() {
            return room;
        }
    }

    static int getMostBusyRoom(int[][] meetings, int n) {
        PriorityQueue<Meeting> pq = new PriorityQueue<>(n, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.getEnd() < o2.getEnd() || o1.getEnd() == o2.getEnd() && o1.getRoom() < o2.getRoom()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
        Map<Integer, Integer> assignment = new HashMap<>();
        TreeSet<Integer> rooms = new TreeSet<>();
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int size = meetings.length;
        Meeting[] meetingArr = new Meeting[size];
        for (int i = 0; i < size; i++) {
            meetingArr[i] = new Meeting(meetings[i][0], meetings[i][1]);
        }
        for (int i = 0; i < n; i++) {
            rooms.add(i);
        }
        for (int i = 0; i < size; i++) {
            Meeting newMeeting = meetingArr[i];
            if (pq.size() < n) {
                pq.offer(newMeeting);
                int room = rooms.pollFirst();
                newMeeting.setRoom(room);
                assignment.put(room, assignment.getOrDefault(room, 0) + 1);
            } else {
                int room = -1;
                if (pq.peek().getEnd() > newMeeting.getStart()) {
                    Meeting topMeeting = pq.poll();
                    int interval = newMeeting.getEnd() - newMeeting.getStart();
                    newMeeting.setStart(topMeeting.getEnd());
                    newMeeting.setEnd(newMeeting.getStart() + interval);
                    room = topMeeting.getRoom();

                } else {
                    while (pq.peek().getEnd() <= newMeeting.getStart()){
                        rooms.add(pq.poll().getRoom());
                    }
                    room = rooms.pollFirst();
                }
                newMeeting.setRoom(room);
                pq.offer(newMeeting);
                assignment.put(room, assignment.get(room) + 1);
            }
        }
        int max = assignment.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        return max;
    }

    public static void main(String[] args) {
        int[][] meetings ={{0,10},{1,5},{2,7},{3,4}};
        System.out.println(getMostBusyRoom(meetings, 2));
    }
}
