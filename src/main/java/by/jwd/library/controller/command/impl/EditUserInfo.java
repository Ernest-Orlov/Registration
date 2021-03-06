package by.jwd.library.controller.command.impl;

import by.jwd.library.bean.User;
import by.jwd.library.controller.command.Command;
import by.jwd.library.controller.command.CommandException;
import by.jwd.library.controller.command.impl.util.LocalMessageCoder;
import by.jwd.library.controller.command.impl.util.SessionCheck;
import by.jwd.library.controller.constant.CommandURL;
import by.jwd.library.controller.constant.RequestAttribute;
import by.jwd.library.controller.constant.RequestParameter;
import by.jwd.library.controller.constant.SessionAttributes;
import by.jwd.library.controller.constant.local.LocalParameter;
import by.jwd.library.service.ServiceException;
import by.jwd.library.service.UserService;
import by.jwd.library.service.factory.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditUserInfo implements Command {

    private static final Logger logger = LoggerFactory.getLogger(EditUserInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        SessionCheck.userLoggedIn(request);

        String name = request.getParameter(RequestParameter.NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String login = request.getParameter(RequestParameter.LOGIN);

        HttpSession session = request.getSession();
        int userID = (int) session.getAttribute(SessionAttributes.USER_ID);
        String localeStr = (String) request.getSession().getAttribute(SessionAttributes.LOCAL);

        UserService userService = ServiceFactory.getInstance().getUserService();
        try {

            User user = userService.getUserByEmail(email);
            if (user != null && user.getUserId() != userID) {
                response.sendRedirect(CommandURL.EDIT_USER_INFO_FORM + "&" + RequestAttribute.EDIT_USER_INFO_MSG + "=" +
                        LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.EMAIL_EXISTS_MSG));
                return;
            }

            user = userService.getUserByLogin(login);
            if (user != null && user.getUserId() != userID) {
                response.sendRedirect(CommandURL.EDIT_USER_INFO_FORM + "&" + RequestAttribute.EDIT_USER_INFO_MSG + "=" +
                        LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.LOGIN_EXISTS_MSG));
                return;
            }

            user = userService.getUserById(userID);
            user.setName(name);
            user.setEmail(email);
            user.setLogin(login);

            userService.editUser(user);

            response.sendRedirect(CommandURL.EDIT_USER_INFO_FORM + "&" + RequestAttribute.EDIT_USER_INFO_MSG + "=" +
                    LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.EDIT_SUCCESS_MSG));


        } catch (IOException e) {
            logger.error("IOException in EditUserInfo", e);
            throw new CommandException(e);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
