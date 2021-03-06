package by.jwd.library.service.factory;


import by.jwd.library.service.LibraryService;
import by.jwd.library.service.UserService;
import by.jwd.library.service.impl.LibraryServiceImpl;
import by.jwd.library.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }


    public UserService getUserService() {
        return userService;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

}
