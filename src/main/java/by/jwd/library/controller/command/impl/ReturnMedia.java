package by.jwd.library.controller.command.impl;

import by.jwd.library.controller.command.Command;
import by.jwd.library.controller.command.CommandException;
import by.jwd.library.controller.command.impl.util.SessionCheck;
import by.jwd.library.controller.constant.CommandURL;
import by.jwd.library.controller.constant.RequestParameter;
import by.jwd.library.service.ServiceException;
import by.jwd.library.service.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReturnMedia implements Command {

    private static final Logger logger = LoggerFactory.getLogger(ReturnMedia.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        SessionCheck.librarianOrAdmin(request);

        try {

            int copyId = Integer.parseInt(request.getParameter(RequestParameter.COPY_ID));
            int loanId = Integer.parseInt(request.getParameter(RequestParameter.LOAN_ID));

            ServiceFactory.getInstance().getLibraryService().returnMedia(copyId, loanId);

            response.sendRedirect(CommandURL.RETURN_MEDIA_FORM);


        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (IOException e) {
            logger.error("IOException in ReturnMedia", e);
            throw new CommandException(e);
        }
    }
}
