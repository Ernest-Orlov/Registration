package by.jwd.library.controller.command.impl;

import by.jwd.library.bean.User;
import by.jwd.library.controller.command.Command;
import by.jwd.library.controller.command.CommandException;
import by.jwd.library.controller.command.impl.util.LocalMessageCoder;
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
import java.io.IOException;

public class Registration implements Command {

    private static final Logger logger = LoggerFactory.getLogger(Registration.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String name = request.getParameter(RequestParameter.NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passportId = request.getParameter(RequestParameter.PASSPORT_ID);

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setPassportId(passportId);

        UserService userService = ServiceFactory.getInstance().getUserService();

        String localeStr = (String) request.getSession().getAttribute(SessionAttributes.LOCAL);

        try {
            if (userService.emailExists(email)) {
                response.sendRedirect(CommandURL.REGISTRATION_FORM + "&" + RequestAttribute.REGISTRATION_FAIL_MSG
                        + "=" + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.EMAIL_EXISTS_MSG));
                return;
            }
            if (userService.loginExists(login)) {
                response.sendRedirect(CommandURL.REGISTRATION_FORM + "&" + RequestAttribute.REGISTRATION_FAIL_MSG
                        + "=" + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.LOGIN_EXISTS_MSG));
                return;
            }
            if (userService.passportIdExists(passportId)) {
                response.sendRedirect(CommandURL.REGISTRATION_FORM + "&" + RequestAttribute.REGISTRATION_FAIL_MSG
                        + "=" + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.PASSPORT_ID_EXISTS_MSG));
                return;
            }

            ServiceFactory.getInstance().getUserService().register(user);

            response.sendRedirect(CommandURL.REGISTRATION_FORM + "&" + RequestAttribute.REGISTRATION_SUCCESS_MSG
                    + "=" + LocalMessageCoder.getCodedLocalizedMsg(localeStr, LocalParameter.REGISTRATION_SUCCESS_MSG));


        } catch (IOException e) {
            logger.error("IOException in Registration", e);
            throw new CommandException(e);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

}
