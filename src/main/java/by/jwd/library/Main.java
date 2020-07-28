package by.jwd.library;

import by.jwd.library.dao.DAOException;
import by.jwd.library.dao.connectionpool.ConnectionPoolManager;
import by.jwd.library.service.ServiceException;
import by.jwd.library.service.factory.ServiceFactory;
import by.jwd.library.service.util.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) throws DAOException, ServiceException {
//        ConnectionPoolManager.getInstance().initConnectionPool();


//        System.out.println(ServiceFactory.getInstance().getLibraryService().searchLoans("11111111111111"));

//        ServiceFactory.getInstance().getLibraryService().closeOutdatedReservations();


        System.out.println(BCrypt.hash("Password"));
        System.out.println(BCrypt.hash("Password1"));
        System.out.println(BCrypt.hash("Password2"));
        System.out.println(BCrypt.hash("Password3"));
        System.out.println(BCrypt.hash("Password4"));


        LocalDateTime localDateTime = LocalDateTime.now();
        logger.debug("TEST {}", localDateTime);

    }
}
