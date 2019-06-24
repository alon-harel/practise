package com.harel.practise.inteviews.sourcemanager;

public class Application {

    public static void main(String[] args) {
        SourceManager user1 = new InMemorySourceManager();
        SourceManager user2 = new InMemorySourceManager();

        System.out.println("User 1 added a key with the value: 'start'");
        user1.set("a", "start");
        System.out.println("User 2 should see 'start': " + user2.get("a"));
        user1.begin();
        System.out.println("User 1 started transaction and changed the value to 'modify'");
        user1.set("a", "modify");
        System.out.println("User 1 should see 'modify': " + user1.get("a"));
        System.out.println("User 2 should keep seeing 'start': " + user2.get("a"));

        user1.delete("a");
        System.out.println("User 1 delete the key");
        System.out.println("User 1 should see 'null': " + user1.get("a"));
        System.out.println("User 2 should keep seeing 'start': " + user2.get("a"));

        user1.set("a", "end");
        System.out.println("User 1 modified the key to 'end'");

        System.out.println("User 1 should see 'end': " + user1.get("a"));
        System.out.println("User 2 should keep seeing 'start': " + user2.get("a"));

        user1.commit();
        System.out.println("After user 1 commit, User 2 should see 'end': " + user2.get("a"));
        System.out.println("Also user 1 see 'end': " + user1.get("a"));

        user1.begin();
        user1.set("a", "blabla");
        System.out.println("User 1 started a new transaction and changed the value to 'blabla': " + user1.get("a"));
        user1.rollback();
        System.out.println("After user 1 rollback, User 2 should see 'end': " + user2.get("a"));
        System.out.println("Also user 1 see 'end': " + user1.get("a"));

    }
}
