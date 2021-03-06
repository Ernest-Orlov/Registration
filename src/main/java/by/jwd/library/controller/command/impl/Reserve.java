package by.jwd.library.controller.command.impl;

import by.jwd.library.controller.command.Command;
import by.jwd.library.controller.command.CommandException;
import by.jwd.library.controller.command.impl.util.LocalMessageCoder;
import by.jwd.library.controller.command.impl.util.QueryCoder;
import by.jwd.library.controller.command.impl.util.SessionCheck;
import by.jwd.library.controller.constant.CommandURL;
import by.jwd.library.controller.constant.RequestAttribute;
import by.jwd.library.controller.constant.RequestParameter;
import by.jwd.library.controller.constant.SessionAttributes;
import by.jwd.library.controller.constant.local.LocalParameter;
import by.jwd.library.service.LibraryService;
import by.jwd.library.service.ServiceException;
import by.jwd.library.service.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Reserve implements Command {

    private static final Logger logger = LoggerFactory.getLogger(Reserve.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        SessionCheck.userLoggedIn(request);

        int mediaId = Integer.parseInt(request.getParameter(RequestParameter.MEDIA_ID));
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute(SessionAttributes.USER_ID);
        String localeStr = (String) request.getSession().getAttribute(SessionAttributes.LOCAL);

        String lastCommand = request.getParameter(RequestParameter.LAST_COMMAND);
        String mediaDetailCallPoint;

        LibraryService libraryService = ServiceFactory.getInstance().getLibraryService();
        try {


            String lastPage = request.getParameter(RequestParameter.LAST_PAGE);
            if ((lastCommand == null || lastCommand.isEmpty()) || lastPage == null || lastPage.isEmpty()) {
                mediaDetailCallPoint = CommandURL.MEDIA_DETAIL + "&" + RequestParameter.MEDIA_ID + "=" + mediaId;
            } else {
                lastCommand = lastCommand.substring(0, lastCommand.indexOf(RequestParameter.LAST_PAGE));
                mediaDetailCallPoint = CommandURL.CONTROLLER + "?" + lastCommand
                        + "&" + RequestAttribute.LAST_PAGE + "=" +
                        QueryCoder.code(request.getParameter(RequestParameter.LAST_PAGE) + "&" + RequestParameter.PAGE + "="
                                + request.getParameter(RequestParameter.PAGE) + "&" + RequestParameter.SEARCH + "="
                                + request.getParameter(RequestParameter.SEARCH));
            }


            if (ServiceFactory.getInstance().getUserService().checkUnverified(userId)) {
                response.sendRedirect(mediaDetailCallPoint + "&" + RequestAttribute.RESERVATION_MSG + "="
                        + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.UNVERIFIED_USER_MSG));
                return;
            }
            if (!libraryService.canReserve(userId)) {
                response.sendRedirect(mediaDetailCallPoint + "&" + RequestAttribute.RESERVATION_MSG + "="
                        + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.RESERVATION_MAX_LIMIT_MSG));
                return;
            }
            if (libraryService.userReservedOrLoanedMedia(userId, mediaId)) {
                response.sendRedirect(mediaDetailCallPoint + "&" + RequestAttribute.RESERVATION_MSG + "="
                        + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.RESERVATION_FORBIDDEN_MSG));
                return;
            }

            libraryService.reserveMedia(userId, mediaId);

            response.sendRedirect(mediaDetailCallPoint + "&" + RequestAttribute.RESERVATION_MSG + "="
                    + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.RESERVATION_SUCCESS_MSG));


        } catch (IOException e) {
            logger.error("IOException in Reserve", e);
            throw new CommandException(e);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
