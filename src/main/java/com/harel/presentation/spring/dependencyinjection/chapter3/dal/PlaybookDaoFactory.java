package com.harel.presentation.spring.dependencyinjection.chapter3.dal;

public class PlaybookDaoFactory {
    private final static PlaybookDao pgPlaybookDao = new PgPlaybookDao();
    private final static PlaybookDao pgDemoPlaybookDao = new PgDemoPlaybookDao();
    private final static PlaybookDao h2PlaybookDao = new H2PlaybookDao();

    public static PlaybookDao provide(String profile) {
        switch (profile) {
            case ("prod"):
                return pgPlaybookDao;
            case("demo"):
                return pgDemoPlaybookDao;
            case("test"):
                return h2PlaybookDao;
            default:
                throw new IllegalArgumentException("unknown profile. profile=" + profile);
        }

    }
}
